package model;

//import scala.collection.immutable.List;

import java.util.ArrayList;
import java.util.List;

/**
 * ContentPage
 * Created by thebaz on 25/04/15.
 * <p>
 * Mapped from Json lie following
 * <pre>
 *  { "links" : [
 *          { "rel" : "next", "href" : "http://localhost:8080/contents/{siteId}?page=1&size=20 }
 *      ],
 *    "content" : [
 *          &hellip; // 20 Content instances rendered here
 *      ],
 *    "pageMetadata" : {
 *          "size" : 20,
 *          "totalElements" : 30,
 *          "totalPages" : 2,
 *          "number" : 0
 *      }
 *  }
 * </pre>
 */
public class ContentPage {
    private List<Content> cmsContents = new ArrayList<>();
    private Tag tag = new Tag();

    public List<Content> getCmsContents() {
        return cmsContents;
    }

    public void setCmsContents(List<Content> cmsContents) {
        this.cmsContents = cmsContents;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public List<Content> toContentList() {
        return cmsContents;
    }

    public Content toContent() {
        return cmsContents.isEmpty() ? new Content() : cmsContents.get(0);
    }

    public int size() {
        return cmsContents.size();
    }
}
