package uz.bazar.backend.repository.product;

import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.product.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
}
