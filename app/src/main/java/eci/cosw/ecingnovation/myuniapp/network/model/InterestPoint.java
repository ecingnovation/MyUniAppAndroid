package eci.cosw.ecingnovation.myuniapp.network.model;

public class InterestPoint {
    private String id;
    private String description;
    private String title;
    private String label;
    private Double lng;
    private Double lat;
    private String imageURL;

    public InterestPoint(String id, String description, String title, String label, Double lng, Double lat, String imageURL) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.label = label;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
                ", Description='" + description + '\'' +
                ", Title='" + title + '\'' +
                ", Label='" + label + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
