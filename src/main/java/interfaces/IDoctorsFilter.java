package interfaces;

import models.Comment;
import models.Doctor;

import java.util.List;

@FunctionalInterface
public interface IDoctorsFilter {
    List<Doctor> apply(List<String> words, List<Doctor> doctors, List<Comment> comments, Integer threshHold);
}
