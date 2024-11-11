package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.menu.validation.MenuValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuValidation menuValidation;

    @Override
    public MenuResponseDto addMenu(UUID shopId, MenuRequestDto requestDto) {
        //shop 유효성 검증

        String menuName = requestDto.menuName();
        int menuPrice = requestDto.menuPrice();

        menuValidation.addMenuValidation(menuName, menuPrice);

        Menu entity = menuRepository.save(requestDto.toEntity());

        return MenuResponseDto.from(entity);
    }
}
