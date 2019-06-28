package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
	
	public String free_bikes;

	public Extra extra;

	public String latitude;

	public String name;

	public String id;

	public String empty_slots;

	public String longitude;

	public String timestamp;

	public Station() {
		super();
	}

	public String getFree_bikes() {
		return free_bikes;
	}

	public void setFree_bikes(String free_bikes) {
		this.free_bikes = free_bikes;
	}

	public Extra getExtra() {
		return extra;
	}

	public void setExtra(Extra extra) {
		this.extra = extra;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpty_slots() {
		return empty_slots;
	}

	public void setEmpty_slots(String empty_slots) {
		this.empty_slots = empty_slots;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Station [free_bikes = " + free_bikes + ", extra = " + extra + ", latitude = " + latitude + ", name = "
				+ name + ", id = " + id + ", empty_slots = " + empty_slots + ", longitude = " + longitude
				+ ", timestamp = " + timestamp + "]";
	}
}
