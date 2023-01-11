package uz.bazar.backend.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.entity.product.Product;
import uz.bazar.backend.repository.ProductPhotoRepository;
import uz.bazar.backend.service.product.ProductService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductRestHandler {

    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId){
        return productService.getById(productId);
    }

    @GetMapping("/recommended")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public String uploadProduct(@RequestBody ProductService.ProductWrapper product){
        String savedProductId =  productService.save(product);
        return savedProductId;
    }

    @GetMapping("/by_subcategory_id/{subcategoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable("subcategoryId") Long subcategoryId){
        return productService.getProductsByCategoryId(subcategoryId);
    }
}










