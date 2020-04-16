import data.DataBuilder;
import functions.DocLinkFunctionsWonde;
import models.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;

public class TestOfQueryOneAndThree {

    @Test
    public void getRatedDoctorsInSpecialization() {

        // Test of Query 1

        // Instance of data genrator
        QueryOneAndThreeTestData queryOneTester = new QueryOneAndThreeTestData();
        queryOneTester.generate();

        // Expected result definition
        Map<Specialization, Doctor> expectedResult = new HashMap<Specialization, Doctor>();
        expectedResult.put(Specialization.valueOf("Cardiology"), queryOneTester.doc3);
        expectedResult.put(Specialization.valueOf("OralSurgery"), queryOneTester.doc4);

        // Function output definition
        Map<Specialization, Doctor> result = new HashMap<>();
        result = DocLinkFunctionsWonde.getRatedDoctorsInSpecialization.apply(queryOneTester.commentsForTest, queryOneTester.doctorsForTest);
        Assert.assertTrue(expectedResult.equals(result));
    }

    @Test
    public void groupingHealthIssubeByAgeGroupTest() {
        // Test case of query 3
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Post> healthIssuesForTest = DataBuilder.getPosts().stream()
                .filter(aPost -> aPost instanceof HealthIssue)
                .collect(Collectors.toList()).subList(10, 21)
                .stream()
                .collect(Collectors.toList())
                ;

        Map<String, Category> expectedOfFunction2 = new HashMap<>();
        expectedOfFunction2.put("21-30",Category.EYES);
        expectedOfFunction2.put("31-40",Category.EYES);
        expectedOfFunction2.put("41-50",Category.EYES);
        expectedOfFunction2.put("51-60",Category.CHEST);
        Map<String, Category> actualResult = DocLinkFunctionsWonde.groupingHealthIssubeByAgeGroup.apply(healthIssuesForTest);
        System.out.println(actualResult);
        Assert.assertTrue(expectedOfFunction2.equals(actualResult));
    }


}
