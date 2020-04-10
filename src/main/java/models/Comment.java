package models;

import interfaces.ICsv;

import java.time.LocalDate;
import java.util.Date;

public class Comment implements ICsv {
    private Integer id;
    private User user;
    private Post post;
    private Date createdAt;
    private Integer checked;
    private String text;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Comment(Integer id, User user, Post post, Integer checked, String text) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.createdAt = new Date();
        this.checked = checked;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public Integer getChecked() {
        return checked;
    }

    @Override
    public String toString() {
        String base = "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                ", checked=" + checked +
                '}';

        return String.format("%n%s%n", base);
    }

    @Override
    public String writeToCsv() {
        return String.format("%d,%d,%d,%d,%s,\"%s\"", id, user.getId(), post.getId(), checked, createdAt.toString(), text);
    }
}
