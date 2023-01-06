package uz.bazar.backend.repository.product;

import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.product.LineOfBusiness;

public interface LineOfBusinessRepository extends CrudRepository<LineOfBusiness, Long> {
}
