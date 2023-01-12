package uz.bazar.backend.service.token;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.Token;
import uz.bazar.backend.repository.token.TokenRepository;

import java.time.LocalDate;

@Service
public class APITokenValidationService {

    @Autowired
    TokenRepository tokenRepository;

    public void generateToken(String tokenName) {
        Token newToken = new Token();
        String randomToken = RandomString.make(50);
        newToken.setName(tokenName);
        newToken.setValue(randomToken);

//        set creation date, and calculate expiration date
        LocalDate todayLocalDate = LocalDate.now();
        newToken.setCreationDate(todayLocalDate);
//        Expiration date is set to 90 days after the token creation date
        newToken.setExpirationDate(newToken.getCreationDate().plusDays(90));
        tokenRepository.save(newToken);
    }

    public boolean verifyToken(String requestToken) {
        Token tokenToCheck = tokenRepository.findByTokenValue(requestToken);
        if ( tokenToCheck != null) {
            if (LocalDate.now().isAfter(tokenToCheck.getExpirationDate())) {
                System.out.println("This token was expired. Please generate a new token");
                return false;
            } else {
                System.out.println("Token verified successfully");
                return true;
            }
        }
        System.out.println("Invalid token provided");
        return false;
    }
}
