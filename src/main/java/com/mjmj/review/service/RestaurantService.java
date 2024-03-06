package com.mjmj.review.service;

import com.mjmj.review.api.request.CreateAndEditRestaurantRequest;
import com.mjmj.review.model.MenuEntity;
import com.mjmj.review.model.RestaurantEntity;
import com.mjmj.review.repository.MenuRepository;
import com.mjmj.review.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public void createRestaurant(
            CreateAndEditRestaurantRequest request
    ){
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        restaurantRepository.save(restaurant);

        request.getMenus().forEach(createAndEditRestaurantRequestMenu -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(createAndEditRestaurantRequestMenu.getName())
                    .price(createAndEditRestaurantRequestMenu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

    }

    @Transactional
    public void editRestaurant(
            Long restaurantId,
            CreateAndEditRestaurantRequest request
    ){
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new RuntimeException("없는 아이디"));
        restaurant.changeNameAndAddress(request.getName(), request.getAddress());
        restaurantRepository.save(restaurant);


        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach(createAndEditRestaurantRequestMenu -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantId)
                    .name(createAndEditRestaurantRequestMenu.getName())
                    .price(createAndEditRestaurantRequestMenu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });
    }

    @Transactional
    public void deleteRestaurant(Long restaurantId){
            RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
            restaurantRepository.delete(restaurant);

            List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
            menuRepository.deleteAll(menus);
    }

}
