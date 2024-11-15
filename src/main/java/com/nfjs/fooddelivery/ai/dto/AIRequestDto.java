package com.nfjs.fooddelivery.ai.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AIRequestDto {
    private List<Content> contents;

    @Getter
    public static class Content {
        private List<Part> parts;
    }

    @Getter
    public static class Part {
        private String text;
    }
}

