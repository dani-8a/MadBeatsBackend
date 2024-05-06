package com.madbeats.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Default_Users")
public class DefaultUser {

    @Id
    private String idUser;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    private String password;
    private List<Event> favouritesEventList;
    private List<SpotWithEventResponse> favouritesSpotList;

    public DefaultUser(String idUser, String email, String password, List<Event> favouritesEventList,
                       List<SpotWithEventResponse> favouritesSpotList) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.favouritesEventList = favouritesEventList;
        this.favouritesSpotList = favouritesSpotList;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getFavouritesEventList() {
        return favouritesEventList;
    }

    public void setFavouritesEventList(List<Event> favouritesEventList) {
        this.favouritesEventList = favouritesEventList;
    }

    public List<SpotWithEventResponse> getFavouritesSpotList() {
        return favouritesSpotList;
    }

    public void setFavouritesSpotList(List<SpotWithEventResponse> favouritesSpotList) {
        this.favouritesSpotList = favouritesSpotList;
    }
}
