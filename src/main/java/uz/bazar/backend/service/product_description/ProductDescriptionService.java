package uz.bazar.backend.service.product_description;

import org.springframework.util.StringUtils;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.bazar.backend.entity.ProductPhoto;
import uz.bazar.backend.entity.product.ProductDescription;
import uz.bazar.backend.repository.ProductPhotoRepository;
import uz.bazar.backend.repository.product.ProductDescriptionRepository;

@Service
public class ProductDescriptionService {

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    @Autowired
    ProductPhotoRepository productPhotoRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ProductDescriptionWrapper{
        private String title;
        private String descriptionText;
    }

    public String save(ProductDescriptionWrapper productDescriptionWrapper, MultipartFile[] images){
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle(productDescriptionWrapper.getTitle());
        productDescription.setDescriptionText(productDescriptionWrapper.getDescriptionText());
        String savedProductDescriptionId = productDescriptionRepository.save(productDescription).getId();

        for(MultipartFile image: images){
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            try{
                if(fileName.contains("..")){
                    throw new Exception("File name contains invalid path sequence" + fileName);
                }
                ProductPhoto productPhoto = new ProductPhoto(fileName, image.getContentType(),image.getBytes());
                productPhotoRepository.save(productPhoto);
                productDescription.setProductPhoto(productPhoto);
            }catch (Exception e){

            }

        }
        productDescriptionRepository.save(productDescription);
        return savedProductDescriptionId;
    }

    public ProductPhoto getSingleProductPhoto(String productDescriptionId){
        ProductDescription productDescription = productDescriptionRepository.getReferenceById(productDescriptionId);
        return productPhotoRepository.getReferenceById(productDescription.getProductPhotos().get(0));
    }
}
