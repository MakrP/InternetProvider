package ua.epam.internetprovider.entity;

public class Tariff extends Entity {
    private long id;
    private String title;
    private int price;

    public Tariff(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Tariff()  {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
