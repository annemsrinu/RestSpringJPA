/**
 * 
 */
package com.learner.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author DELL
 *
 */
@Entity
public class Address {
	@Id
	@GeneratedValue
	private int doorNumber;
	private String streename;
	private String city;

	/**
	 * @return the doorNumber
	 */
	public int getDoorNumber() {
		return doorNumber;
	}

	/**
	 * @param doorNumber the doorNumber to set
	 */
	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}

	/**
	 * @return the streename
	 */
	public String getStreename() {
		return streename;
	}

	/**
	 * @param streename the streename to set
	 */
	public void setStreename(String streename) {
		this.streename = streename;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
