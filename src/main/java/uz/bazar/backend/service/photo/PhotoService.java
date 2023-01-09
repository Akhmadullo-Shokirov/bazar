package uz.bazar.backend.service.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.bazar.backend.entity.ProductPhoto;
import uz.bazar.backend.repository.ProductPhotoRepository;

@Service
public class PhotoService {

    @Autowired
    ProductPhotoRepository productPhotoRepository;


    public ProductPhoto getPhoto(String photoId){
        ProductPhoto productPhoto = productPhotoRepository.getReferenceById(photoId);
        return productPhoto;
    }
}
