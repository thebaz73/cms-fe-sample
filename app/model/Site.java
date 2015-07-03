package model;

import java.util.List;

/**
 * Site
 * Created by thebaz on 23/04/15.
 */
public class Site {
    private String id;
    private String name;
    private String motto;
    private Long creationDate;
    private String address;
    private String workflowType;
    private String commentApprovalMode;
    private User webMaster;
    private List<User> authors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }

    public String getCommentApprovalMode() {
        return commentApprovalMode;
    }

    public void setCommentApprovalMode(String commentApprovalMode) {
        this.commentApprovalMode = commentApprovalMode;
    }

    public User getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(User webMaster) {
        this.webMaster = webMaster;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }
}
