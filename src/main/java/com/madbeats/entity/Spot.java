package com.madbeats.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Spots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

    @Id
    private String idSpot;
    private String nameSpot;
    private String addressSpot;

    public Spot(String idSpot, String nameSpot, String addressSpot) {
        this.idSpot = idSpot;
        this.nameSpot = nameSpot;
        this.addressSpot = addressSpot;
    }

    public String getIdSpot() {
        return idSpot;
    }

    public void setIdSpot(String idSpot) {
        this.idSpot = idSpot;
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

	@Override
	public String toString() {
		return "Spot [idSpot=" + idSpot + ", nameSpot=" + nameSpot + ", addressSpot=" + addressSpot + "]";
	}
}
