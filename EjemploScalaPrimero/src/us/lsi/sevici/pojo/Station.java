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
}
