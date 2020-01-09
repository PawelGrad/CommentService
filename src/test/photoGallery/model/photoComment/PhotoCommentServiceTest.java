package photoGallery.model.photoComment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import photoGallery.microservices.FeignClientPhotoService;
import photoGallery.model.PhotoFile.PhotoConverter;
import photoGallery.model.PhotoFile.PhotoFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PhotoCommentServiceTest {



    @Mock
    PhotoCommentRepository photoCommentRepository;

    @Spy
    PhotoConverter photoConverter;

    @InjectMocks
    private PhotoCommentService photoCommentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertToEntity() {
        PhotoComment comment = new PhotoComment();
        comment.setCommentAuthor("123");
        comment.setCommentText("456");
        comment.setId(1L);
        List<PhotoComment> comments = new ArrayList<>();
        comments.add(comment);

        byte[] bytes = new byte[20];
        new Random().nextBytes(bytes);

        PhotoFile photo = new PhotoFile();
        photo.setRating(0L);
        photo.setVoteCounter(0L);
        photo.setFileName("hurr");
        photo.setFileType("durr");
        photo.setId(1L);
        photo.setComments(comments);
        photo.setData(bytes);


        comment.setPhoto(photo);


        PhotoComment convertedComment = photoCommentService.convertToEntity(photoCommentService.convertToDto(comment));

        assertTrue(convertedComment.equals(comment));



    }
}