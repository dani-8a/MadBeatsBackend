package com.madbeats.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Events")
public class Event {

    @Id
    private String id;
    private String nameEvent;
    private String artists;
    private String date;
    private String schedule;
    private double price;
    private int minimumAge;
    private String musicCategory;
    private String musicGenre;
    private String urlEvent;
    private String dressCode;

    public Event(String id, String nameEvent, String artists, String date, String schedule, double price, int minimumAge,
                 String musicCategory, String musicGenre, String urlEvent, String dressCode) {
        this.id = id;
        this.nameEvent = nameEvent;
        this.artists = artists;
        this.date = date;
        this.schedule = schedule;
        this.price = price;
        this.minimumAge = minimumAge;
        this.musicCategory = musicCategory;
        this.musicGenre = musicGenre;
        this.urlEvent = urlEvent;
        this.dressCode = dressCode;
    }
    
    public Event() {
    	
    }

    public String getIdEvent() {
        return id;
    }

    public void setIdEvent(String id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
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

    public String getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nameEvent='" + nameEvent + '\'' +
                ", artists='" + artists + '\'' +
                ", date='" + date + '\'' +
                ", schedule='" + schedule + '\'' +
                ", price=" + price +
                ", minimumAge=" + minimumAge +
                ", musicCategory='" + musicCategory + '\'' +
                ", musicGenre='" + musicGenre + '\'' +
                ", urlEvent='" + urlEvent + '\'' +
                ", dressCode='" + dressCode + '\'' +
                '}';
    }

	public void setSpot(Spot spot) {
		
	}
}
