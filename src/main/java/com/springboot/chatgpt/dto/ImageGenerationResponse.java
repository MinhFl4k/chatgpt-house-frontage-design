package com.springboot.chatgpt.dto;

import java.util.List;

public class ImageGenerationResponse {
    private List<ImageData> data;

    public static class ImageData {
//        private String url; // dall-e
//
//        public String getUrl() { return url; }
//        public void setUrl(String url) { this.url = url; }

        private String b64_json;   // gpt-image-1

        public String getB64_json() { return b64_json; }
        public void setB64_json(String b64_json) { this.b64_json = b64_json; }
    }

    public List<ImageData> getData() { return data; }
    public void setData(List<ImageData> data) { this.data = data; }
}
