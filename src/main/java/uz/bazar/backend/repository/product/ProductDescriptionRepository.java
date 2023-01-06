package uz.bazar.backend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bazar.backend.entity.product.ProductDescription;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, String> {
}
