package com.familysafety.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${openai.api.key:}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public String classifyApp(String appName) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "ERRO: Chave da OpenAI não configurada no application.properties";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String prompt = String.format(
            "Analisa o aplicativo ou site: '%s'. Responde APENAS com uma destas categorias exatas: EDUCAÇÃO, REDES_SOCIAIS, JOGOS, ENTRETENIMENTO, PRODUTIVIDADE, OUTROS. Não adiciones mais nenhum texto.",
            appName
        );

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(message));
        body.put("max_tokens", 20);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_URL, entity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> messageResp = (Map<String, Object>) choices.get(0).get("message");
                    return ((String) messageResp.get("content")).trim();
                }
            }
        } catch (Exception e) {
            return "ERRO_IA: " + e.getMessage();
        }

        return "OUTROS";
    }
}