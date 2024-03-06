package com.mjmj.review.api;

import com.mjmj.review.api.request.CreateAndEditRestaurantRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RestaurantApi {

    @GetMapping("/restaurants")
    public String getRestaurants(){
        return "This is getRestaurants";
    }


    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(
            @PathVariable Long restaurantId
    ){
        return "This is getRestaurant" + restaurantId;
    }


    @PostMapping("/restaurant")
    public String createRestaurant(
            @RequestBody CreateAndEditRestaurantRequest request
            ){
        return "This is createRestaurant " + request.getName() + " " + request.getAddress()
                + request.getMenus().get(0).getName() + " " + request.getMenus().get(0).getPrice();
    }


    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody CreateAndEditRestaurantRequest request
    ){
        return "This is editRestaurant" + request.getName() + " " + request.getAddress();
    }


    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(
            @PathVariable Long restaurantId
    ){
        return "This is deleteRestaurant" + restaurantId;
    }
}
