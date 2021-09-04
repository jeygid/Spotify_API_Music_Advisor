package advisor.dto.newresponse;

import java.util.List;

public class NewAlbums {

    String href;
    List<NewItem> items;

    public String getHref() {
        return href;
    }

    public List<NewItem> getItems() {
        return items;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setItems(List<NewItem> items) {
        this.items = items;
    }
}
