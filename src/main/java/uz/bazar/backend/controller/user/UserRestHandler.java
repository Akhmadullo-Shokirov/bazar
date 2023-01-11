package uz.bazar.backend.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.service.user.UserService;
import uz.bazar.backend.service.user.UserValidationService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestHandler {

    @Autowired
    UserService userService;

    @Autowired
    UserValidationService userValidationService;

    @PostMapping("/add")

    public String save(@RequestBody UserService.UserWrapper userWrapper) throws MessagingException, UnsupportedEncodingException {
        return userService.save(userWrapper);
    }
    
    @PostMapping("/login")
    public boolean login(@RequestBody UserValidationService.LoginUserWrapper loginUserWrapper) {
        return userValidationService.isUsernameAndPasswordValid(loginUserWrapper);
    }
    @GetMapping("/verify")
    public boolean verify(@RequestParam("code") String verificationCode) {
        System.out.println("THE VERIFICATION CODE: " + verificationCode);
        return userValidationService.verifyUser(verificationCode);

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