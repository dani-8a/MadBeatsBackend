package com.madbeats.repository;

import com.madbeats.model.Spot;
import java.util.HashMap;
import java.util.Map;
//import org.springframework.stereotype.Repository;

//@Repository
public class SpotRepository {
	
	private Map<String, Spot> spotMap = new HashMap<>();

	public static void main(String[] args) {
		
		SpotRepository spotRepository = new SpotRepository();
		spotRepository.addSpot();
		spotRepository.readSpot("Spot 1");
		spotRepository.readSpot("Spot 2");
	}
	
	public void addSpot() {
		
		Spot spot1 = new Spot(01, "Sala Coco", "Calle Alcalá 20, Madrid", "www.mondodisko.com");
		Spot spot2 = new Spot(02, "Club Malasaña","Calle San Vicente Ferrer 23, Madrid","www.clubmalasaña.com");		
		spotMap.put("Spot 1", spot1);
		spotMap.put("Spot 2", spot2);
	}
	
	public void readSpot(String key) {
		
		Spot spot = spotMap.get(key);
        if (spot != null) {
            System.out.println("Lugar encontrado: " + spot.toString());
        } else {
            System.out.println("Lugar no encontrado con la clave: " + key);
        }
			
	}

	 public Spot getSpot(String key) {
	        return spotMap.get(key);
	    }

}
