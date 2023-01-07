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

    public boolean isUsernameAndPasswordValid(LoginUserWrapper loginUserWrapper) {
        String loginUsernameOrEmail = loginUserWrapper.getUsernameOrEmail();
        String loginUserPassword = loginUserWrapper.getPassword();
        String userId = isUsernameOrEmailExist(loginUsernameOrEmail);
        if (userId != null) return isPasswordValid(loginUserPassword, userId);

        return false;
    }

    public String isUsernameOrEmailExist(String loginUsernameOrEmail) {
        String userId;
        //        Checks whether login input is username or email
        if (checkForUsernameOrEmail(loginUsernameOrEmail).equals("email")) {
            userId = userRepository.findByEmail(loginUsernameOrEmail);
        } else {
            userId = userRepository.findByUsername(loginUsernameOrEmail);
        }
        return userId;
    }

    public boolean isPasswordValid(String loginPassword, String userId) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userToCheck = userRepository.findById(userId).get();
        return passwordEncoder.matches(loginPassword, userToCheck.getPassword());
    }

    public String checkForUsernameOrEmail(String usernameOrEmail) {
        if (usernameOrEmail.contains("@")) return "email";

        return "username";
    }


}
