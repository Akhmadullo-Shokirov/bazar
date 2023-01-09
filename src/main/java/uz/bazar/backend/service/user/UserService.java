package uz.bazar.backend.service.user;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.entity.product.Product;
import uz.bazar.backend.repository.product.ProductRepository;
import uz.bazar.backend.repository.user.UserRepository;

import java.util.Optional;


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
    ProductRepository productRepository;

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
        User userToSave = new User();
        userToSave.setFirstName(userWrapper.getFirstName());
        userToSave.setLastName(userWrapper.getLastName());
        userToSave.setUsername(userWrapper.getUsername());
        // TODO email verification
        userToSave.setEmail(userWrapper.getEmail());
        // TODO phoneNumber verification - maybe to be delayed
        userToSave.setPhoneNumber(userWrapper.getPhoneNumber());
        // TODO password encription and save

        String savedUserId = userRepository.save(userToSave).getId();

        return savedUserId;

    }

    public String addProductToCart(String userId, String productId){
        Optional<User> optionalUser= userRepository.findById(userId);
        User user = optionalUser.isPresent()?optionalUser.get():null;

        if(user != null){
            Optional<Product> optionalProduct =  productRepository.findById(productId);
            if(optionalProduct.isPresent()){
                user.setProductsInCart(optionalProduct.get());
                userRepository.save(user);
                return userId;
            }else{
                return "";
            }
        }else{
            return "";
        }
    }

    public User getUser(String userId){
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }
}
