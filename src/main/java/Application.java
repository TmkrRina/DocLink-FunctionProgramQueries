import data.DataBuilder;
import functions.DocLinkFunctionsWonde;
import models.Category;
import models.HealthIssue;
import models.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

       List<Post> healthIssuesForTest = DataBuilder.getPosts().stream()
               .filter(aPost -> aPost instanceof HealthIssue)
               .collect(Collectors.toList()).subList(10, 21)
               ;

       Map<String, Category> expectedOfFunction2 = new HashMap<>();
       expectedOfFunction2.put("21-30",Category.EYES);
       expectedOfFunction2.put("31-40",Category.DENTAL);
       expectedOfFunction2.put("41-50",Category.CHEST);
       Map<String, Category> actualResult = DocLinkFunctionsWonde.groupingHealthIssubeByAgeGroup.apply(healthIssuesForTest);
       System.out.println(actualResult);
    }



}
