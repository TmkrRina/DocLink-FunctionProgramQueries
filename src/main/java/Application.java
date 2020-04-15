import data.DataBuilder;
import functions.GroupByLocation;
import models.*;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Application {

    private static List<Post> posts;

    public static void main(String[] args) {

        posts = DataBuilder.getPosts();
        Generator generator = new Generator();
        generator.run();


        try {
            DataBuilder.setUp();


            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Map<Country, List<String>> listLocation = GroupByLocation.groupByLocation.apply(posts, PostType.HEALTH_ISSUE);
        System.out.println(listLocation);

    }

}



