package advisor.dto.playlistsresponse;

import java.util.List;

public class PlaylistsPlaylists {

    String href;
    List<PlaylistsItem> items;

    public String getHref() {
        return href;
    }

    public List<PlaylistsItem> getItems() {
        return items;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setItems(List<PlaylistsItem> items) {
        this.items = items;
    }
}
