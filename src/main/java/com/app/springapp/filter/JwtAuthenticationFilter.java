package com.app.springapp.filter;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.exception.MemberException;
import com.app.springapp.repository.MemberDAO;
import com.app.springapp.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberDAO memberDAO;
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;

    @Value("${jwt.token-blacklist-prefix}")
    private String BLACKLIST_TOKEN_PREFIX;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        // 로그아웃은 토큰 만료/없어도 쿠키 삭제가 실행돼야 하므로 필터 제외
        if (path.equals("/private/auth/logout") && "POST".equals(method)) return true;

        if (path.startsWith("/private")) return false;
        if (path.startsWith("/api/private")) return false;
        if (path.startsWith("/api/ai")) return false;
        if (path.startsWith("/api/project")) return false;
        if (path.startsWith("/api/logs")) return false;
        if (path.equals("/api/suggestion/create") && method.equals("POST")) return false;
        if (path.startsWith("/api/project") && !path.startsWith("/api/project/public")) return false;
        if (path.startsWith("/api/checklist") && (method.equals("POST") || method.equals("PUT") || method.equals("DELETE"))) return false;
        return true;
    }

    private String getAccessTokenFromCookie(HttpServletRequest request){
        if(request.getCookies() == null){
            return null;
        }

        for(Cookie cookie : request.getCookies()){
            if("accessToken".equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessToken = getAccessTokenFromCookie(request);
        String memberEmail = null;
        String socialMemberProvider = null;
        boolean isPrivatePath = request.getRequestURI().startsWith("/private");

        if (accessToken == null) {
            if (isPrivatePath) {
                sendErrorResponse(response, "토큰 없음 또는 인증 실패");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = jwtTokenUtil.parseToken(accessToken);

            // 블랙리스트 체크: 로그아웃된 토큰 차단
            try {
                String blacklistKey = BLACKLIST_TOKEN_PREFIX + claims.get("id");
                Boolean isBlacklisted = redisTemplate.opsForSet().isMember(blacklistKey, accessToken);
                if (Boolean.TRUE.equals(isBlacklisted)) {
                    SecurityContextHolder.clearContext();
                    sendErrorResponse(response, "이미 로그아웃된 토큰입니다.");
                    return;
                }
            } catch (Exception e) {
                log.warn("[JWT 필터] 블랙리스트 체크 실패 (Redis 오류): {}", e.getMessage());
            }

            memberEmail = (String) claims.get("memberEmail");
            socialMemberProvider = (String) claims.get("socialMemberProvider");

            if(memberEmail != null && socialMemberProvider != null){
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setMemberEmail(memberEmail);
                memberDTO.setSocialMemberProvider(socialMemberProvider);
                MemberDTO foundMember = memberDAO.findMemberByMemberEmailAndSocialMemberProvider(memberDTO)
                        .orElseThrow(() -> {throw new MemberException("doFilterInternal 회원 조회 실패", HttpStatus.BAD_REQUEST);});

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(foundMember, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            sendErrorResponse(response, "토큰 만료");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponseDTO apiResponseDTO = ApiResponseDTO.of(false, message);
        String json = objectMapper.writeValueAsString(apiResponseDTO);
        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
