package io.pax.forum.domain;

/**
 * Created by AELION on 15/02/2018.
 */
public class Topic {
    int id;
    String name;
    int userId;

    public Topic(){}

    public Topic(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return this.id;
    }
    public int getUserId(){
        return this.userId;
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
