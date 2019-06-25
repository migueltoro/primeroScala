package us.lsi.sevici

import us.lsi.data.Coordenadas2D
import us.lsi.tools.FileTools

class Red(nameP:String,hrefP:String,countryP:String,cityP: String,ubicacionP: Coordenadas2D, estacionesP:List[Estacion]) {
  val name = nameP
	val href = hrefP
	val country = countryP
	val city = cityP
	val ubicacion = ubicacionP
	val estaciones = estacionesP
	
	override def toString(): String = {
    this.estaciones.map(_.toString).mkString("\n")
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
		val lineas = FileTools.lines(file)
		val estaciones = lineas.slice(1, lineas.size)
				.map(linea=>Estacion.parse(linea))
				.toList
		Red.of(name, href, country, city, ubicacion,estaciones)
	}
  
  def of(name:String,href:String,country:String,city: String,ubicacion: Coordenadas2D,estaciones:List[Estacion]): Red = {
    new Red(name, href, country, city, ubicacion,estaciones)
  }
  
}