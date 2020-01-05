package photoGallery.model.photoComment;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PhotoCommentService {

    private PhotoCommentRepository photoCommentRepository;

    public PhotoCommentService(PhotoCommentRepository photoCommentRepository) {
        this.photoCommentRepository = photoCommentRepository;
    }

    public void storeComment(PhotoComment comment) {
        photoCommentRepository.save(comment);

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
}
