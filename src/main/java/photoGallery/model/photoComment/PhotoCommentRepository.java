package photoGallery.model.photoComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoCommentRepository extends JpaRepository<PhotoComment,Long> {


    @Query(nativeQuery = true, value = "Select * from comments where photo_id = ?")
    List<PhotoComment> getAllByPhotoId(Long id);
}
