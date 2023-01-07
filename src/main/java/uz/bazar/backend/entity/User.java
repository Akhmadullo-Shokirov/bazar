package uz.bazar.backend.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import uz.bazar.backend.entity.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Component
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    private String firstName;
    private String lastName;

    private String username;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @ManyToMany
    @JoinTable(
            name = "user_product_in_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> productsInCart;

    @OneToMany(mappedBy = "owner")
    private List<Product> productsUserIsSelling;

    public User() {
    }

    public User(String firstName, String lastName, String username, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getProductsInCart() {
        return productsInCart.stream().map(product->product.getId()).collect(Collectors.toList());
    }

    public void setProductsInCart(List<Product> productsInCart) {
        if(this.productsInCart == null){
            productsInCart = new ArrayList<>();
        }
        this.productsInCart.addAll(productsInCart);
    }
    // this will return ID of products
    public List<String> getProductsUserIsSelling() {
        return productsUserIsSelling.stream().map(product->product.getId()).collect(Collectors.toList());
    }

    public void setProductsUserIsSelling(List<Product> productsUserIsSelling) {
        if(this.productsUserIsSelling == null){
            this.productsUserIsSelling = new ArrayList<>();
        }
        this.productsUserIsSelling.addAll(productsUserIsSelling);
    }

    public void setProductsUserIsSelling(Product product){
        if(this.productsUserIsSelling == null){
            this.productsUserIsSelling = new ArrayList<>();
        }
        this.productsUserIsSelling.add(product);
    }

    public List<String> getRatings() {
        return ratings.stream().map(rating->rating.getId()).collect(Collectors.toList());
    }

    public void setRatings(Rating rating) {
       if(this.ratings == null){
           this.ratings = new ArrayList<>();
       }

       this.ratings.add(rating);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
