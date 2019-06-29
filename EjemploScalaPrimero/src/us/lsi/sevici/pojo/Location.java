package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	public String country;

	public String city;

	public String latitude;

	public String longitude;

	public Location() {
		super();
	}
}
