import data.DataBuilder;
import functions.DocLinkFunctions;
import functions.DoctorsWithMostComments;
import functions.IssuesNotResolved;
import models.Comment;
import models.Post;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Application {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //   public static String doctorWithMostComments(){
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();
        calendar.add(Calendar.DATE, -30);
        Date past = calendar.getTime();

        System.out.println(current);
        System.out.println(past);

        List<Comment> comments = DataBuilder.getComments();

        System.out.println(DoctorsWithMostComments.getDoctor.apply(comments, past, current));

        List<Post> posts = DataBuilder.getPosts();

        System.out.println(IssuesNotResolved.issuesNotResolved.apply(posts));



    }

}
