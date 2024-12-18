package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.common.excetpion.MenuException;
import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.menu.validation.MenuValidation;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.nfjs.fooddelivery.common.excetpion.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuValidation menuValidation;
    private final ShopRepository shopRepository;


    @Override
    public MenuResponseDto addMenu(UUID shopId, MenuAddRequestDto requestDto, User user) {
        //shop 유효성 검증
        Shop shop = shopRepository.findById(shopId).orElseThrow();

        menuValidation.addMenuValidation(requestDto, shopId, user);

        Menu entity = menuRepository.save(requestDto.toEntity(shop));

        return MenuResponseDto.from(entity);
    }

    @Override
    @Transactional
    public MenuResponseDto updateMenu(UUID menuId, MenuUpdateRequestDto requestDto, User user) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        menuValidation.updateValidation(requestDto, user);

        menu.update(requestDto);

        return MenuResponseDto.from(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(UUID menuId, User user) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

        if (!menu.getShop().getUser().getUserId().equals(user.getUserId())) {
            throw new MenuException(SHOP_OWNER_MISMATCH);
        }

        menu.delete(user.getUsername());
    }

    @Override
    public Page<MenuResponseDto> getMenuList(UUID shopId, Pageable pageable) {
        //todo shop 브랜치 병합 시 예외 처리
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        Page<Menu> menus = menuRepository.findAllByShop(shop, pageable);

        return menus.map(MenuResponseDto::from);
    }
}
