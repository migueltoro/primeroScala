package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Network {
	public License license;

	public String name;

	public Location location;

	public String href;

	public String id;

	public String source;

	public Station[] stations;

	public Network() {
		super();
	}
}

