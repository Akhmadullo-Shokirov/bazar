package uz.bazar.backend.entity.product;

import org.hibernate.annotations.GenericGenerator;
import uz.bazar.backend.entity.ProductPhoto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ProductDescription {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    private String title;
    @Lob
    private String descriptionText;
    @OneToOne
    private Product product;

    @OneToMany
    @JoinColumn(name="product_description_id")
    private List<ProductPhoto> productPhotos;


    // Todo discount percentage
    // Todo company name field
    //


    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductPhoto(ProductPhoto productPhoto) {
        if(this.productPhotos == null){
            this.productPhotos = new ArrayList<>();
        }
        productPhotos.add(productPhoto);
    }

    public List<String> getProductPhotos() {
        return productPhotos.stream().map(productPhoto-> productPhoto.getId()).collect(Collectors.toList());
    }
}
