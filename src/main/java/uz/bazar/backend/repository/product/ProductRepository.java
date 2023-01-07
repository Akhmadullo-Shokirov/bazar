package uz.bazar.backend.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.product.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {
    @Query(value = "SELECT * FROM product WHERE sub_category_id = ?1", nativeQuery = true)
    List<Product> findBySubCategoryId(Long sub_category_id);
}
