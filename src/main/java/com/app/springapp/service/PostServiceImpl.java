package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import com.app.springapp.domain.dto.response.*;
import com.app.springapp.domain.vo.PostVO;
import com.app.springapp.exception.PostException;
import com.app.springapp.repository.LogDAO;
import com.app.springapp.repository.PostDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final PostLikeService postLikeService;
    private final ReplyService replyService;

    private final LogDAO logDAO;
    private final PostVO postVO;

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Override
    public CommunityResponseDTO getCommunityInfo(Long id) {
        CommunityResponseDTO communityResponseDTO = new CommunityResponseDTO();

        //지난달 인기글 불러오기(지난달)
        communityResponseDTO.setPostMonth(postDAO.findPopularPostAtLastMonth(id));

        //실시간 인기글 불러오기(1개월전 ~ 현재)
        communityResponseDTO.setPopularPosts(postDAO.findPopularPosts(id));

        HashMap<String, Object> params = new HashMap<>();
        params.put("order", 0);
        params.put("order2", 0);
        params.put("page", 1);
        params.put("category", 0);
        params.put("content", "");
        params.put("memberId", id);
        //검색 게시글 초기화
        communityResponseDTO.setPost(getSearchResult(params));

        return communityResponseDTO;
    }

    //검색 결과 만족하는 게시글 리스트로 반환
    @Override
    public List<PostListResponseDTO> getPostList(Map<String, Object> order) {
        return postDAO.selectPostList(order);
    }

    //검색 결과 만족하는 내 게시글 리스트로 반환
    @Override
    public List<PostListResponseDTO> getMyPostList(Map<String, Object> order) {
        return postDAO.findMyPostAll(order);
    }

    //검색 결과 게시글 목록 갯수 (페이지 조건제외)
    @Override
    public Integer getPostCount(Map<String, Object> order) {
        return postDAO.getPostCount(order);
    }

    //검색 결과 내 게시글 목록 갯수 (페이지 조건제외)
    @Override
    public Integer getMyPostCount(Map<String, Object> order) {
        return postDAO.getMyPostCount(order);
    }

    //검색 결과 정보 DTO반환(게시글 총 갯수(페이지 조건제외) + 게시글 목록)
    @Override
    public CommunityPostListResponseDTO getSearchResult(Map<String, Object> order) {
        CommunityPostListResponseDTO communityPostListResponseDTO = new CommunityPostListResponseDTO();
        communityPostListResponseDTO.setTotal(getPostCount(order));
        communityPostListResponseDTO.setPosts(getPostList(order));
        return communityPostListResponseDTO;
    }

    //검색 결과 내 게시글 정보 DTO반환(게시글 총 갯수(페이지 조건제외) + 게시글 목록)
    @Override
    public CommunityPostListResponseDTO getMyPostSearchResult(Map<String, Object> order) {
        CommunityPostListResponseDTO communityPostListResponseDTO = new CommunityPostListResponseDTO();
        communityPostListResponseDTO.setTotal(getMyPostCount(order));
        communityPostListResponseDTO.setPosts(getMyPostList(order));
        return communityPostListResponseDTO;
    }

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    @Override
    public PostResponseDTO findPost(PostReadRequestDTO postReadRequestDTO) {
        return postDAO.findById(postReadRequestDTO).orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
    }

    //id로 게시글 검색
    @Override
    public PostVO findPost(Long id) {
        return postDAO.find(id).orElseThrow(() -> new PostException("게시글을 찾지 못했습니다.", HttpStatus.NOT_FOUND));
    }

    @Override
    public PostListResponseDTO findPopularPostAtLastMonth(Long id) {
        return postDAO.findPopularPostAtLastMonth(id);
    }


    //인기글 목록 불러오기
    @Override
    public List<PostListResponseDTO> findPopularPosts(Long id) {
        return postDAO.findPopularPosts(id);
    }

    // POST ID로 이전글 찾기
    @Override
    public PostBeforeResponseDTO findBeforePost(Long postId) {
        return postDAO.findBeforePost(postId);
    }

    //POST ID로 다음글 찾기
    @Override
    public PostAfterResponseDTO findAfterPost(Long postId) {
        return postDAO.findAfterPost(postId);
    }

    //작성자가 작성한 게시글 수
    @Override
    public Integer countPost(Long memberId) {
        return postDAO.countPost(memberId);
    }

    //게시판 상세페이지에 모든 정보 불러오기
    @Override
    public PostReadResponseDTO getPostDetailInfo(PostReadRequestDTO postReadRequestDTO) {
        PostReadResponseDTO postReadResponseDTO = new PostReadResponseDTO();

        postReadResponseDTO.setPost(findPost(postReadRequestDTO));  //게시글 정보 저장
        postReadResponseDTO.setReplies(replyService.getPostReplies(postReadRequestDTO));    //게시글에 달린 댓글 정보(대댓글포함) 저장
        postReadResponseDTO.setBeforePost(findBeforePost(postReadRequestDTO.getPostId()));  //이전글 정보 저장
        postReadResponseDTO.setAfterPost(findAfterPost(postReadRequestDTO.getPostId()));    //다음글 정보 저장

        Long memberId = postReadResponseDTO.getPost().getMemberId();

        postReadResponseDTO.setMemberPostCount(countPost(memberId));    //해당 멤버의 게시글 갯수
        postReadResponseDTO.setMemberLogCount(logDAO.findAllByMemberId(memberId).toArray().length); //해당 멤버의 로그갯수
        postReadResponseDTO.setMemberReplyCount(replyService.countReply(memberId)); //해당 멤버의 댓글 갯수

        //게시글 조회수 증가
        increaseReadCount(postReadRequestDTO.getPostId());

        return postReadResponseDTO;
    }

    //게시글 작성 (PostCreateResponseDTO는 새로 작성된 게시글 번호정보가 들어있다.)
    @Override
    public PostCreateResponseDTO writePost(PostCreateDTO postCreateDTO) {

        postDAO.save(postCreateDTO);

        PostCreateResponseDTO postCreateResponseDTO = new PostCreateResponseDTO();
        postCreateResponseDTO.setPostId(postCreateDTO.getId());

        return postCreateResponseDTO;
    }

    //게시글 수정
    @Override
    public void updatePost(PostUpdateRequestDTO postUpdateRequestDTO) {
        postDAO.update(postUpdateRequestDTO);
    }

    //게시글 조회수 증가
    @Override
    public void increaseReadCount(Long postId) {
        postDAO.increaseReadCount(postId);
    }

    //게시글 삭제
    @Override
    public void deletePost(Long postId) {
        //게시글 모든 좋아요 삭제
        postLikeService.cancelPostLikeAll(postId);

        //게시글에 달린 모든 댓글 삭제
        replyService.deleteReplies(postId);

        //게시글 삭제
        postDAO.delete(postId);
    }

    @Override
    public PostVO findIdAndPostContentById(Long postId) {
        return postDAO.findIdAndPostContentByPostId(postId);
    }

    //ai 추천글 (메인 커뮤니티)
    @Override
    public List<PostListResponseDTO> getPostAiRecommand(Long memberId) {
        List<PostListResponseDTO> postListResponseDTOs = new ArrayList<PostListResponseDTO>();
        PostVO postVO = postDAO.findIdAndPostContentByMemberId(memberId);
        if (postVO == null) {
            return postListResponseDTOs;
        }

        // 게시글 내용 (HTML 태그 제거)
        String target = postVO.getPostContent().replaceAll("<[^>]*>", "");

        // 비교 대상 게시글 목록 (HTML 태그 제거)
        List<PostVO> posts = postDAO.findIdAndPostContentExceptMemberId(memberId);
        posts = posts.stream()
                .peek(x -> x.setPostContent(x.getPostContent().replaceAll("<[^>]*>", "")))
                .toList();
        log.info("{}", posts);

        if (posts.isEmpty()) {
            log.info("추천할 게시글이 없습니다.");
            return postListResponseDTOs;
        }

        // OpenAI에 전달할 게시글 목록 문자열 생성
        StringBuilder postsBuilder = new StringBuilder();
        for (PostVO post : posts) {
            postsBuilder.append("ID: ").append(post.getId())
                    .append(", 내용: ").append(post.getPostContent())
                    .append("\n");
        }

        // 프롬프트 구성
        String prompt = "아래는 기준 게시글과 비교 게시글 목록입니다.\n\n"
                + "기준 게시글 내용:\n" + target + "\n\n"
                + "비교 게시글 목록 (ID: 내용):\n" + postsBuilder
                + "\n기준 게시글과 내용이 가장 유사하거나 관련성이 높은 게시글의 ID를 최대 3개 골라주세요. "
                + "반드시 아래 JSON 형식으로만 응답하세요. 다른 설명은 절대 포함하지 마세요.\n"
                + "{\"ids\": [1, 2, 3]}";

        List<Long> results = new ArrayList<>();

        try {
            // OpenAI 메시지 구성
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage("system",
                    "당신은 게시글 내용을 분석하여 관련성 높은 게시글을 추천하는 어시스턴트입니다. " +
                            "항상 지정된 JSON 형식으로만 응답합니다."));
            messages.add(new ChatMessage("user", prompt));

            // OpenAI API 호출
            OpenAiService openAiService = new OpenAiService(apiKey);
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                    .model(model)
                    .messages(messages)
                    .maxTokens(256)
                    .temperature(0.0)   // 결정적 응답을 위해 temperature 0
                    .build();

            String aiResponse = openAiService.createChatCompletion(completionRequest)
                    .getChoices().get(0).getMessage().getContent();

            log.info("AI 추천 원문: {}", aiResponse);

            // JSON 파싱하여 ids 추출
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode idsNode = objectMapper.readTree(aiResponse).path("ids");

            if (idsNode.isArray()) {
                for (JsonNode idNode : idsNode) {
                    results.add(idNode.asLong());
                }
            }

            log.info("AI 추천 게시글 IDs: {}", results);
            // 이후 results를 DB 저장 또는 반환 처리

        } catch (Exception e) {
            log.error("OpenAI AI 추천 실패: {}", e.getMessage(), e);
            return postListResponseDTOs;
        }

        //ai가 선택한 id > postListResponseDTO로 변환
        postListResponseDTOs = results.stream().map(x -> {
            PostVO requestVO = new PostVO();
            requestVO.setId(x);
            requestVO.setMemberId(memberId);
            return postDAO.findByMemberIdAndPostId(requestVO);
        }).toList();

        return postListResponseDTOs;
    }

    //ai 추천글 (게시글 상세 페이지)
    @Override
    public List<PostListResponseDTO> getPostAiRecommand(Long memberId, Long postId) {
        List<PostListResponseDTO> postListResponseDTOs = new ArrayList<PostListResponseDTO>();
        PostVO postVO = postDAO.findIdAndPostContentByPostId(postId);
        if (postVO == null) {
            return postListResponseDTOs;
        }

        // 게시글 내용 (HTML 태그 제거)
        String target = postVO.getPostContent().replaceAll("<[^>]*>", "");

        // 비교 대상 게시글 목록 (HTML 태그 제거)
        List<PostVO> posts = postDAO.findIdAndPostContentExceptId(postId);
        posts = posts.stream()
                .peek(x -> x.setPostContent(x.getPostContent().replaceAll("<[^>]*>", "")))
                .toList();
        log.info("{}", posts);

        if (posts.isEmpty()) {
            log.info("추천할 게시글이 없습니다.");
            return postListResponseDTOs;
        }

        // OpenAI에 전달할 게시글 목록 문자열 생성
        StringBuilder postsBuilder = new StringBuilder();
        for (PostVO post : posts) {
            postsBuilder.append("ID: ").append(post.getId())
                    .append(", 내용: ").append(post.getPostContent())
                    .append("\n");
        }

        // 프롬프트 구성
        String prompt = "아래는 기준 게시글과 비교 게시글 목록입니다.\n\n"
                + "기준 게시글 내용:\n" + target + "\n\n"
                + "비교 게시글 목록 (ID: 내용):\n" + postsBuilder
                + "\n기준 게시글과 내용이 가장 유사하거나 관련성이 높은 게시글의 ID를 최대 4개 골라주세요. "
                + "반드시 아래 JSON 형식으로만 응답하세요. 다른 설명은 절대 포함하지 마세요.\n"
                + "{\"ids\": [1, 2, 3, 4]}";

        List<Long> results = new ArrayList<>();

        try {
            // OpenAI 메시지 구성
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage("system",
                    "당신은 게시글 내용을 분석하여 관련성 높은 게시글을 추천하는 어시스턴트입니다. " +
                            "항상 지정된 JSON 형식으로만 응답합니다."));
            messages.add(new ChatMessage("user", prompt));

            // OpenAI API 호출
            OpenAiService openAiService = new OpenAiService(apiKey);
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                    .model(model)
                    .messages(messages)
                    .maxTokens(256)
                    .temperature(0.0)   // 결정적 응답을 위해 temperature 0
                    .build();

            String aiResponse = openAiService.createChatCompletion(completionRequest)
                    .getChoices().get(0).getMessage().getContent();

            log.info("AI 추천 원문: {}", aiResponse);

            // JSON 파싱하여 ids 추출
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode idsNode = objectMapper.readTree(aiResponse).path("ids");

            if (idsNode.isArray()) {
                for (JsonNode idNode : idsNode) {
                    results.add(idNode.asLong());
                }
            }

            log.info("AI 추천 게시글 IDs: {}", results);
            // 이후 results를 DB 저장 또는 반환 처리

        } catch (Exception e) {
            log.error("OpenAI AI 추천 실패: {}", e.getMessage(), e);
            return postListResponseDTOs;
        }

        //ai가 선택한 id > postListResponseDTO로 변환
        postListResponseDTOs = results.stream().map(x -> {
            PostVO requestVO = new PostVO();
            requestVO.setId(x);
            requestVO.setMemberId(memberId);
            return postDAO.findByMemberIdAndPostId(requestVO);
        }).toList();

        return postListResponseDTOs;
    }
}
