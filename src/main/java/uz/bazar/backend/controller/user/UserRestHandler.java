package uz.bazar.backend.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.service.user.UserService;
import uz.bazar.backend.service.user.UserValidationService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestHandler {

    @Autowired
    UserService userService;

    @Autowired
    UserValidationService userValidationService;

    @PostMapping("/add")
    public String save(@RequestBody UserService.UserWrapper userWrapper){
        return userService.save(userWrapper);
    }
    @PostMapping("/login")
    public boolean login(@RequestBody UserValidationService.LoginUserWrapper loginUserWrapper) {
        return userValidationService.isUsernameAndPasswordValid(loginUserWrapper);
    }
}
