package model;

import java.util.List;

/**
 * User
 * Created by thebaz on 23/04/15.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Long creationDate;
    private List<Role> roles;
    private String authoredSiteId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getAuthoredSiteId() {
        return authoredSiteId;
    }

    public void setAuthoredSiteId(String authoredSiteId) {
        this.authoredSiteId = authoredSiteId;
    }
}
