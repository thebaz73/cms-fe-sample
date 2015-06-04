package model;

import java.util.Date;

/**
 * Comment
 * Created by bazzoni on 04/06/2015.
 */
public class Comment {
    private Date timestamp = new Date();
    private String email;
    private String title;
    private String content;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
