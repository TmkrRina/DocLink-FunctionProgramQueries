package functions;

import models.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import interfaces.IDoctorsFilter;
import interfaces.IPostRecommendation;
import interfaces.IPostsFilter;
import interfaces.IRecommendation;

import java.util.*;

public class DocLinkFunctions {
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

    public static final Function<List<Post>, String> reducePosts = (posts) -> Optional.ofNullable(posts).orElse(new ArrayList<>()).stream().map(x -> x.getDescription()).reduce("", (a, b) -> a + " " + b).trim();

    public static final Function<String, List<String>> tokenize = (
            (string) -> Arrays.asList(Optional.ofNullable(string).orElse("").toLowerCase().split(" "))
                    .stream()
                    .map(word -> word.replaceAll("[\"\\.!\\?]", "").trim())
                    .filter(x -> x.length() >= 1)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList())
    );

    public static final BiFunction<List<String>, List<String>, Set<String>> getIntersection = (
            (words1, words2) -> Optional
                    .ofNullable(words1)
                    .orElse(new ArrayList<String>())
                    .stream()
                    .filter(word -> Optional.ofNullable(words2).orElse(new ArrayList<>()).contains(word))
                    .collect(Collectors.toSet())
    );

    public static final BiFunction<List<String>, List<String>, Double> jaccardIndex = ((words1, words2) ->
            ((double) getIntersection.apply(words1, words2).size() / (Optional.ofNullable(words1).orElse(new ArrayList<>()).size() + Optional.ofNullable(words2).orElse(new ArrayList<>()).size() - getIntersection.apply(words1, words2).size())) * 100
    );


    public static final Function<List<Comment>, List<String>> tokenizePostsThroughComments = (comments -> tokenize.apply(
            reducePosts.apply(Optional.ofNullable(comments).orElse(new ArrayList<>()).stream().map(comment -> comment.getPost()).collect(Collectors.toList()))
    ));

    public static final IDoctorsFilter doctorsMap = (
            (words, doctors, comments, threshHold) -> Optional.ofNullable(doctors).orElse(new ArrayList<>()).stream()
                    .filter(
                            doctor -> jaccardIndex.apply(
                                    words, tokenizePostsThroughComments.apply(getAllComments.apply(doctor.getUser(), comments))
                            ) > threshHold
                    ).collect(Collectors.toList())
    );


    public static final IRecommendation recommendDoctorsToUser = (
            (user, posts, doctors, comments, threshHold) -> doctorsMap.apply(
                    tokenize.apply(reducePosts.apply(posts)),
                    doctors,
                    comments,
                    threshHold
            )
    );


    public static final IPostsFilter postsFilter = (
            (words, posts, threshHold) -> Optional.ofNullable(posts).orElse(new ArrayList<>()).stream()
                    .filter(
                            post -> jaccardIndex.apply(words, tokenize.apply(post.getDescription())) > threshHold
                    )
            .collect(Collectors.toList())
    );

    public static final IPostRecommendation recommendPostsToDoctors = (
            (doctor, posts, comments, threshHold) -> postsFilter.apply(
                    tokenizePostsThroughComments.apply(getAllComments.apply(doctor.getUser(), comments)),
                    posts, threshHold
            )
    );

}