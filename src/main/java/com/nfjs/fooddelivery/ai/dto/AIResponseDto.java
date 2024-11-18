package com.nfjs.fooddelivery.ai.dto;

import com.nfjs.fooddelivery.ai.entity.AI;
import lombok.Getter;

import java.util.List;

@Getter
public class AIResponseDto {
    private List<Candidate> candidates;

    @Getter
    public static class Candidate {
        private Content content;
    }

    @Getter
    public static class Content {
        private List<Part> parts;
    }

    @Getter
    public static class Part {
        private String text;
    }

}
