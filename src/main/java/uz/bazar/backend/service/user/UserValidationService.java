package uz.bazar.backend.service.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.repository.user.UserRepository;

@Service
@Slf4j
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

    public String isUsernameAndPasswordValid(LoginUserWrapper loginUserWrapper) {
        String loginUsernameOrEmail = loginUserWrapper.getUsernameOrEmail();
        String loginUserPassword = loginUserWrapper.getPassword();
        String userId = isUsernameOrEmailExist(loginUsernameOrEmail);
        if (userId != null) return isPasswordValid(loginUserPassword, userId);

        return "There's no account with username or email";
    }

    private String isUsernameOrEmailExist(String loginUsernameOrEmail) {
        String userId;
        //        Checks whether login input is username or email
        if (checkForUsernameOrEmail(loginUsernameOrEmail).equals("email")) {
            userId = userRepository.findByEmail(loginUsernameOrEmail);
        } else {
            userId = userRepository.findByUsername(loginUsernameOrEmail);
        }
        return userId;
    }

    private String isPasswordValid(String loginPassword, String userId) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userToCheck = userRepository.findById(userId).get();
        if(passwordEncoder.matches(loginPassword, userToCheck.getPassword()))
            return userId;

        return "Username/email address or password is incorrect";
    }

    private String checkForUsernameOrEmail(String usernameOrEmail) {
        if (usernameOrEmail.contains("@")) return "email";

        return "username";
    }

    public boolean verifyUser(String verificationCode) {
        User unverifiedUser = userRepository.findByVerificationCode(verificationCode);

        if (unverifiedUser == null || unverifiedUser.isActive()) {
            log.debug("This user doesn't exist or already verified");
            return false;
        } else {
//              User is verified, account active status will be set to TRUE
            unverifiedUser.setVerificationCode(null);
            unverifiedUser.setActive(true);
            userRepository.save(unverifiedUser);
            log.debug("User is verified: " + unverifiedUser.getFirstName());
            return true;
        }
    }
}