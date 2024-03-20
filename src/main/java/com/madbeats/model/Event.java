package com.madbeats.model;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;

//@Document
public class Event extends Spot {

	//@Id
	private int idEvent;
	//@Field
	private String nameEvent;
	//@Field
	private String artist;
	//@Field
	private String date;
	//@Field
	private String schedule;
	//@Field
	private double price;
	//@Field
	private int minimumAge;
	//@Field
	private String musicCategory;
	//@Field
	private String urlEvent;
	//@Field
	private String dressCode;

	public Event(int idEvent, int idSpot, String nameEvent, String artist, String date,String schedule, double price, int minimumAge, 
			String musicCategory,String urlEvent, String dressCode, String nameSpot,String addressSpot, String urlSpot){

		super(idSpot, nameSpot, addressSpot, urlSpot);
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
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
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

	public String toString() {

		return "Evento{" +
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
				"} " + super.toString();
	}
}
