package photoGallery.model.photoComment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import photoGallery.model.PhotoFile.PhotoFile;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class PhotoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentText;

    private String commentAuthor;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="photo_id", nullable=false)
    private PhotoFile photo;

    public PhotoComment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public PhotoFile getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoFile photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoComment that = (PhotoComment) o;
        return id.equals(that.id) &&
                commentText.equals(that.commentText) &&
                commentAuthor.equals(that.commentAuthor) &&
                photo.equals(that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentText, commentAuthor, photo);
    }
}
