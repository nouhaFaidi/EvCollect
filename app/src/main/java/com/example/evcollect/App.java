package com.example.evcollect;

public class App {
    private String date_created;
    private String date_modified;
    private String description;
    private int id;
    private String name;
    public App(){

    }

    public String getDate_created() {
        return date_created;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public App(String date_created, String date_modified, String description, int id, String name) {
        this.date_created = date_created;
        this.date_modified = date_modified;
        this.description = description;
        this.id = id;
        this.name = name;
    }
}
