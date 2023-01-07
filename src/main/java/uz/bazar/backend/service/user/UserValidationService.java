package uz.bazar.backend.service.user;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.repository.user.UserRepository;

@Service
public class UserValidationService {
    @Autowired
    UserRepository userRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class LoginUserWrapper {

        private String usernameOrEmail;
        private String password;
    }

    public boolean isPasswordValid(LoginUserWrapper loginUserWrapper) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userToCheck = userRepository.findById("5a46ea3e-7f25-4445-b87a-aecf1dafec87").get();
        return passwordEncoder.matches(loginUserWrapper.getPassword(),
                userToCheck.getPassword());
    }


}
