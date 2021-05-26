package ua.epam.internetprovider.controller;

public class Forward {
    private String resource;
    private boolean redirect;

    public Forward(String resource, boolean redirect) {
        this.resource = resource;
        this.redirect = redirect;
    }

    public Forward(String resource) {
        this(resource,false);
    }

    public boolean isRedirect() {
        return redirect;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}
