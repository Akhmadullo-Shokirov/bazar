package uz.bazar.backend.controller.product_description;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.bazar.backend.entity.ProductPhoto;
import uz.bazar.backend.entity.product.ProductDescription;
import uz.bazar.backend.service.product_description.ProductDescriptionService;

@RestController
@RequestMapping("/product_description")
public class ProductDescriptionRestController {

    @Autowired
    ProductDescriptionService productDescriptionService;

    @CrossOrigin
    @PostMapping("/add")
    public String saveDescription(@RequestParam String title, @RequestParam String descriptionText, @RequestParam MultipartFile[] images){
        ProductDescriptionService.ProductDescriptionWrapper  productDescriptionWrapper = new ProductDescriptionService.ProductDescriptionWrapper(title, descriptionText);
        return productDescriptionService.save(productDescriptionWrapper, images);
    }

    @CrossOrigin
    @GetMapping("/photo/{productDescriptionId}")
    public ResponseEntity<Resource>  getSinglePhotoOfProduct(@PathVariable("productDescriptionId") String productDescriptionId){
        ProductPhoto productPhoto  = productDescriptionService.getSingleProductPhoto(productDescriptionId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(productPhoto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + productPhoto.getFileName()+"\"")
                .body(new ByteArrayResource(productPhoto.getData()));
    }

}
