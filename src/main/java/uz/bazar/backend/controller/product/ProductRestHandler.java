package uz.bazar.backend.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.bazar.backend.entity.ProductPhoto;
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

    // TODO to be deleted written just for test
    @Autowired
    ProductPhotoRepository productPhotoRepository;

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId){
        return productService.getById(productId);
    }
//    @GetMapping("/all")
//    public List<Product> productList(){
//        return (List)productRepository.findAll();
//    }

    @GetMapping("/recommended")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public String uploadProduct(@RequestBody ProductService.ProductWrapper product){
        System.out.println("Response:" + product);
        String savedProductId =  productService.save(product);
        return savedProductId;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("File name contains invalid path sequence" + fileName);
            }
            ProductPhoto productPhoto = new ProductPhoto(fileName,file.getContentType(), file.getBytes());
            productPhotoRepository.save(productPhoto);
        }catch(Exception e){

        }

        return "ok";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId){
        ProductPhoto productPhoto = productPhotoRepository.getReferenceById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(productPhoto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + productPhoto.getFileName()+"\"")
                .body(new ByteArrayResource(productPhoto.getData()));
    }
}










