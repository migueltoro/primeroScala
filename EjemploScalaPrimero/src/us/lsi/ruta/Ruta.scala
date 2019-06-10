package us.lsi.ruta

import us.lsi.tools.FileTools
import us.lsi.data.Coordenadas3D
import us.lsi.tools.Graphics

class Ruta(marcasP: List[Marca]) {
  val marcas: List[Marca] = marcasP
  
  def longitud(): Double = {
    val indices = List.range(0,marcas.length-1)
    indices.map(i=>Coordenadas3D.distance(marcas(i).coordenadas,marcas(i+1).coordenadas)).sum;
	}
  
  def mostrarAltitud(fileOut: String): Unit = {
		val alturas = List.range(0,marcas.length,10)
				.map(x=>marcas(x).coordenadas.altitude)
				.toList
		val campos = List("Posicion","Altura")
		val position = List.range(0,alturas.length).map(x=>x.doubleValue).toList
		Graphics.lineChart[Double](fileOut,"Ruta Ronda",campos,List(position,alturas));
	}
  
  override def toString(): String = {
		marcas.map(x=>x.toString()).mkString("\n")
	}
}

object Ruta {
  
  def of(marcas: List[Marca]): Ruta = {
    new Ruta(marcas)
  }
  
  def of(file: String): Ruta = {
    val marcas = FileTools.lines(file).map(x=>Marca.parse(x)).toList
    Ruta.of(marcas)
  }
  
}