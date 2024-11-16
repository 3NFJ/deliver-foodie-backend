package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.menu.validation.MenuValidation;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.nfjs.fooddelivery.common.excetpion.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuValidation menuValidation;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;


    @Override
    public MenuResponseDto addMenu(UUID shopId, MenuAddRequestDto requestDto) {
        //shop 유효성 검증
        Shop shop = shopRepository.findById(shopId).orElseThrow();

        menuValidation.addMenuValidation(requestDto, shopId);

        Menu entity = menuRepository.save(requestDto.toEntity(shop));

        return MenuResponseDto.from(entity);
    }

    @Override
    @Transactional
    public MenuResponseDto updateMenu(UUID menuId, MenuUpdateRequestDto requestDto) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new NullPointerException("메뉴가 존재하지 않습니다."));
        User user = userRepository.findById(requestDto.userId()).orElseThrow(() -> new IllegalStateException(USER_NOT_FOUND.getMessage()));

        menuValidation.updateValidation(requestDto, user);

        menu.update(requestDto);

        return MenuResponseDto.from(menu);
    }
}
