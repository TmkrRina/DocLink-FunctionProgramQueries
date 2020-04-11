import models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    static List<User> users = new ArrayList<User>();
    static List<Post> posts = new ArrayList<Post>();
    static List<Comment> comments = new ArrayList<Comment>();

    public static void main(String[] args) throws Exception {
        Generator generator = new Generator();
        generator.run();

        Application.setUp();
    }

    public static void setUp() throws Exception {

        String filename = User.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "user", users);

//        System.out.println(users);

        filename = HealthIssue.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "healthIssue", posts);

        filename = Announcement.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "announcement", posts);

        filename = Comment.class.getName().toLowerCase() + ".csv";
        readCsv(filename, "comment", comments);

    }

    public static void readCsv(String filename, String className, List list) throws Exception {
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                switch (className) {
                    case "user":
                        list.add(buildUser(scanner.nextLine()));
                        break;
                    case "healthIssue":
                        list.add(buildHealthIssue(scanner.nextLine()));
                        break;
                    case "announcement":
                        list.add(buildAnnouncement(scanner.nextLine()));
                        break;
                    case "comment":
                        list.add(buildComment(scanner.nextLine()));
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String[] getParts(String line) {
        return line.split(",");
    }

    public static User buildUser(String line) throws Exception {
        String[] parts = getParts(line);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        User user = new User(
                Integer.valueOf(parts[0]),
                parts[1],
                parts[2],
                simpleDateFormat.parse(parts[3]),
                Country.valueOf(parts[4]),
                parts[5],
                simpleDateFormat.parse(parts[6]),
                simpleDateFormat.parse(parts[7])
        );
        return user;
    }

    public static HealthIssue buildHealthIssue(String line) throws Exception {
//        System.out.println(getParts(line));
        String[] parts = getParts(line);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String cats = parts[6];
        System.out.println(cats);

        List<Category> categories = Arrays.stream(parts[6].replaceAll("(%|<|>)", "").split(","))
                .map(x -> Category.valueOf(x))
                .collect(Collectors.toList());

//        HealthIssue healthIssue = new HealthIssue(
//                Integer.valueOf(parts[0]),
//                parts[1],
//                parts[2],
//                users.get(Integer.valueOf(parts[3])),
//                simpleDateFormat.parse(parts[4]),
//                simpleDateFormat.parse(parts[5]),
//                categories,
//                Status.valueOf(parts[7])
//        );

        return null;
    }

    public static Announcement buildAnnouncement(String line) {
//        System.out.println(getParts(line));
        return null;
    }

    public static Comment buildComment(String line) {
//        System.out.println(getParts(line));
        return null;
    }
}
