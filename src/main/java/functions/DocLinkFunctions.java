package functions;

import interfaces.IMostComment;
import models.Comment;

import java.util.*;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.stream.Collectors;


public interface DocLinkFunctions {

    static IMostComment getDoctor = (listOfComments, date1, date2) -> listOfComments.stream()
            .filter(comment-> comment.getCreatedAt().after(date1) && comment.getCreatedAt().before(date2))
            .collect(Collectors.groupingBy(comment -> comment.getUser()))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .map(entry -> entry.getKey())
            .findFirst().get();
}