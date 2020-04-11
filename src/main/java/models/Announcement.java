package models;

import interfaces.ICsv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Announcement extends Post implements ICsv {
    public Announcement(Integer id, String title, String description, Doctor doctor, List<Category> categoryList) {
        super(id, title, description, doctor.getUser(), categoryList);
        this.postType = PostType.ANNOUNCEMENT;
    }

    public Announcement(Integer id, String title, String description, User user, Date createdAt, Date updatedAt, List<Category> categories) {
        super(id, title, description, user, createdAt, updatedAt, categories);
        this.postType = PostType.ANNOUNCEMENT;
    }

    @Override
    public String writeToCsv() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return String.format(
                "%d,%s,\"%s\",%d,%s,%s,%s",
                id,
                title,
                description,
                user.getId(),
                simpleDateFormat.format(createdAt),
                simpleDateFormat.format(updatedAt),
                categoriesToCsvFormat()
        );
    }
}
