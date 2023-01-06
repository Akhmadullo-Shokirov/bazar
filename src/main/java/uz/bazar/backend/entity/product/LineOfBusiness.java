package uz.bazar.backend.entity.product;

import javax.persistence.*;
import java.util.List;

@Entity
public class LineOfBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "lineOfBusiness")
    private List<ProductLine> productLineList;

    public LineOfBusiness() {
    }

    public LineOfBusiness(String name) {
        this.name = name;
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

    public List<ProductLine> getProductLineList() {
        return productLineList;
    }

    public void setProductLineList(List<ProductLine> productLineList) {
        this.productLineList = productLineList;
    }
}
