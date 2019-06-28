package us.lsi.sevici.pojo;


import us.lsi.tools.JsonTools;

public class Web {
	
	public static OneNetwork getRed(String url) {
		return JsonTools.fromJSON_URL(url, OneNetwork.class);
	}
	
	public static Networks getRedes() {
		String url = "http://api.citybik.es/v2/networks";
		Networks redes = JsonTools.fromJSON_URL(url, Networks.class);
		return redes;
	}
	
	

}
