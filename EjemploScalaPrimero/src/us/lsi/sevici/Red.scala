package us.lsi.sevici

import us.lsi.data.Coordenadas2D
import us.lsi.tools.FileTools
import us.lsi.sevici.pojo._
import us.lsi.tools.Extensions._



class Red(nameP:String,hrefP:String,countryP:String,cityP: String,ubicacionP: Coordenadas2D, estacionesP:List[Estacion]) {
  val name = nameP
	val href = hrefP
	val country = countryP
	val city = cityP
	val ubicacion = ubicacionP
	val estaciones = estacionesP
	
	
	def byNumero(numero:Int): Estacion = {
		this.estaciones.find(e=>e.numero == numero).getOrElse(null)
	}
	
	def getCercanas(ubicacion: Coordenadas2D, d: Double): List[Estacion] = {
		this.estaciones.filter(x=>x.ubicacion.esCercana(ubicacion, d)).toList
	}
	
	override def toString(): String = {
    var r = "name=" + name + ", href=" + href + ", country=" + country + ", city=" + city + "]\n"
    if(this.estaciones != null) {
      r = r + this.estaciones.map(_.toString).mkString("\n")
    }
    r
  }
  
}

object Red { 
  
  
  val url = "http://api.citybik.es"
	val file = "resources/estaciones.csv"
	
	def of(): Red = {
		val name = "Sevici" 
		val href = null
		val country = "ES"
		val city = "Sevilla"
		val ubicacion = Coordenadas2D.of(37.388096,-5.982330)
		val lineas = FileTools.lines(file,enc="ISO-8859-1")
		val estaciones = lineas.slice(1, lineas.size)
				.map(linea=>Estacion.parse(linea))
				.toList
		Red.of(name, href, country, city, ubicacion,estaciones)
	}
  
  def ofUrl(url: String): Red =  {
     val sevici = Web.getRed(url);
     val network = sevici.network
		 val name = network.name 
		 val href = network.href
		 val country = network.location.country?:""
		 val city = network.location.city
		 val ubicacion = Coordenadas2D.of(network.location.latitude.toDouble,network.location.longitude.toDouble)
		 val estaciones = network.stations.map(s=>Estacion.of(s)).toList
		 Red.of(name, href, country, city, ubicacion,estaciones)
	}
  
  def of(network: Network): Red =  {
		 val name = network.name 
		 val href = network.href
		 val country = network.location.country?:""
		 val city = network.location.city
		 val ubicacion = Coordenadas2D.of(network.location.latitude.toDouble,network.location.longitude.toDouble)
		 val estaciones = null // network.stations.map(s=>Estacion.of(s)).toList
		 Red.of(name, href, country, city, ubicacion,estaciones)
	}
  
  
  def of(name:String,href:String,country:String,city: String,ubicacion: Coordenadas2D,estaciones:List[Estacion]): Red = {
    new Red(name, href, country, city, ubicacion,estaciones)
  }
  
  
}