package uz.bazar.backend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bazar.backend.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {
}
