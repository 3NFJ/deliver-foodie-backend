package com.nfjs.fooddelivery.ai.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class GeminiRequestDto {
    private List<Content> contents;

    @Getter
    @Builder
    public static class Content {
        private List<Part> parts;
    }

    @Getter
    @Builder
    public static class Part {
        private String text;
    }

    public static GeminiRequestDto convert(String question) {
        Part part = Part.builder()
                .text(question)
                .build();

        Content content = Content.builder()
                .parts(new ArrayList<>(List.of(part)))
                .build();

        return GeminiRequestDto.builder()
                .contents(new ArrayList<>(List.of(content)))
                .build();
    }
}

