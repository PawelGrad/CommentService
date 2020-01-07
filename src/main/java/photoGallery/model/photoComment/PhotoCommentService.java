package photoGallery.model.photoComment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import photoGallery.microservices.FeignClientPhotoService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PhotoCommentService {

    private PhotoCommentRepository photoCommentRepository;
    private FeignClientPhotoService feignClientPhotoService;

    public PhotoCommentService(PhotoCommentRepository photoCommentRepository, FeignClientPhotoService feignClientPhotoService) {
        this.photoCommentRepository = photoCommentRepository;
        this.feignClientPhotoService = feignClientPhotoService;
    }

    public void storeComment(PhotoCommentDTO commentDTO){
            photoCommentRepository.save(convertToEntity(commentDTO));
    }
    public PhotoComment getComment(Long commentId) {
        //return PhotoFileRepository.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
        return photoCommentRepository.findById(commentId).orElse(null);
    }

    public void deleteComment(Long id) {
        photoCommentRepository.deleteById(id);
    }

    public void update(PhotoComment comment){
        photoCommentRepository.save(comment);
    }

    public List<PhotoComment>getComments(Long id) { return photoCommentRepository.getAllByPhotoId(id);}

    public PhotoCommentDTO convertToDto(PhotoComment comment){
        PhotoCommentDTO photoCommentDTO = new PhotoCommentDTO();
        photoCommentDTO.setPhoto(feignClientPhotoService.getPhotoDTO(comment.getPhoto().getId()));
        photoCommentDTO.setCommentText(comment.getCommentText());
        photoCommentDTO.setCommentAuthor(comment.getCommentAuthor());
        photoCommentDTO.setId(comment.getId());
        return photoCommentDTO;
    }

    public PhotoComment convertToEntity(PhotoCommentDTO comment){
        PhotoComment photoComment = new PhotoComment();
        photoComment.setPhoto(feignClientPhotoService.getPhotoEntity(comment.getPhoto().getId()));
        photoComment.setCommentText(comment.getCommentText());
        photoComment.setCommentAuthor(comment.getCommentAuthor());
        photoComment.setId(comment.getId());
        return photoComment;
    }
}
