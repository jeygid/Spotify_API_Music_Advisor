package advisor.dto.newresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewItem {

    String name;
    List<NewArtist> artists;
    @SerializedName("external_urls")
    NewSpotify externalUrls;

    public List<NewArtist> getArtists() {
        return artists;
    }

    public NewSpotify getExternalUrls() {
        return externalUrls;
    }

    public void setArtists(List<NewArtist> artists) {
        this.artists = artists;
    }

    public void setExternalUrls(NewSpotify externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        StringBuilder artistBuilder = new StringBuilder("[");

        for (NewArtist artist : artists) {
            artistBuilder.append(artist.getName()).append(", ");
        }

        String artists = artistBuilder.substring(0, artistBuilder.length() - 2) + "]";

        return name + "\n" +

                artists + "\n" +

                externalUrls.getSpotify();
    }
}
