package uz.bazar.backend.repository.product;

import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.product.ProductLine;

public interface ProductLineRepository extends CrudRepository<ProductLine, Long> {
}
