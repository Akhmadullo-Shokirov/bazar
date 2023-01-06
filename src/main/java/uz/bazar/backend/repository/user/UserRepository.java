package uz.bazar.backend.repository.user;

import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.User;

public interface UserRepository extends CrudRepository<User,String> {

}
