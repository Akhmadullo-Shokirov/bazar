package uz.bazar.backend.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.User;

public interface UserRepository extends CrudRepository<User,String> {

    @Query(value = "SELECT id FROM user WHERE email = ?1", nativeQuery = true)
    String findByEmail(String email);

    @Query(value = "SELECT id FROM user WHERE username = ?1", nativeQuery = true)
    String findByUsername(String username);
    
    @Query(value = "SELECT * FROM user WHERE verification_code = ?1", nativeQuery = true)
    User findByVerificationCode(String code);
}
