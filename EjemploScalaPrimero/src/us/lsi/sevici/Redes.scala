package us.lsi.sevici

import us.lsi.sevici.pojo._
import us.lsi.tools.Extensions._

class Redes(redesP: List[Red]) {
  val redes = redesP
  
  def byCountry(country: String): List[Red]={
		this.redes.filter(x=>x.country == country).toList
	}
	
	def existRedInCity(country: String,city: String): Boolean = {
		this.redes.exists(x=>x.country == country && x.city == city)
	}
	
	def citiesWhithSeveralNetworks: List[String] = {
		this.redes.counting(r => r.city).filter(x => x._2 > 1).map(x=> x._1).toList
	}
	
	def byCountryCityName(country: String,city: String, name: String): List[Red] = {
		this.redes.filter(x=>x.country == country && x.city == city && x.name == name).toList
	}
	
	def byCountryCity(country: String,city: String):List[Red] = {
		this.redes.filter(x=>x.country == country && x.city == city).toList
	}
	
	def countries: Set[String] = {
		this.redes.map(x=>x.country).toSet
	}

	def cities(country:String): Set[String] ={
		this.redes.filter(x=>x.country==country).map(x=>x.city).toSet
	}
  
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