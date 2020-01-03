package photoGallery.model.photoComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoCommentRepository extends JpaRepository<PhotoComment,Long> {
}
