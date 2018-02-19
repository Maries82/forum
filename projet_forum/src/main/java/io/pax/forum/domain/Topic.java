package io.pax.forum.domain;

/**
 * Created by AELION on 15/02/2018.
 */
public class Topic {
    int id;
    String name;

    public Topic(){}

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
