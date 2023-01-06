package uz.bazar.backend.controller.category;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

class LineOfBusiness {
    String LineOfBusiness;
    List<ProductLineObject> ProductLine;

    public LineOfBusiness() {
    }

    public LineOfBusiness(String LineOfBusiness, List<ProductLineObject> ProductLine) {
        this.LineOfBusiness = LineOfBusiness;
        this.ProductLine = ProductLine;
    }

    public String getLineOfBusiness() {
        return LineOfBusiness;
    }

    public void setLineOfBusiness(String LineOfBusiness) {
        this.LineOfBusiness = LineOfBusiness;
    }

    public List<ProductLineObject> getProductLine() {
        return ProductLine;
    }

    public void setProductLine(List<ProductLineObject> ProductLine) {
        this.ProductLine = ProductLine;
    }
}
