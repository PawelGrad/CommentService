package photoGallery.microservices;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.PhotoFile.PhotoFileDTO;
import photoGallery.model.photoComment.PhotoCommentDTO;



@FeignClient(name = "photoFile-service")
public interface FeignClientPhotoService {

    @GetMapping("/photo/{id}")
    PhotoFile getPhoto(@PathVariable("id") String id);

    @PostMapping(value="/uploadFile", consumes = "multipart/form-data" )
    Response postPhoto(@RequestPart("file") MultipartFile file);

    @DeleteMapping("/photo/delete/{id}")
    Response deletePhoto(@PathVariable String id);

    @PutMapping(value = "/photo/update", consumes =  "application/json")
    Response updatePhoto(@RequestPart("file") PhotoFile file);

    @GetMapping("/photo/getEntity/{id}")
    PhotoFile getPhotoEntity(@PathVariable Long id);

    @GetMapping("/photo/getDTO/{id}")
    PhotoFileDTO getPhotoDTO(@PathVariable Long id);


}
