package uz.bazar.backend.entity;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;
import uz.bazar.backend.entity.product.Product;

import javax.persistence.*;

/* Rating update is going to happen on front-end*/
@Entity
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2") // TODO maybe change generation type for performance? check if needed
    @Column(name = "rating_id")
    private String id;
    private float ratingValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Rating(float ratingValue, User user, Product product) {
        this.ratingValue = ratingValue;
        this.user = user;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getUser() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProduct() {
        return product.getId();
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
