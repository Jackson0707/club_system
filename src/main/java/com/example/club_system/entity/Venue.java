package com.example.club_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "venueqaq")
public class Venue {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user")
    private String user;

    public Venue() {
        super();
    }

    public Venue(String id, String user) {
        super();
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
