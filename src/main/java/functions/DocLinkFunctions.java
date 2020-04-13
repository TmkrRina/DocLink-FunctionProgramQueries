package functions;

import models.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DocLinkFunctions {
    public static final Function<List<Post>, String> reducePosts = (posts) -> posts.stream().map(x -> x.getDescription()).reduce("", (a, b) -> a + " " + b);

    public static final Function<String, List<String>> tokenize = (
            (string) -> Arrays.asList(string.toLowerCase().split(" "))
                    .stream()
                    .map(word -> word.replaceAll("[\"\\.!\\?]", ""))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList())
    );

    public static final BiFunction<List<String>, List<String>, Set<String>> getIntersection = (
            (words1, words2) -> words1.stream().filter(words2::contains).collect(Collectors.toSet())
    );

    public static final BiFunction<List<String>, List<String>, Double> jaccardIndex = ((words1, words2) ->
            ((double) getIntersection.apply(words1, words2).size() / (words1.size() + words2.size() - getIntersection.apply(words1, words2).size())) * 100
    );


    public static final Function<List<Comment>, List<String>> tokenizePostsThroughComments = (comments -> tokenize.apply(
            reducePosts.apply(comments.stream().map(comment -> comment.getPost()).collect(Collectors.toList()))
    ));

    public static final BiFunction<List<String>, List<Doctor>, List<Doctor>> doctorsMap = (
            (words, doctors) -> doctors.stream()
                    .filter(
                            doctor -> jaccardIndex.apply(
                                    words, tokenizePostsThroughComments.apply(doctor.getUser().getComments())
                            ) > 30
                    ).collect(Collectors.toList())
    );


    public static final BiFunction<User, List<Doctor>, List<Doctor>> recommendDoctors = (
            (user, doctors) -> doctorsMap.apply(
                    tokenize.apply(reducePosts.apply(user.getHealthIssues())),
                    doctors
            )
    );


    public static final BiFunction<User, List<Comment>, List<Comment>> getAllComments = (
            (user, comments) -> comments.stream()
                    .filter(comment -> Integer.valueOf(comment.getUser().getId()).equals(user.getId()))
                    .collect(Collectors.toList())
    );

    public static final BiFunction<User, List<Post>, List<Post>> getAllHealthIssues = (
            (user, posts) -> posts.stream()
                    .filter(post -> post.getPostType().equals(PostType.HEALTH_ISSUE) && Integer.valueOf(post.getUser().getId()).equals(user.getId()))
                    .collect(Collectors.toList())
    );


}
