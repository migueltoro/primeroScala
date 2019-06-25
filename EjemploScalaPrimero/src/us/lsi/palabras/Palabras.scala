package us.lsi.palabras

import us.lsi.tools.FileTools
import us.lsi.tools.StringTools
import scala.collection.SortedMap
import us.lsi.tools.Extensions._

object Palabras {
  
  def palabrasDistintas(file: String):Set[String] = {
		val lineas = FileTools.lines(file)
		val words = lineas.flatMap(w=>w.split("[ ,;.\n()?¿!¡:\"]"))
				.filter(w=>w.length()>0)
				.toSet
		words
	}
	
	def numeroDePalabrasDistintas(file: String): Long = {
		val lineas = FileTools.lines(file);
		val nwords = lineas.flatMap(w=>w.split("[ ,;.\n()?¿!¡:\"]"))
				.filter(w=>w.length()>0)
				.distinct
				.size
		nwords
	}
	
	def  frecuenciasDePalabras(file: String): SortedMap[String,Int] = {
		val lineas = FileTools.lines(file)
		val r = lineas.flatMap(w=>w.split("[ ,;.\n()?¿!¡:\"]"))
				.filter(w=>w.length()>0)
				.groupBy(x=>x)
				.mapValues(x=>x.size)
		SortedMap(r.toSeq:_*)	
	}
	
	def gruposDePalabrasPorFrecuencias(file: String): SortedMap[Int,Set[String]] = {
		val fwords = frecuenciasDePalabras(file)
		val rFwords =  fwords.toSeq
		                      .groupBy(x=>x._2)
		                      .mapValues(x=>x.map(y=>y._1))
		                      .mapValues(x=>x.toSet)
		SortedMap(rFwords.toSeq:_*)
	}


	def gruposDeLineas(file: String): SortedMap[String,Set[Int]] = { 	
		val lineas = FileTools.lines(file)	 
		val words	=	lineas
		    .zipWithIndex
				.flatMap(StringTools.splitTuple)
				.groupBy(x=>x._1)
				.mapValues(x=>x.map(y=>y._2))	
				.mapValues(x=>x.toSet)
		SortedMap(words.toSeq:_*)
	}
	
}

