package photoGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import photoGallery.model.photoComment.PhotoComment;
import photoGallery.model.photoComment.PhotoCommentService;

@Controller
@RestController
public class HomeController {

    private PhotoCommentService photoCommentService;

    public HomeController(PhotoCommentService photoCommentService) {
        this.photoCommentService = photoCommentService;
    }

    @PostMapping("/uploadComment")
    public void uploadCommnet(@RequestBody PhotoComment comment) {
        photoCommentService.storeComment(comment);
    }

    @GetMapping("/comment/{id}")
    public PhotoComment getCommnet(@PathVariable Long id) {
        return photoCommentService.getComment(id);
    }

    @DeleteMapping("/comment/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        photoCommentService.deleteComment(id);
    }

    @PutMapping("comment/update")
    public void updateComment(@RequestBody PhotoComment comment) {
        photoCommentService.update(comment);
    }
}
