package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OneNetwork {
	
	public Network network;

	public OneNetwork() {
		super();
	}
}
