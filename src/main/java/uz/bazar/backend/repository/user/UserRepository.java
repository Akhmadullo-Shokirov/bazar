package uz.bazar.backend.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.User;

public interface UserRepository extends CrudRepository<User,String> {

    @Query(value = "SELECT id FROM test.user email WHERE email = ?1", nativeQuery = true)
    String findByEmail(String email);

    @Query(value = "SELECT id FROM test.user username WHERE username = ?1", nativeQuery = true)
    String findByUsername(String username);
    
    @Query(value = "SELECT * FROM test.user u WHERE u.verification_code = ?1", nativeQuery = true)
    User findByVerificationCode(String code);
}
