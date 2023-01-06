package uz.bazar.backend.entity.product;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import uz.bazar.backend.entity.Rating;
import uz.bazar.backend.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String displayName;
    private double price;
    private int leftInStock;

    // Todo adding inCart boolean variable  --elaboration needed #conclusion: to be done in frontend


 // separate post request
    @ManyToMany
    @JoinTable(
            name="user_product_in_cart",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    @Column(nullable = true)
    private List<User> inCartOf; // better name needed? what would that be? No; this looks enough

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    // Todo adding rating
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductDescription productDescription;

    public Product(String displayName, double price, int leftInStock) {
        this.displayName = displayName;
        this.price = price;
        this.leftInStock = leftInStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLeftInStock() {
        return leftInStock;
    }

    public void setLeftInStock(int leftInStock) {
        this.leftInStock = leftInStock;
    }

    public List<String> getInCartOf() {
        if(inCartOf != null)
            return inCartOf.stream().map(user -> user.getId()).collect(Collectors.toList());

        return null;
    }

    public void setInCartOf(List<User> inCartOf) {
        this.inCartOf = inCartOf;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getSubCategory() {
        return subCategory.getId();
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }


    public String getProductDescription() {
        if(productDescription !=null )
            return productDescription.getId();
        else return null;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
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
        return "Product{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", price=" + price +
                ", quantity=" + leftInStock +
                ", inCartOf=" + inCartOf +
                ", owner=" + owner +
                ", subCategory=" + subCategory +
                '}';
    }
}
