package com.madbeats.model;

public class Event {
	
	private int id;
	private String name;
	private String spot;
	private String artist;
	private String adress;
	private String date;
	private String schedule;
	private double price;
	private int minimunAge;
	private String musicCategory;
	private String uRL;
	private String dressCode;
	
	public Event(int id, String name, String spot, String artist, String adress, String date, String schedule,
			double price, int minimunAge, String musicCategory, String uRL, String dressCode) {
		
		super();
		
		this.id = id;
		this.name = name;
		this.spot = spot;
		this.artist = artist;
		this.adress = adress;
		this.date = date;
		this.schedule = schedule;
		this.price = price;
		this.minimunAge = minimunAge;
		this.musicCategory = musicCategory;
		this.uRL = uRL;
		this.dressCode = dressCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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

	public int getMinimunAge() {
		return minimunAge;
	}

	public void setMinimunAge(int minimunAge) {
		this.minimunAge = minimunAge;
	}

	public String getMusicCategory() {
		return musicCategory;
	}

	public void setMusicCategory(String musicCategory) {
		this.musicCategory = musicCategory;
	}

	public String getuRL() {
		return uRL;
	}

	public void setuRL(String uRL) {
		this.uRL = uRL;
	}

	public String getDressCode() {
		return dressCode;
	}

	public void setDressCode(String dressCode) {
		this.dressCode = dressCode;
	}
	
}
