package uz.bazar.backend.service.rating;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.Rating;
import uz.bazar.backend.repository.product.ProductRepository;
import uz.bazar.backend.repository.product.RatingRepository;
import uz.bazar.backend.repository.user.UserRepository;


/*
{
  "ratingValue": 4.5,
  "user": "5348a168-5dfa-4ad5-9dba-98e74dcaa0d4",
  "product": "0aaa3d0e-dee2-4f06-9176-bc5fbfd6c0c9"
}
*/
@Service
public class RatingService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class RatingWrapper{
        private float ratingValue;
        private String user;
        private String product;

    }

    public String save(RatingWrapper ratingWrapper){
        Rating ratingToSave = new Rating();
        ratingToSave.setRatingValue(ratingWrapper.getRatingValue());
        ratingToSave.setUser(userRepository.findById(ratingWrapper.getUser()).get());
        ratingToSave.setProduct(productRepository.findById(ratingWrapper.getProduct()).get());
        String savedRatingId = ratingRepository.save(ratingToSave).getId();

        return savedRatingId;
    }

}
