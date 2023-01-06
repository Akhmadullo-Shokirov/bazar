package uz.bazar.backend.controller.category;

import java.util.List;

class ProductLineObject {
    String name;
    String[] subcategories;

    public ProductLineObject() {
    }

    public ProductLineObject(String name, String[] subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(String[] subcategories) {
        this.subcategories = subcategories;
    }
}
