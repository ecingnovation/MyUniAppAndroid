package eci.cosw.ecingnovation.myuniapp.network.model;

public class AppNew {
    public int id;
    public String title;
    public String publisher;
    public String type;
    public long date;
    public String email;
    public String content;
    public String imageURL;

    public AppNew(int id, String title, String publisher, String type, long date, String email, String content, String imageURL) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.type = type;
        this.date = date;
        this.email = email;
        this.content = content;
        this.imageURL = imageURL;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", publisher='" + getPublisher() + "'" +
                ", type='" + getType() + "'" +
                ", date='" + getDate() + "'" +
                ", email='" + getEmail() + "'" +
                ", content='" + getContent() + "'" +
                ", imageURL='" + getImageURL() + "'" +
                "}";
    }
}
