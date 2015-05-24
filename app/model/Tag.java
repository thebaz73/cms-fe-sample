package model;

import java.util.Set;

/**
 * Tag
 * Created by thebaz on 24/04/15.
 */
public class Tag {
    private String id;
    private String siteId;
    private String tag;
    private String uri;
    private Integer popularity;
    private Set<String> contentIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Set<String> getContentIds() {
        return contentIds;
    }

    public void setContentIds(Set<String> contentIds) {
        this.contentIds = contentIds;
    }
}
