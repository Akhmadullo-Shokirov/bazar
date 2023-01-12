package uz.bazar.backend.repository.token;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uz.bazar.backend.entity.Token;

public interface TokenRepository extends CrudRepository<Token, String> {

    @Query(value = "SELECT * FROM test.token t WHERE t.value = ?1", nativeQuery = true)
    Token findByTokenValue(String requestTokenValue);
}
