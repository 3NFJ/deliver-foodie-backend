package com.nfjs.fooddelivery.ai.service;

import com.nfjs.fooddelivery.ai.dto.AIRequestDto;
import com.nfjs.fooddelivery.ai.dto.GeminiRequestDto;
import com.nfjs.fooddelivery.ai.dto.AIResponseDto;
import com.nfjs.fooddelivery.ai.entity.AI;
import com.nfjs.fooddelivery.ai.repository.AIRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIService {
    private final AIRepository aiRepository;
    private final UserRepository userRepository;

    @Value("${ai.url}")
    private String url;
    @Value("${ai.key}")
    private String key;

    private final RestTemplate restTemplate = new RestTemplate();


    @Transactional
    public AIResponseDto askQuestion(AIRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow();
        String question = requestDto.getQuestion();

        AIResponseDto body = callApi(GeminiRequestDto.convert(question));

        String answer = body.getCandidates().get(0).getContent().getParts().get(0).getText();

        aiRepository.save(AI.toEntity(answer, question, user));

        return body;
    }

    private AIResponseDto callApi(GeminiRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String urlWithKey = url + "?key=" + key;

        HttpEntity<GeminiRequestDto> request = new HttpEntity<>(requestDto, headers);

        return restTemplate.exchange(
                urlWithKey,
                HttpMethod.POST,
                request,
                AIResponseDto.class
        ).getBody();
    }
}
