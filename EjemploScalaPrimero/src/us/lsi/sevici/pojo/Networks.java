package us.lsi.sevici.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Networks {
	public Network[] networks;

	public Networks() {
		super();
	}

	public Network[] getNetworks() {
		return networks;
	}

	public void setNetworks(Network[] networks) {
		this.networks = networks;
	}

	@Override
	public String toString() {
		return "Networks [networks = " + networks + "]";
	}
}
