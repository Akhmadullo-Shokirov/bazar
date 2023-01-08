package uz.bazar.backend.service.user;

import lombok.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.repository.user.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


/*
{
  "firstName": "Nematullo",
  "lastName": "Kozimov",
  "email": "koznem24@gmail.com",
  "phoneNumber": "+48 789 215 805",
  "password":"Nothing"
}
*/
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender mailSender;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class UserWrapper{
        private String firstName;
        private String lastName;
        private String email;
        private String username;
        private String phoneNumber;
        private String password;
    }

    public String save(UserWrapper userWrapper) throws MessagingException, UnsupportedEncodingException {
        if (checkIfEmailExist(userWrapper.getEmail())) {
            return "This email address already exists";
        } else if (checkIfUsernameExist(userWrapper.getUsername())) {
            return "This username already exists";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userToSave = new User();
        userToSave.setFirstName(userWrapper.getFirstName());
        userToSave.setLastName(userWrapper.getLastName());
        userToSave.setUsername(userWrapper.getUsername());
        // TODO email verification
        userToSave.setEmail(userWrapper.getEmail());
        // TODO phoneNumber verification
        userToSave.setPhoneNumber(userWrapper.getPhoneNumber());
        // TODO password encryption and save -> DONE
        userToSave.setPassword(passwordEncoder.encode(userWrapper.getPassword()));

        String randomCode = RandomString.make(64);
        userToSave.setVerificationCode(randomCode);
        userToSave.setActive(false);
        String newUserId = userRepository.save(userToSave).getId();
        sendVerificationMail(userToSave);
        return newUserId;
    }

    private void sendVerificationMail(User user) throws
            MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "ahmadillo0610@gmail.com";
        String senderName = "Bazar Uz";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Bazar uz.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = "http://localhost:3000/user/add/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        mailSender.send(message);
    }

    private boolean checkIfEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean checkIfUsernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
