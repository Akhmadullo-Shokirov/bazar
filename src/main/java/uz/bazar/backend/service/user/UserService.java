package uz.bazar.backend.service.user;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.repository.user.UserRepository;


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

    public String save(UserWrapper userWrapper){
        if (checkIfEmailExist(userWrapper.getEmail())) {
            return "This email address already exists";
        } else if (checkIfUsernameExist(userWrapper.getUsername())) {
            return "This username already exists";
        }

        User userToSave = new User();
        userToSave.setFirstName(userWrapper.getFirstName());
        userToSave.setLastName(userWrapper.getLastName());
        userToSave.setUsername(userWrapper.getUsername());
        // TODO email verification
        userToSave.setEmail(userWrapper.getEmail());
        // TODO phoneNumber verification
        userToSave.setPhoneNumber(userWrapper.getPhoneNumber());
        // TODO password encryption and save
        userToSave.setPassword(userWrapper.getPassword());

        return userRepository.save(userToSave).getId();
    }

    public boolean checkIfEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean checkIfUsernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
