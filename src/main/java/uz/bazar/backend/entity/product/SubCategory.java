package uz.bazar.backend.entity.product;

import javax.persistence.*;
import java.util.List;

@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subCategory")
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name="product_line_id")
    private ProductLine productLine;

    public SubCategory(String name) {
        this.name = name;
    }

    public SubCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

//    public ProductLine getProductLine() {
//        return productLine;
//    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }
}
