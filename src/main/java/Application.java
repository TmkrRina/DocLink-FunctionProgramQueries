import data.DataBuilder;
import functions.FunctionForQ;
import models.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Application  {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//System.out.println(FunctionForQ.categories.apply(DataBuilder.getPosts()));
        System.out.println(FunctionForQ.common_health_issue.apply(DataBuilder.getPosts(),3));
//        Map<Integer, Integer> posts = FunctionForQ.get_doctor_from_post.apply(DataBuilder.getPosts());
//        Map<Integer, Integer> comments = FunctionForQ.getGet_doctor_from_commnts.apply(DataBuilder.getComments());
//        DataBuilder.getPosts().stream()
//                .filter(x -> x.getPostType()
//                        .equals(PostType.HEALTH_ISSUE))
//                .collect(Collectors.toMap((Category.valueOf("HEART"),Category.valueOf("EAR_NOSE_THROAT")
//                        ,Category.valueOf("DENTAL"),Category.valueOf("CHEST"),Category.valueOf("SKIN"),Category.valueOf("EYES")
//                        ));
//
//        System.out.println(
//                Arrays.stream(Category.values()).collect(Collectors.toMap(
//                category -> category,
//                category -> DataBuilder.getPosts().stream()
//                        .filter(post -> post.getCategories().contains(category))
//                        .collect(Collectors.toList())))
//                        .entrySet().stream()
//                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()))
//        );
//



//System.out.println(FunctionForQ.Kth_Users_That_Active.apply(DataBuilder.getPosts(),20));
//        System.out.println(posts);
//        System.out.println(comments);
//       System.out.println(
//               FunctionForQ.Kth_Doctors_That_Active.apply(
//                       posts, comments
//                       ,20));

//System.out.println( DataBuilder.getPosts()
//        .stream().filter(comment -> comment.getPostType().equals(PostType.HEALTH_ISSUE))
//        .filter(comment -> comment.getUser().getRoles().contains(Role.PATIENT))
//        .collect(Collectors.groupingBy(Post::getUser))
//        .entrySet()
//        .stream()
//        .collect(Collectors.toMap(x -> x.getKey().getId(), x-> x.getValue().size()))
//        .entrySet()
//        .stream()
//        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//        .map(integerIntegerEntry -> DataBuilder.getUsers()
//                .stream()
//                .filter(user -> user.getId().equals(integerIntegerEntry.getKey())).findFirst())
//        .filter(Optional::isPresent)
//        .map(Optional::get).limit(20)
//        .collect(Collectors.toList()));
//        System.out.println( DataBuilder.getPosts()
//                .stream().filter(comment -> comment.getPostType().equals(PostType.HEALTH_ISSUE))
//                .filter(comment -> comment.getUser().getRoles().contains(Role.PATIENT))
//                .collect(Collectors.groupingBy(Post::getUser))
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(x -> x.getKey().getId(), x-> x.getValue().size()))
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//
//                .collect(Collectors.toList()));


//        System.out.println(DataBuilder.getPosts().stream().collect(Collectors.groupingBy(Post::getDescription)).values().stream().collect(Collectors.toList()));
//        Map<Integer, Integer> result = DataBuilder.getPosts()
//                .stream()
//                .filter(s->s.getPostType().equals(PostType.ANNOUNCEMENT))
//                .map(x -> (Announcement) x)
//                .collect(Collectors.groupingBy(Announcement::getUser))
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(x -> x.getKey().getId(), x -> x.getValue().size()))
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
//
//        Map<Integer, Integer> result2 = DataBuilder.getComments()
//                .stream()
//                .filter(comment -> comment.getUser().getRoles().contains(Role.DOCTOR))
//                .filter(comment -> comment.getPost().getPostType().equals(PostType.HEALTH_ISSUE))
//                .collect(Collectors.groupingBy(Comment::getUser))
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(x -> x.getKey().getId(), x-> x.getValue().size()))
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
//        System.out.println("hi"+
//        );

//        DataBuilder.getPosts()
//                .stream().filter(comment -> comment.getPostType().equals(PostType.HEALTH_ISSUE))
//                .filter(comment -> comment.getUser().getRoles().contains(Role.PATIENT))
//              .collect(Collectors.groupingBy(Post::getUser))
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(x -> x.getKey().getId(), x-> x.getValue().size()))
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(integerIntegerEntry -> DataBuilder.getDoctors()
//                .stream()
//                .filter(user -> user.getUser().getId().equals(integerIntegerEntry.getKey())).findFirst())
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

//        System.out.println(result);
//        System.out.println(result2);
//
//        Map<Integer, Integer> result3 = new HashMap<>(result);
//        result3.putAll(result);
//        result3.putAll(result2);

//        result2.forEach((k, v) -> result3.merge(k,v, Integer::sum));

//        System.out.println(result);
//        result.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
//        System.out.println(
//                result3.entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                        .limit(20)
//                        .map(integerIntegerEntry -> DataBuilder.getDoctors().stream().filter(doctor -> doctor.getUser().getId().equals(integerIntegerEntry.getKey())).findFirst())
//                        .filter(Optional::isPresent)
//                        .map(Optional::get)
//                .collect(Collectors.toList())
//        );
//     //   System.out.println(DataBuilder.getPosts());
//
////System.out.println( DataBuilder.getComments().stream().filter(s->s.getPost().getUser().getRoles().contains(Role.DOCTOR)).filter(h->h.getPost().getPostType().equals(PostType.HEALTH_ISSUE) || h.getPost().getPostType().equals(PostType.ANNOUNCEMENT )).collect(Collectors.toList()));
//
///*
//
//Stream<String> a =
//        DataBuilder.getComments()
//                .stream()
//                .filter(s->s.getPost().getUser().getRoles().contains(Role.DOCTOR))
//                .filter(h->h.getPost().getPostType().equals(PostType.HEALTH_ISSUE)) .sorted(Comparator.comparing(Comment::getCreatedAt))
//                .map(s->s.getUser().getFirstName())
//               ;
//
//       /* DataBuilder.getPosts()
//                .stream()
//                .filter(s->s.getUser().getRoles().contains(Role.DOCTOR))
//                .filter(s->s.getPostType().equals(PostType.ANNOUNCEMENT))
//                .map(s->s.getUser().getFirstName()).
//                distinct()
//                .collect(Collectors.toList());*//*
//Stream<String> b = DataBuilder.getPosts()
//        .stream()
//        .filter(s->s.getUser().getRoles().contains(Role.DOCTOR))
//        .filter(s->s.getPostType().equals(PostType.ANNOUNCEMENT)).sorted(Comparator.comparing(Post::getCreatedAt))
//        .map(s->s.getUser().getFirstName())
//                ;
//
//System.out.println(  Stream.concat(DataBuilder.getComments()
//                .stream()
//                .filter(s->s.getPost().getUser().getRoles().contains(Role.DOCTOR))
//                .filter(h->h.getPost().getPostType().equals(PostType.HEALTH_ISSUE)) .sorted(Comparator.comparing(Comment::getCreatedAt))
//                .map(s->s.getUser().getFirstName()).distinct()
//        , DataBuilder.getPosts()
//                .stream()
//                .filter(s->s.getUser().getRoles().contains(Role.DOCTOR))
//                .filter(s->s.getPostType().equals(PostType.ANNOUNCEMENT)).sorted(Comparator.comparing(Post::getCreatedAt))
//                .map(s->s.getUser().getFirstName()).distinct()
//).distinct().limit(20).collect(Collectors.toList()));




/*
        DataBuilder.getComments()
                .stream()
                .filter(s->s.getPost().getUser().getRoles().contains(Role.DOCTOR))
                .filter(h->h.getPost().getPostType().equals(PostType.HEALTH_ISSUE) )
                .sorted().collect(Collectors.toList());*/
    }




    }

