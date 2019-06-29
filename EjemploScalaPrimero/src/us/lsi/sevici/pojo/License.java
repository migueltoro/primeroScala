package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class License {
	public String name;

	public String url;

	public License() {
		super();
	}
}