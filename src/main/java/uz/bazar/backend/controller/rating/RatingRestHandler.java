package uz.bazar.backend.controller.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.entity.Rating;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.entity.product.Product;
import uz.bazar.backend.repository.product.ProductRepository;
import uz.bazar.backend.repository.product.RatingRepository;
import uz.bazar.backend.repository.user.UserRepository;
import uz.bazar.backend.service.rating.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingRestHandler {

    @Autowired
    RatingService ratingService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public String addRating(@RequestBody RatingService.RatingWrapper ratingWrapper){
        return ratingService.save(ratingWrapper);
    }

//    @GetMapping("/add")
//    public Rating addRating(){
//        Product product = new Product();
//        product.setDisplayName("Dell optiplex 1233");
//
//        User user = new User();
//
//        Rating rating = new Rating();
//        user.setRatings(rating);
//        userRepository.save(user);
//        rating.setRatingValue(4.5f);
//        rating.setProduct(product);
//        rating.setUser(user);
//        product.setRatings(rating);
//
//        productRepository.save(product);
//
//        return ratingRepository.save(rating);
//    }

}
