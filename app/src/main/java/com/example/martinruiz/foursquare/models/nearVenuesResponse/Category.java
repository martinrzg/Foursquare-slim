package com.example.martinruiz.foursquare.models.nearVenuesResponse;

import java.io.Serializable;

public class Category implements Serializable{
    private String id;
    private String name;
    private Icon icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
