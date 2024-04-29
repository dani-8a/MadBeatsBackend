package com.madbeats.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Spots")
public class Spot {

    @Id
    private String id;
    private String nameSpot;
    private String addressSpot;
    private List <Event> eventList; 

    public Spot(String id, String nameSpot, String addressSpot, List<Event> eventList) {
		super();
		this.id = id;
		this.nameSpot = nameSpot;
		this.addressSpot = addressSpot;
		this.eventList = new ArrayList<>();
	}

	public Spot() {
		this.eventList = new ArrayList<>();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSpot() {
        return nameSpot;
    }

    public void setNameSpot(String nameSpot) {
        this.nameSpot = nameSpot;
    }

    public String getAddressSpot() {
        return addressSpot;
    }

    public void setAddressSpot(String addressSpot) {
        this.addressSpot = addressSpot;
    }

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	@Override
	public String toString() {
		return "Spot [id=" + id + ", nameSpot=" + nameSpot + ", addressSpot=" + addressSpot + ", eventList="
				+ eventList + "]";
	}

}
