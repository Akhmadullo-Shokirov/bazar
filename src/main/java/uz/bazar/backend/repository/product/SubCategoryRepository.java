package uz.bazar.backend.repository.product;

import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.product.SubCategory;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
}
