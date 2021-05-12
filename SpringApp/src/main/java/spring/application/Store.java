package spring.application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	private String name; // Name of the store
	private int count; // Number of people currently in the room
	private int numberMax = 0; // Maximum number of people allowed

	//////////////// Constructors/////////////////////////

	public Store() {

	}

	public Store(String name) {
		this.name = name;
	}

	public Store(String name, int numberMax) {

		this.name = name;
		this.setNumberMax(numberMax);
	}

	//////////////////// Getters and Setters/////////////////

	public int getCount() {
		return count;
	}

	public int getNumberMax() {
		return numberMax;
	}

	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getID() {
		return id;
	}

	/////////////////////////////////////////////////////////

	public void addCount(int number) {
		// Calculate the change in the number of people in the shop
		count = count + number;
		if (count < 0) {
			count = 0;
		}
	}

	public String printStore() {
		return "Name: " + name + " -- People currently in the room: " + count + " -- Maximum number of people allowed: "
				+ numberMax;
	}

}
