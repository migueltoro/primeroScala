package us.lsi.sevici

import us.lsi.data.Coordenadas2D
import us.lsi.sevici.pojo._
import us.lsi.tools.Extensions._

class Estacion(numeroP: Int, nameP: String, slotsP: Int, empty_slotsP: Int,  ubicacionP: Coordenadas2D) {
  val numero = numeroP
	val name = nameP
	val slots = slotsP
	val empty_slots = empty_slotsP
	val ubicacion = ubicacionP
	
	override def toString(): String = {
    f"${name}%35s  ${slots}%2d  ${empty_slots}%2d  ${free_bikes}%2d  ${ubicacion.toString()}%s"
  }
  
  def free_bikes: Int = {
		slots-empty_slots
	}
  
}

object Estacion {
  
  var n: Int = 0
  
  def nextInt:String = {
    val old = n
    n = n+1
    old.toString
  }
  
  def of(numero: Int, name: String, slots: Int, empty_slots: Int,  ubicacion: Coordenadas2D): Estacion = {
    new Estacion(numero, name, slots, empty_slots,  ubicacion)
  }
  
  def parse(linea: String): Estacion = {
    val campos = linea.split(",")
		val nameCompuesto = campos(0)
		val partes = nameCompuesto.split("_")
		val numero = partes(0).toInt
		val name = partes(1)
		val slots = campos(1).toInt
		val empty_slots = campos(2).toInt
	  val ubicacion = Coordenadas2D.of(campos(4).toDouble,campos(5).toDouble)
		Estacion.of(numero,name, slots, empty_slots, ubicacion)
  }
  
  def of(station: Station): Estacion = { 
    val numero: Int = (station.extra.uid?:nextInt).toInt
		val name: String = station.name 
		val empty_slots: Int = (station.empty_slots?:nextInt).toInt
		val slots: Int = (station.free_bikes?:nextInt).toInt+empty_slots
	  val ubicacion: Coordenadas2D = Coordenadas2D.of(station.latitude.toDouble,station.longitude.toDouble)
		Estacion.of(numero,name, slots, empty_slots, ubicacion)
  }
  
}