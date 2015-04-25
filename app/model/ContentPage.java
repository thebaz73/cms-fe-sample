package model;

//import scala.collection.immutable.List;

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
    private List<PageLink> links;
    private List<Content> content;
    private PageMetadata pageMetadata;

    public List<PageLink> getLinks() {
        return links;
    }

    public void setLinks(List<PageLink> links) {
        this.links = links;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    public void setPageMetadata(PageMetadata pageMetadata) {
        this.pageMetadata = pageMetadata;
    }

    public List<Content> toContentList() {
        return content;
    }

    public Content toContent() {
        return content.get(0);
    }

    public int size() {
        return pageMetadata.getSize();
    }
}
