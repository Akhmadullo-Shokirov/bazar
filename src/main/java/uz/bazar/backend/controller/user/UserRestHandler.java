package uz.bazar.backend.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.repository.user.UserRepository;
import uz.bazar.backend.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestHandler {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String save(@RequestBody UserService.UserWrapper userWrapper){
        return userService.save(userWrapper);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/to_cart/add/{userId}/{productId}")
    public String addProductToCart(@PathVariable("userId") String userId, @PathVariable("productId") String productId){
        String response =  userService.addProductToCart(userId, productId);
        return response.isEmpty()?"Error":response;
    }
}
