package com.madbeats.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Events")
@JsonIgnoreProperties("spot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private String idEvent;
    private String nameEvent;
    private String artist;
    private String date;
    private String schedule;
    private double price;
    private int minimumAge;
    private String musicCategory;
    private String urlEvent;
    private String dressCode;
    @JsonBackReference
    private Spot spot;

    public Event(String idEvent, String nameEvent, String artist, String date, String schedule, double price, int minimumAge,
                 String musicCategory, String urlEvent, String dressCode, Spot spot) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.artist = artist;
        this.date = date;
        this.schedule = schedule;
        this.price = price;
        this.minimumAge = minimumAge;
        this.musicCategory = musicCategory;
        this.urlEvent = urlEvent;
        this.dressCode = dressCode;
        this.spot = spot;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public String getMusicCategory() {
        return musicCategory;
    }

    public void setMusicCategory(String musicCategory) {
        this.musicCategory = musicCategory;
    }

    public String getUrlEvent() {
        return urlEvent;
    }

    public void setUrlEvent(String urlEvent) {
        this.urlEvent = urlEvent;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", nameEvent='" + nameEvent + '\'' +
                ", artist='" + artist + '\'' +
                ", date='" + date + '\'' +
                ", schedule='" + schedule + '\'' +
                ", price=" + price +
                ", minimumAge=" + minimumAge +
                ", musicCategory='" + musicCategory + '\'' +
                ", urlEvent='" + urlEvent + '\'' +
                ", dressCode='" + dressCode + '\'' +
                ", spot=" + spot +
                '}';
    }
}
