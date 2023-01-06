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
}
