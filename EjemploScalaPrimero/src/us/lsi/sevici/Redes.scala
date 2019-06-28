package us.lsi.sevici

import us.lsi.sevici.pojo._

class Redes(redesP: List[Red]) {
  val redes = redesP
  
  override def toString(): String = {
    val cb = "%50s,%15s,%60s\n".format("Ciudad","Pais","Url")
		redes.map(r=>String.format("%50s,%15s,%60s",r.city,r.country,r.href)).mkString(cb,"\n","\n________")
  }
	
}

object Redes {
  
  val url: String = "http://api.citybik.es/v2/networks"
	
  
  def ofWeb(): Redes = {
    val r = Web.getRedes()
		val redes = r.networks.map(r=>Red.of(r)).toList
		new Redes(redes)
  }
  
  
}