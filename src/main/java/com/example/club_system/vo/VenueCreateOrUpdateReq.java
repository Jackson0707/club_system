package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VenueCreateOrUpdateReq {

    @NotBlank
    @JsonProperty("id")
    private String id;

    @JsonProperty("user")
    private String user;

    public VenueCreateOrUpdateReq() {
        super();
    }

    public VenueCreateOrUpdateReq(@NotBlank String id, String user) {
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
