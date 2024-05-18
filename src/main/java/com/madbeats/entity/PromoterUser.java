package com.madbeats.entity;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Promoter_Users")
public class PromoterUser {
	
	@Id
	private String idUser;
	@Indexed (unique = true, direction = IndexDirection.DESCENDING)
	private String email;
	private String password;
	private ArrayList <Event> favouritesEventList = new ArrayList<>();
	private ArrayList <Spot> favouritesSpotList = new ArrayList<>();
	private ArrayList <Event> myEvents = new ArrayList<>();
	
	public PromoterUser(String idUser, String email, String password, ArrayList<Event> favouritesEventList,
			ArrayList<Spot> favouritesSpotList, ArrayList<Event> myEvents) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.favouritesEventList = favouritesEventList;
		this.favouritesSpotList = favouritesSpotList;
		this.myEvents = myEvents;
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
	public ArrayList<Event> getFavouritesEventList() {
		return favouritesEventList;
	}
	public void setFavouritesEventList(ArrayList<Event> favouritesEventList) {
		this.favouritesEventList = favouritesEventList;
	}
	public ArrayList<Spot> getFavouritesSpotList() {
		return favouritesSpotList;
	}
	public void setFavouritesSpotList(ArrayList<Spot> favouritesSpotList) {
		this.favouritesSpotList = favouritesSpotList;
	}
	public ArrayList<Event> getMyEvents() {
		return myEvents;
	}
	public void setMyEvents(ArrayList<Event> myEvents) {
		this.myEvents = myEvents;
	}
}
