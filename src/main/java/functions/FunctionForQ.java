package functions;

import data.DataBuilder;
import interfaces.TriFunction;
import models.*;

import java.sql.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionForQ {



public static final Function<List<Post>,Map<Integer, Integer>>get_doctor_from_post=posts -> {
    return posts.stream()
            .filter(s -> s.getPostType().equals(PostType.ANNOUNCEMENT))
            .map(x -> (Announcement) x)
            .collect(Collectors.groupingBy(Announcement::getUser))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(x -> x.getKey().getId(), x -> x.getValue().size()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
};

public static final Function<List<Comment>,Map<Integer, Integer>>getGet_doctor_from_commnts=comment ->{
    return comment.stream()
            .filter(c -> c.getUser().getRoles().contains(Role.DOCTOR))
            .filter(c -> c.getPost().getPostType().equals(PostType.HEALTH_ISSUE))
            .collect(Collectors.groupingBy(Comment::getUser))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(x -> x.getKey().getId(), x-> x.getValue().size()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    };


public static final TriFunction<Map<Integer, Integer>,Map<Integer, Integer>,Integer,List<Doctor>>Kth_Doctors_That_Active=(mappedPost, mappedComments, integer)
        ->
    mappedPost.entrySet().stream()
            .collect(
                    Collectors.toMap(
                            post -> post.getKey(),
                            post -> Optional.ofNullable(mappedComments.get(post.getKey()))
                                    .orElse(0) + post.getValue()
                    ))
        .entrySet()
        .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(integer)
            .map(integerIntegerEntry -> DataBuilder.getDoctors().stream()
                    .filter(doctor -> doctor.getUser().getId().equals(integerIntegerEntry.getKey()))
                    .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());


public static final BiFunction<List<Post>,Integer,List<User>>Kth_Users_That_Active=(posts, integer) -> {
    return posts.stream().filter(comment -> comment.getPostType().equals(PostType.HEALTH_ISSUE))
            .filter(comment -> comment.getUser().getRoles().contains(Role.PATIENT))
            .collect(Collectors.groupingBy(Post::getUser))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(x -> x.getKey().getId(), x-> x.getValue().size()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .map(integerIntegerEntry -> DataBuilder.getUsers()
                    .stream()
                    .filter(user -> user.getId().equals(integerIntegerEntry.getKey())).findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get).limit(integer)
            .collect(Collectors.toList());
};
    public static final Function<List<Post>,List<Map.Entry<Category, List<Post>>>> categories=(posts) -> {


        return new ArrayList<>(Arrays.stream(Category.values())
                .collect(Collectors.toMap(category -> category,
                        category -> posts.stream()
                                .filter(x -> x.getCategories().contains(category))
                                .collect(Collectors.toList())))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .entrySet());
    };
    public static final BiFunction<List<Post>,Integer,List<Map.Entry<Category,Integer>>> common_health_issue=(posts,integer) -> {


        return Arrays.stream(Category.values())
                .collect(Collectors.toMap(category -> category,
                        category -> posts.stream()
                                .filter(x -> x.getCategories().contains(category))
                                .collect(Collectors.toList())))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()))

                .entrySet()
                .stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(integer).collect(Collectors.toList());
    };

}

