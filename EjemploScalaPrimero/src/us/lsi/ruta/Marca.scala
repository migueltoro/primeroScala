package us.lsi.ruta

import us.lsi.data.Coordenadas3D
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class Marca(timeP: LocalTime, coordenadasP: Coordenadas3D) {
  val time: LocalTime = timeP
	val coordenadas: Coordenadas3D = coordenadasP

  override def toString(): String = {
		f"($time%s,$coordenadas%s)"
	}
}

object Marca {
  
  def of(time: LocalTime, coordenadas: Coordenadas3D): Marca = {
    new Marca(time: LocalTime, coordenadas: Coordenadas3D)
  }
  
  def parse(text: String): Marca =  { 
		val campos  = text.split(",")
		val time = LocalTime.parse(campos(0),DateTimeFormatter.ofPattern("HH:mm:ss"))
		val coordenadas = Coordenadas3D.of(campos(1).toDouble,campos(2).toDouble,campos(3).toDouble/1000);
		Marca.of(time, coordenadas)
	}
  
}