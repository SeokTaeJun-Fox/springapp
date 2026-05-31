package com.app.springapp.constant;

public class LogPromptConstants {

    public static final String JSON_SCHEMA = "{\n" +
            "  \"failureType\": \"Short classification of failure (e.g. 인지 과부하 및 회복 탄력성 부족)\",\n" +
            "  \"failureTitle\": \"A catchy, insightful analysis title\",\n" +
            "  \"failureDesc\": \"Detailed analysis paragraph (max 1000 Korean characters)\",\n" +
            "  \"oneLineSummary\": \"A short motivational one-line quote/summary\",\n" +
            "  \"oneLineSub\": \"A short sub-phrase supporting the one-line summary\",\n" +
            "  \"externalRatio\": \"Integer 0-100 (external failure factors)\",\n" +
            "  \"internalRatio\": \"Integer 0-100 (internal failure factors, externalRatio+internalRatio=100)\",\n" +
            "  \"flowSituation\": \"Starting context leading to failure (max 50 chars)\",\n" +
            "  \"flowDecision\": \"Critical wrong decision or turning point (max 50 chars)\",\n" +
            "  \"flowResult\": \"Ultimate direct failure outcome (max 50 chars)\",\n" +
            "  \"logKeyDecision\": \"Most critical subjective mistake (max 100 chars)\",\n" +
            "  \"logExternalFactor\": \"Most significant external factor (max 100 chars)\",\n" +
            "  \"logInternalFactor\": \"Most significant internal factor (max 100 chars)\",\n" +
            "  \"radarScores\": {\n" +
            "    \"Custom Category 1 (e.g., 완벽주의)\": \"Integer 0-100 (Choose exactly 6 distinct, highly relevant cognitive, psychological, or behavioral factors tailored to this specific log. Keys must be clear Korean terms of 2-5 characters)\",\n" +
            "    \"Custom Category 2\": \"Integer 0-100\",\n" +
            "    \"Custom Category 3\": \"Integer 0-100\",\n" +
            "    \"Custom Category 4\": \"Integer 0-100\",\n" +
            "    \"Custom Category 5\": \"Integer 0-100\",\n" +
            "    \"Custom Category 6\": \"Integer 0-100\"\n" +
            "  },\n" +
            "  \"patterns\": [\n" +
            "    {\n" +
            "      \"patternType\": \"HABIT\",\n" +
            "      \"patternTitle\": \"Title of repetitive failure pattern\",\n" +
            "      \"patternContent\": \"How this pattern manifests\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"patternType\": \"INTERNAL\",\n" +
            "      \"patternTitle\": \"Title of another pattern\",\n" +
            "      \"patternContent\": \"Content\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"patternType\": \"EXTERNAL\",\n" +
            "      \"patternTitle\": \"Title of third pattern\",\n" +
            "      \"patternContent\": \"Content\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"actionPlans\": {\n" +
            "    \"tryPlans\": [\n" +
            "      {\n" +
            "        \"title\": \"Action strategy 1\",\n" +
            "        \"content\": \"Detailed description 1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"title\": \"Action strategy 2\",\n" +
            "        \"content\": \"Detailed description 2\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"title\": \"Action strategy 3\",\n" +
            "        \"content\": \"Detailed description 3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"changePlans\": [\n" +
            "      {\n" +
            "        \"title\": \"Behavioral change 1\",\n" +
            "        \"currentPattern\": \"Bad pattern 1\",\n" +
            "        \"currentContent\": \"Details 1\",\n" +
            "        \"improvedPattern\": \"Positive pattern 1\",\n" +
            "        \"improvedContent\": \"Details 1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"title\": \"Behavioral change 2\",\n" +
            "        \"currentPattern\": \"Bad pattern 2\",\n" +
            "        \"currentContent\": \"Details 2\",\n" +
            "        \"improvedPattern\": \"Positive pattern 2\",\n" +
            "        \"improvedContent\": \"Details 2\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"title\": \"Behavioral change 3\",\n" +
            "        \"currentPattern\": \"Bad pattern 3\",\n" +
            "        \"currentContent\": \"Details 3\",\n" +
            "        \"improvedPattern\": \"Positive pattern 3\",\n" +
            "        \"improvedContent\": \"Details 3\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    public static String getStyleGuideline(String style) {
        if ("warm".equalsIgnoreCase(style)) {
            return "Write with high empathy, encouragement, warmth, and resilience-building. Focus on self-compassion and gentle, comforting advice. Avoid harsh words.";
        } else if ("cold".equalsIgnoreCase(style)) {
            return "Write with absolute directness, bluntness, and a harsh reality check (known as '팩폭' in Korean). Point out self-deception, laziness, poor habits, or excuses directly without sugarcoating. Make it highly motivational through cold truth.";
        }
        return "Write with logical objectivity, balance, systematic and metacognitive analysis. Provide structured, unbiased assessments.";
    }

    public static String getSystemTemplate(String guideline) {
        return "You are a professional failure-analysis AI. " +
               "Analyze the user's self-reflection log and output a highly structured JSON report.\n" +
               "Language: Korean (한국어로 작성).\n" +
               "CRITICAL: Do NOT use any emojis. Use plain text only (e.g. '->' not '➡️'). 이모지 사용 절대 금지.\n" +
               "CRITICAL: Based on the content depth and complexity, generate between 1 and 4 highly relevant items for 'patterns', 'tryPlans', and 'changePlans'. Do not force unnecessary items if the content is simple, but provide up to 4 if the situation is complex.\n" +
               "Analysis Style Guideline: " + guideline + "\n\n" +
               "You MUST respond with a single valid JSON object matching this exact schema. Ensure the arrays have 1 to 4 elements based on your analysis:\n" +
               JSON_SCHEMA;
    }

    public static String getHumanTemplate(String title, String category, String vision, String content, String pastLogs) {
        return "Here is the user's log details:\n" +
               "- Log Title: " + title + "\n" +
               "- Category: " + category + "\n" +
               "- Vision/Goal: " + vision + "\n" +
               "- Self-reflection Content:\n" + content + "\n\n" +
               "Past related failures for contextual comparison:\n" + (pastLogs != null && !pastLogs.isEmpty() ? pastLogs : "없음") + "\n\n" +
               "Perform the failure analysis. Return only valid JSON.";
    }
}
