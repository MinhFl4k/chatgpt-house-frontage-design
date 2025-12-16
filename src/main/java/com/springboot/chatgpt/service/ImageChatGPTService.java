package com.springboot.chatgpt.service;

import com.springboot.chatgpt.dto.ImageGenerationRequest;
import com.springboot.chatgpt.dto.ImageGenerationResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ImageChatGPTService {

    private final RestClient restClient;

    @Value("${openapi.api.key}")
    private String apiKey;

    @Value("${openapi.api.image.model}")
    private String modelId;


    public ImageChatGPTService(@Qualifier("imageRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public String generateImage(String prompt) {

        var request = new ImageGenerationRequest(
                modelId,
                prompt,
                1,
                "1024x1024"
        );

        ImageGenerationResponse response = restClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(ImageGenerationResponse.class);

        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            return response.getData().get(0).getB64_json(); // getUrl if use Dall-e
        }

        throw new RuntimeException("Không thể sinh ảnh từ ChatGPT.");
    }
}
