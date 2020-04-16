package functions;

import models.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface DocLinkFunctionsWonde {

    // Wondwosen Query one Auxilary Function -> generating rated doctors per specialization
    static Function<List<Doctor>,Doctor> ratedDoctor = (listOfDoctors) -> listOfDoctors.stream()
            .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .findFirst().get();

    // Query one Main Function
    static BiFunction<List<Comment>, List<Doctor>, Map<Specialization,Doctor>> getRatedDoctorsInSpecialization = (listOfComments, lostOfDocs) -> listOfComments.stream()
            .filter(aComment -> aComment.getChecked() > 0)
            .flatMap(aComment -> lostOfDocs.stream()
                    .filter(aDoctor -> aDoctor.getUser().equals(aComment.getUser())))
            .collect(Collectors.groupingBy(Doctor::getSpecialization))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, (entry) -> ratedDoctor.apply(entry.getValue())))
            ;


    // Query 3  grouping health issue cases by age agroup

    List<String> ageGroups = new ArrayList<String>() {
        {
            add("00 - 09"); add("10-19"); add("20-29"); add("30-39"); add("40-49"); add("50-59"); add("60-69");
            add("70-79"); add("80-89"); add("90-99"); add("100-109");
        }
    };

    static BiFunction<User, Integer, String> mapUserToAgegroup= (user, max) -> Stream.of(user)
            .map(User::getAge)
            .map(aAge -> ageGroups.get(Math.min(aAge, max)/10))
            .collect(Collectors.joining());

    static Function<List<Category>, Category> findPrevalentCategory = (listOfCategory) -> listOfCategory.stream()
            .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .map(Map.Entry::getKey)
            .findFirst().get();

    static Function<List<Post>, Map<String,Category>> groupingHealthIssubeByAgeGroup = (listOfPosts) -> listOfPosts.stream()
            .filter(aPost -> aPost instanceof HealthIssue)
            .sorted(Comparator.comparing(aPost-> aPost.getUser().getAge()))
            .collect(Collectors.groupingBy((aPost -> mapUserToAgegroup.apply(aPost.getUser(), 100)),
                     Collectors.flatMapping(aPost -> aPost.getCategories().stream(), Collectors.toList())))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey,(entry) -> findPrevalentCategory.apply(entry.getValue())))
            ;
}
