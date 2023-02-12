package uz.bazar.backend.service.product;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.Rating;
import uz.bazar.backend.entity.User;
import uz.bazar.backend.entity.product.Product;
import uz.bazar.backend.entity.product.ProductDescription;
import uz.bazar.backend.entity.product.SubCategory;
import uz.bazar.backend.repository.product.ProductDescriptionRepository;
import uz.bazar.backend.repository.product.ProductRepository;
import uz.bazar.backend.repository.product.RatingRepository;
import uz.bazar.backend.repository.product.SubCategoryRepository;
import uz.bazar.backend.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ProductWrapper{
        private String displayName;
        private float price;
        private int leftInStock;
        private String ownerId;
        private Long subCategoryId;
        private String productDescriptionId;
    }

    public String save(ProductWrapper productWrapper){
        Product productToSave = new Product();
        productToSave.setDisplayName(productWrapper.getDisplayName());
        productToSave.setPrice(productWrapper.getPrice());
        productToSave.setLeftInStock(productWrapper.getLeftInStock());
        log.info(productWrapper.getOwnerId());
        User user = userRepository.findById(productWrapper.getOwnerId()).get();
        productToSave.setOwner(user);

        SubCategory subCategory = subCategoryRepository.findById(productWrapper.getSubCategoryId()).get();
        productToSave.setSubCategory(subCategory);

        log.info(productWrapper.getProductDescriptionId());
        ProductDescription productDescription = productDescriptionRepository.findById(productWrapper.getProductDescriptionId()).get();
        productToSave.setProductDescription(productDescription);

        String savedProductId = productRepository.save(productToSave).getId();

        return savedProductId;
    }

    public Product getById(String productId){
        return productRepository.findById(productId).get();
    }
    public List<Product> getAllProducts(){
        return ((List<Product>)productRepository.findAll());
    }

    public List<Product> getProductsByCategoryId(Long subcategoryId){
        List<Product> productList = productRepository.findBySubCategoryId(subcategoryId);
        return productList;
    }

    public List<Product> getProductsByDisplayName(String displayName) {
        List<Product> productList = productRepository.findByDisplayName(displayName);
        return productList;
    }
}


