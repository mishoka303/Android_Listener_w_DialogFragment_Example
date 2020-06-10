package com.android.recyclerviewnewapp.Models;

public class Cat {
    String name, breed, status;

    public Cat() {}

    public Cat(String name, String breed, String status) {
        this.name = name;
        this.breed = breed;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
