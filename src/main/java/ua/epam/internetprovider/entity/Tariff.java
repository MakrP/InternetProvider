package ua.epam.internetprovider.entity;

public class Tariff extends Entity {
    private long id;
    private String title;
    private int price;
    private long serviceId;

    public Tariff(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }


    public Tariff(long id, String title, int price, int serviceId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.serviceId = serviceId;
    }


    public Tariff(String title, int price, long serviceId) {
        this.title = title;
        this.price = price;
        this.serviceId = serviceId;
    }

    public Tariff() {
    }

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

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
}
