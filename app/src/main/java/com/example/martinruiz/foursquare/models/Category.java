package com.example.martinruiz.foursquare.models;

/**
 * Created by MartinRuiz on 9/24/2017.
 */

public class Category {

    private String id;
    private String name;
    private String iconPrefix;
    private String iconSuffix;

    public Category(String id, String name, String iconPrefix, String iconSuffix) {
        this.id = id;
        this.name = name;
        this.iconPrefix = iconPrefix;
        this.iconSuffix = iconSuffix;
    }

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

    public String getIconPrefix() {
        return iconPrefix;
    }

    public void setIconPrefix(String iconPrefix) {
        this.iconPrefix = iconPrefix;
    }

    public String getIconSuffix() {
        return iconSuffix;
    }

    public void setIconSuf(String iconSuffix) {
        this.iconSuffix = iconSuffix;
    }
}
