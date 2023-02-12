package uz.bazar.backend.entity.product;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
public class ProductLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "productLine")
    private List<SubCategory> subCategoryList;
    @ManyToOne
    @JoinColumn(name="line_of_business_id")
    private LineOfBusiness lineOfBusiness;

    public ProductLine(String name) {
        this.name = name;
    }

    public ProductLine() {
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        log.info(subCategoryList.toString());
        if(subCategoryList == null){
            this.subCategoryList = new ArrayList<>();
        }
        this.subCategoryList = subCategoryList;
    }

//    public LineOfBusiness getLineOfBusiness() {
//        return lineOfBusiness;
//    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setLineOfBusiness(LineOfBusiness lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
}
