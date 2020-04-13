import data.DataBuilder;
import functions.DocLinkFunctions;
import models.Post;
import models.PostType;
import models.User;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        User user = DataBuilder.getUsers().get(2);

//        System.out.println(user.getComments().size());
        Post post1 = DataBuilder.getPosts().get(100);
        Post post2 = DataBuilder.getPosts().get(199);

        List<String> token1 = DocLinkFunctions.tokenize.apply(post1.getDescription());
        List<String> token2 = DocLinkFunctions.tokenize.apply(post2.getDescription());

//        System.out.printf("=======%n%s%n===========%n%d%n==========%n", post.getDescription(), post.getDescription().split(" ").length);
//        double intersectionSize = (double) DocLinkFunctions.getIntersection.apply(token1, token2).size();

//        System.out.printf("=====%n%s%n=======%n", token1.size());
//        System.out.printf("=====%n%s%n=======%n", token2.size());
//        System.out.printf("=====%n%s%n=======%n", token2);
//        System.out.printf("=====%n%s%n=======%n", );
//        System.out.printf("=====%n%s%n=======%n", DocLinkFunctions.jaccardIndex.apply(token1, token2));
        System.out.printf("=====%n%s%n=======%n", DocLinkFunctions.recommendDoctors.apply(user, DataBuilder.getDoctors()));

    }
}
