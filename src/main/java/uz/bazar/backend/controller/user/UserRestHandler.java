package uz.bazar.backend.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.service.token.APITokenValidationService;
import uz.bazar.backend.service.user.UserService;
import uz.bazar.backend.service.user.UserValidationService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestHandler {

    @Autowired
    UserService userService;

    @Autowired
    UserValidationService userValidationService;

    @Autowired
    APITokenValidationService tokenValidationService;

    @PostMapping("/add")
    public String save(@RequestBody UserService.UserWrapper userWrapper) throws MessagingException, UnsupportedEncodingException {
        tokenValidationService.generateToken("frontend token");
        return userService.save(userWrapper);
    }
    
    @PostMapping("/login")
    public boolean login(@RequestHeader(HttpHeaders.AUTHORIZATION) String postRequestToken,
                         @RequestBody UserValidationService.LoginUserWrapper loginUserWrapper) {
        if (tokenValidationService.verifyToken(postRequestToken.substring(7))) {
            return userValidationService.isUsernameAndPasswordValid(loginUserWrapper);
        }

        return false;
    }

    @GetMapping("/verify")
    public boolean verify(@RequestParam("code") String verificationCode) {
        System.out.println("THE VERIFICATION CODE: " + verificationCode);
        return userValidationService.verifyUser(verificationCode);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/to_cart/add/{userId}/{productId}")
    public String addProductToCart(@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
        String response = userService.addProductToCart(userId, productId);
        return response.isEmpty() ? "Error" : response;
    }

    @DeleteMapping("/to_cart/remove/{userId}/{productId}")
    public String removeProductFromCart(@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
        String returnedUserId = userService.removeProductFromCart(userId,productId);
        return returnedUserId.isEmpty()?"Error":returnedUserId;
    }
}