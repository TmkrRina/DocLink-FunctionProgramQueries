package models;

import interfaces.ICsv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HealthIssue extends Post implements ICsv {
    private Status status;

    public HealthIssue(Integer id, String title, String description, User user, List<Category> categoryList, Status status) {
        super(id, title, description, user, categoryList);
        this.postType = PostType.HEALTH_ISSUE;
        this.status = status;
    }

    public HealthIssue(Integer id, String title, String description, User user, Date createdAt, Date updatedAt, List<Category> categories) {
        super(id, title, description, user, createdAt, updatedAt, categories);
        this.postType = PostType.HEALTH_ISSUE;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String writeToCsv() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return String.format(
                "%d,%s,\"%s\",%d,%s,%s,%s,%s",
                id,
                title,
                description,
                user.getId(),
                simpleDateFormat.format(createdAt),
                simpleDateFormat.format(updatedAt),
                categoriesToCsvFormat(),
                status
        );
    }
}
