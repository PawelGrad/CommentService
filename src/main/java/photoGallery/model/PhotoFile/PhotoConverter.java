package photoGallery.model.PhotoFile;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PhotoConverter {

    public PhotoFileDTO convertToDTO(PhotoFile photofile){
        PhotoFileDTO photoDTO = new PhotoFileDTO();
        photoDTO.setId(photofile.getId());
        photoDTO.setData(photoToString(photofile.getData()));
        photoDTO.setRating(photofile.getRating());
        photoDTO.setFileName(photofile.getFileName());
        photoDTO.setFileType(photofile.getFileType());
        photoDTO.setVoteCounter(photofile.getVoteCounter());
        photoDTO.setComments(photofile.getComments());
        return photoDTO;
    }

    public PhotoFile convertToEntity(PhotoFileDTO photoDTO){
        PhotoFile photoEntity = new PhotoFile();
        photoEntity.setId(photoDTO.getId());
        photoEntity.setData(Base64.decodeBase64(photoDTO.getData()));
        photoEntity.setRating(photoDTO.getRating());
        photoEntity.setFileName(photoDTO.getFileName());
        photoEntity.setFileType(photoDTO.getFileType());
        photoEntity.setVoteCounter(photoDTO.getVoteCounter());
        photoEntity.setComments(photoDTO.getComments());
        return photoEntity;
    }
    public String photoToString(byte[] data) {
        byte[] encodeBase64 = Base64.encodeBase64(data);
        return new String(encodeBase64);
    }
}
