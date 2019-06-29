package us.lsi.test

import us.lsi.data.Coordenadas2D
import us.lsi.data.Coordenadas3D
import us.lsi.ruta.Ruta
import us.lsi.ruta.Marca
import us.lsi.tools.{FileTools,StringTools}
import us.lsi.tools.Extensions._
import us.lsi.montecarlo.Mano
import us.lsi.whatsapp.Mensaje
import us.lsi.whatsapp.Grupo
import us.lsi.sevici.Red
import us.lsi.sevici.Redes
import us.lsi.sevici.Estacion
import scala.io.Source.fromURL




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
   
   
   def test6(): Unit = {
     val m = Mano.random()
     println(m)
     println(m.fuerza(5000))
     val m2 = Mano.of("(6H,4S,KC,9D,5H)")
     println(m2)
     println(m2.fuerza(5000))
     m2.muestraMano("ficheros/CartasOut.html")
   }
   
   def test7(): Unit = {
     val m = Mensaje.parse("28/2/16 8:18 - Leonard: Bueno, ¿qué quieres hacer?")
     println(m)
   }
   
   def test8(): Unit = {
     val g = Grupo.ofFile("resources/bigbangtheory_es.txt")   
     println(g.getPalabrasCaracteristicasDeUsuario("Sheldon",2).toSeq.sortBy(x=>x._2).mkString("\n"))
   }
   
   def test9(): Unit = {
     val g = Grupo.ofFile("resources/bigbangtheory_es.txt")   
     val r = g.getPalabrasCaracteristicasDeUsuario("Sheldon",2).toSeq.toSortedMap(Ordering[String].reverse)
      println(r.mkString("\n"))
   }

   
   def test10() : Unit = {
     val e = Estacion.parse("149_CALLE ARROYO,20,11,9,37.397829929383,-5.97567172039552")
      println(e)
     val r = Red.of()
     println(r)
   }
   
   def test11() : Unit = {
     val sevici = Red.ofUrl("http://api.citybik.es/v2/networks/sevici")
     print(sevici)
   }
   
   def test12(): Unit = {
    val redes = Redes.ofWeb()
    print(redes)
   }
   
   def test13(): Unit = {
    val redes = Redes.ofWeb()
    print(redes.citiesWhithSeveralNetworks.mkString("\n"))
   }
   
  def main(args: Array[String]): Unit = {
    test13()
  }
  
}