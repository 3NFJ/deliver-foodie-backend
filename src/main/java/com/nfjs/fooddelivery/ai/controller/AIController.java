package com.nfjs.fooddelivery.ai.controller;

import com.nfjs.fooddelivery.ai.dto.AIRequestDto;
import com.nfjs.fooddelivery.ai.dto.AIResponseDto;
import com.nfjs.fooddelivery.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AIController {
    private final AIService aiService;

    @PostMapping("/ai")
    public ResponseEntity<AIResponseDto> askAQuestion(@RequestBody AIRequestDto aiRequestDto, @RequestParam Long userId) {
       AIResponseDto aiResponseDto = aiService.askQuestion(aiRequestDto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(aiResponseDto);
    }
}
