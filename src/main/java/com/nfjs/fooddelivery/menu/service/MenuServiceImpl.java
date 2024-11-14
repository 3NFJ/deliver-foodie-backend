package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.menu.validation.MenuValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuValidation menuValidation;

    @Override
    public MenuResponseDto addMenu(UUID shopId, MenuRequestDto requestDto) {
        //shop 유효성 검증

        menuValidation.addMenuValidation(requestDto, shopId);

        Menu entity = menuRepository.save(requestDto.toEntity(shopId));

        return MenuResponseDto.from(entity);
    }
}
