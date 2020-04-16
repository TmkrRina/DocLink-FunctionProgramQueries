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

    }



}
