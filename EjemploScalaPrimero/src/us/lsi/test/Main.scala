package us.lsi.test

import us.lsi.data.Coordenadas2D
import us.lsi.data.Coordenadas3D
import us.lsi.ruta.Ruta
import us.lsi.ruta.Marca
import us.lsi.tools.{FileTools,StringTools}


object Main {
  
  def test1(): Unit = {
    val c2 = Coordenadas2D.of(2.1,3.0)
    println(c2)
    val c3 = Coordenadas3D.of(2.1,3.0,5.1)
    println(c3)
  }
  
   def test2(): Unit = {
     val ruta = Ruta.of("resources/ruta.csv")
     println(ruta)
   }
   
   def test3(): Unit = {
     val text = FileTools.text("resources/LineChartPattern.html")
     val out = StringTools.transform(text,Map("data"->"Antonio"))
     println(out)
   }
   
   def test4(): Unit = {
    val r = Ruta.of("resources/ruta.csv");
		println(r.longitud)
		r.mostrarAltitud("ficheros/RutaAlturasOut.html")
   }
  
  def main(args: Array[String]): Unit = {
    test4()
  }
  
}