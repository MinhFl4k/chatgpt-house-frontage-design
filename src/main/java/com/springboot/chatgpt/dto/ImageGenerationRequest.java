package com.springboot.chatgpt.dto;

public class ImageGenerationRequest {
    private String model;
    private String prompt;
    private int n;
    private String size;

    public ImageGenerationRequest(String model, String prompt, int n, String size) {
        this.model = model;
        this.prompt = prompt;
        this.n = n;
        this.size = size;
    }

    public String getModel() { return model; }
    public String getPrompt() { return prompt; }
    public int getN() { return n; }
    public String getSize() { return size; }
}