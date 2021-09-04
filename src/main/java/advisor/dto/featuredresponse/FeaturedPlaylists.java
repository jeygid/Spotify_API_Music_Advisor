package advisor.dto.featuredresponse;

import java.util.List;

public class FeaturedPlaylists {

    String href;
    List<FeaturedItem> items;

    public String getHref() {
        return href;
    }

    public List<FeaturedItem> getItems() {
        return items;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setItems(List<FeaturedItem> items) {
        this.items = items;
    }
}
