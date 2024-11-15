package com.nfjs.fooddelivery.ai.controller;

import com.nfjs.fooddelivery.ai.dto.AIRequestDto;
import com.nfjs.fooddelivery.ai.dto.AIResponseDto;
import com.nfjs.fooddelivery.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AIController {
    private final AIService aiService;

    @PostMapping("/ai")
    public ResponseEntity<AIResponseDto> askAQuestion(@RequestBody AIRequestDto requestDto) {
       AIResponseDto aiResponseDto = aiService.askQuestion(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(aiResponseDto);
    }
}
