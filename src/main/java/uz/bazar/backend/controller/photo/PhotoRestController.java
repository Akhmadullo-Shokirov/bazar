package uz.bazar.backend.controller.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bazar.backend.entity.ProductPhoto;
import uz.bazar.backend.service.photo.PhotoService;

@RestController
@CrossOrigin
@RequestMapping("/photo")
public class PhotoRestController {

    @Autowired
    PhotoService photoService;

    @GetMapping("/{photoId}")
    public ResponseEntity<Resource> getPhoto(@PathVariable("photoId") String photoId){
        ProductPhoto productPhoto = photoService.getPhoto(photoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(productPhoto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + productPhoto.getFileName()+"\"")
                .body(new ByteArrayResource(productPhoto.getData()));
    }

}
