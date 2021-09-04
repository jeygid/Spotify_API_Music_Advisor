package advisor.dto.featuredresponse;

import com.google.gson.annotations.SerializedName;

public class FeaturedItem {

    String name;
    @SerializedName("external_urls")
    FeaturedSpotify externalUrls;

    public String getName() {
        return name;
    }

    public FeaturedSpotify getExternalUrls() {
        return externalUrls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExternalUrls(FeaturedSpotify externalUrls) {
        this.externalUrls = externalUrls;
    }

    @Override
    public String toString() {
        return name + "\n" + externalUrls.getSpotify();
    }
}
