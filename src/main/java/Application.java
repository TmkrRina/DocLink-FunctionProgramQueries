import data.DataBuilder;
import functions.GroupByLocation;

import functions.NonActiveDoctors;
import functions.NonActiveUsers;
import models.PostType;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            Date d = new Date(2020-57-11);
        System.out.printf("=============%n%s%n=============%n", NonActiveUsers.userActivityGrouping.apply(
                DataBuilder.getUsers(),
                DataBuilder.getComments(),
                DataBuilder.getPosts()
        ));
        System.out.println(NonActiveUsers.nonActiveUsersInTimeRange.apply(
                DataBuilder.getUsers(),
                DataBuilder.getComments(),
                DataBuilder.getPosts(),
                d,
                d


        ));

    }


}



