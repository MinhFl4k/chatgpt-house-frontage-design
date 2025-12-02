package com.springboot.chatgpt.service;

import com.springboot.chatgpt.dto.ChatGPTRequest;
import com.springboot.chatgpt.dto.ChatGPTResponse;
import com.springboot.chatgpt.dto.PromptRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ChatGPTService {

    private final RestClient restClient;

    public ChatGPTService(@Qualifier("chatRestClient") RestClient restClient){
        this.restClient = restClient;
    }

    @Value("${openapi.api.key}")
    private String apiKey;

    @Value("${openapi.api.model}")
    private String model;


    public String getChatResponse(PromptRequest promptRequest){

        String finalPrompt =
                "Hãy phân tích yêu cầu dưới đây và trả về kết quả theo đúng cấu trúc JSON cố định sau:\n" +
                        "{\n" +
                        "  \"request\": \"\",\n" +
                        "  \"style\": \"\",\n" +
                        "  \"mass\": \"\",\n" +
                        "  \"roof\": \"\",\n" +
                        "  \"windows_doors\": \"\",\n" +
                        "  \"colors\": \"\",\n" +
                        "  \"materials\": \"\",\n" +
                        "  \"lighting\": \"\",\n" +
                        "  \"landscape\": \"\",\n" +
                        "  \"gate_fence\": \"\",\n" +
                        "  \"highlights\": \"\"\n" +
                        "}\n\n" +
                        "YÊU CẦU:\n" +
                        "- CHỈ được trả về JSON OBJECT theo đúng cấu trúc trên.\n" +
                        "- Không được thêm hoặc bớt trường.\n" +
                        "- Không được trả lời bất kỳ nội dung nào ngoài JSON.\n" +
                        "- Mô tả ngắn gọn nhưng có chiều sâu.\n" +
                        "- Nếu thiếu dữ liệu, hãy tự suy luận hợp lý.\n\n" +
                        "Nội dung yêu cầu người dùng: " + promptRequest.prompt();

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(
                model,
                List.of(
                        new ChatGPTRequest.Message(
                                "system",
                                "Bạn là kiến trúc sư chuyên thiết kế mặt tiền nhà. Luôn trả về kết quả theo đúng cấu trúc JSON chuẩn, không giải thích, không bình luận ngoài JSON."
                        ),
                        new ChatGPTRequest.Message("user", finalPrompt)
                )
        );

        ChatGPTResponse response = restClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(chatGPTRequest)
                .retrieve()
                .body(ChatGPTResponse.class);

        return response.choices().get(0).message().content();

    }
}
