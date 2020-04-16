package interfaces;

import models.Comment;
import models.Doctor;
import models.Post;
import models.User;

import java.util.List;

public interface IPostRecommendation {
    List<Post> apply(Doctor doctor, List<Post> posts, List<Comment> comments, Integer threshHold);
}
