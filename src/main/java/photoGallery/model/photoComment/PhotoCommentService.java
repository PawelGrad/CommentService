package photoGallery.model.photoComment;

import org.springframework.stereotype.Service;
import photoGallery.exceptions.CommentNotFoundException;
import photoGallery.microservices.FeignClientPhotoService;
import photoGallery.model.PhotoFile.PhotoConverter;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PhotoCommentService {

    private PhotoCommentRepository photoCommentRepository;
    private PhotoConverter photoConverter;

    public PhotoCommentService(PhotoCommentRepository photoCommentRepository, PhotoConverter photoConverter) {
        this.photoCommentRepository = photoCommentRepository;
        this.photoConverter = photoConverter;
    }

    public void storeComment(PhotoCommentDTO commentDTO){
            photoCommentRepository.save(convertToEntity(commentDTO));
    }
    public PhotoComment getComment(Long commentId) {
        return photoCommentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("File not found"));
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
        photoCommentDTO.setPhoto(photoConverter.convertToDTO(comment.getPhoto()));
        photoCommentDTO.setCommentText(comment.getCommentText());
        photoCommentDTO.setCommentAuthor(comment.getCommentAuthor());
        photoCommentDTO.setId(comment.getId());
        return photoCommentDTO;
    }

    public PhotoComment convertToEntity(PhotoCommentDTO comment){
        PhotoComment photoComment = new PhotoComment();
        photoComment.setPhoto(photoConverter.convertToEntity(comment.getPhoto()));
        photoComment.setCommentText(comment.getCommentText());
        photoComment.setCommentAuthor(comment.getCommentAuthor());
        photoComment.setId(comment.getId());
        return photoComment;
    }
}
