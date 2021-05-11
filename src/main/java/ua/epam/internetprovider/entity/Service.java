package ua.epam.internetprovider.entity;

public class Service extends Entity {
    private long id;
    private String title;

    public Service(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Service() {}

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
