package com.madbeats.entity;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	
	@Id
	private String idUser;
	@Indexed (unique = true, direction = IndexDirection.DESCENDING)
	private String email;
	private String password;
	private boolean active;
	/**
	@DBRef
	private Set <Role> roles;
	private ArrayList <Event> favouritesEventList = new ArrayList<>();
	private ArrayList <Spot> favouritesSpotList = new ArrayList<>();
	**/
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
/**	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
	}**/
}
