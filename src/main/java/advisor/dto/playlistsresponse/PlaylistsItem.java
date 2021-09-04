package advisor.dto.playlistsresponse;

import com.google.gson.annotations.SerializedName;

public class PlaylistsItem {

    String name;
    @SerializedName("external_urls")
    PlaylistsSpotify externalUrls;

    public String getName() {
        return name;
    }

    public PlaylistsSpotify getExternalUrls() {
        return externalUrls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExternalUrls(PlaylistsSpotify externalUrls) {
        this.externalUrls = externalUrls;
    }

    @Override
    public String toString() {
        return name + "\n" + externalUrls.getSpotify();
    }
}
