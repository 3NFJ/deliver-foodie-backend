package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.menu.validation.MenuValidation;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuValidation menuValidation;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Override
    public MenuResponseDto addMenu(UUID shopId, MenuRequestDto requestDto) {
        //shop 유효성 검증
        Shop shop = shopRepository.findById(shopId).orElseThrow();

        menuValidation.addMenuValidation(requestDto, shopId);

        Menu entity = menuRepository.save(requestDto.toEntity(shop));

        return MenuResponseDto.from(entity);
    }

    @Override
    @Transactional
    public MenuResponseDto updateMenu(UUID menuId, MenuRequestDto requestDto) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new NullPointerException("메뉴가 존재하지 않습니다."));

        menuValidation.addMenuValidation(requestDto, requestDto.shopId());

        menu.update(requestDto);

        return MenuResponseDto.from(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(UUID menuId, Long userId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new NullPointerException(ErrorCode.MENU_NOT_FOUND.getMessage()));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(ErrorCode.USER_NOT_FOUND.getMessage()));

        if (!menu.getShop().getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("유저 불일치");
        }

        menu.delete(user.getUsername());
    }

    @Override
    public List<MenuResponseDto> getMenuList(UUID shopId, Pageable pageable) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        Page<Menu> menus = menuRepository.findAllByShop(shop, pageable);

        return menus.stream()
                .map(MenuResponseDto::from)
                .toList();
    }
}
