package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Extra {
	public String uid;

	public String slots;

	public String address;

	public String bonus;

	public String last_update;

	public String banking;

	public Extra() {
		super();
	}
}
