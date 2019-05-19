package eci.cosw.ecingnovation.myuniapp.network.model;

public class InterestPoint {
    private String id;
    private String Description;
    private String Title;
    private String Label;
    private Double lng;
    private Double lat;
    private String imageURL;

    public InterestPoint(String id, String description, String title, String label, Double lng, Double lat, String imageURL) {
        this.id = id;
        Description = description;
        Title = title;
        Label = label;
        this.lng = lng;
        this.lat = lat;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "InterestPoint{" +
                "id='" + id + '\'' +
                ", Description='" + Description + '\'' +
                ", Title='" + Title + '\'' +
                ", Label='" + Label + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
