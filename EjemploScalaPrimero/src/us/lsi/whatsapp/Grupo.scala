package us.lsi.whatsapp

import us.lsi.tools.FileTools
import java.time.LocalTime
import java.time.LocalDate
import java.time.DayOfWeek
import us.lsi.tools.Preconditions
import us.lsi.tools.Graphics
import us.lsi.tools.Extensions._


class Grupo(mensajesP: List[Mensaje], palabrasHuecasP: Set[String]) {
  val mensajes = mensajesP
  val palabrasHuecas = palabrasHuecasP
  var mensajesPorUsuario: Map[String,List[Mensaje]] = null
  var mensajesPorDiaDeSemana: Map[DayOfWeek,List[Mensaje]] = null
  var mensajesPorFecha: Map[LocalDate,List[Mensaje]] = null
  var mensajesPorHora: Map[LocalTime,List[Mensaje]] = null
  var frecuenciasDePalabras: Map[String,Int] = null;
  var numeroDePalabras: Option[Int] = None
  var frecuenciasDePalabrasPorUsuario:  Map[(String,String),Int] = null
  var numeroDePalabrasPorUsuario: Map[String,Int] = null
  var frecuenciasDePalabrasPorRestoDeUsuarios: Map[(String,String),Int] = null;
  var numeroDePalabrasPorRestoDeUsuarios: Map[String,Int] = null
  
  override def toString(): String = {
		this.mensajes.map(m=>m.toString).mkString("\n")
	}
  
  def getMensajesPorUsuario: Map[String,List[Mensaje]] = {
		if(this.mensajesPorUsuario==null) {
			this.mensajesPorUsuario = this.mensajes.groupBy(x=>x.usuario)
		}
		this.mensajesPorUsuario
	}
	
	def getNumeroDeMensajesPorUsuario(usuario: String): Int =  {
		Preconditions.checkArgument(this.mensajesPorUsuario.contains(usuario), f"No existe el usuario $usuario%s")
		this.mensajesPorUsuario(usuario).length
	}
	
	def getMensajesPorDiaDeSemana: Map[DayOfWeek,List[Mensaje]] = {
		if(this.mensajesPorDiaDeSemana==null) {
			this.mensajesPorDiaDeSemana = this.mensajes.groupBy(x=>x.fecha.getDayOfWeek())
		}
		return this.mensajesPorDiaDeSemana;
	}
	
	def getNumeroDeMensajesPorDiaDeSemana(diaSemana: DayOfWeek): Int = {
		Preconditions.checkArgument(this.mensajesPorDiaDeSemana.contains(diaSemana), f"No existe la fecha ${diaSemana.toString()}%s")
		return this.mensajesPorDiaDeSemana(diaSemana).length
	}
	
	def drawNumeroDeMensajesPorDiaDeSemana(fileOut: String): Unit = {
		val nombresColumna = getMensajesPorDiaDeSemana.keySet.toSeq
				.sorted
				.map(x=>x.toString)
				.toList	
		val datos =  getMensajesPorDiaDeSemana.toSeq
				.sortBy(x=>x._1)
				.map(x=>x._2.length)
				.toList
		val nombresDatos = List("NumeroDeMensajes")	
		Graphics.columnsBarChart(fileOut, "MensajesPorDiaDeSemana", nombresDatos, nombresColumna, datos)
	}
	
	def getMensajesPorFecha: Map[LocalDate,List[Mensaje]] = {
		if(mensajesPorFecha==null) {
			this.mensajesPorFecha = this.mensajes.groupBy(_.fecha)
		}
		this.mensajesPorFecha;
	}
	
	def getNumeroDeMensajesPorFecha(fecha: LocalDate): Int = {
		Preconditions.checkArgument(this.mensajesPorFecha.contains(fecha), f"No existe la fecha ${fecha.toString()}%s")
		this.mensajesPorFecha(fecha).length
	}
	
	def getMensajesPorHora: Map[LocalTime,List[Mensaje]] ={
		if(this.mensajesPorHora==null) {
			this.mensajesPorHora = this.mensajes.groupBy(x=>x.hora)
		}
		this.mensajesPorHora
	}
	
	def getNumeroDeMensajesPorHora(hora: LocalTime): Int =  {
		Preconditions.checkArgument(this.mensajesPorHora.contains(hora),f"No existe la fecha ${hora.toString()}%s")
		this.mensajesPorHora(hora).length
	}
	
	def getFrecuenciasDePalabras: Map[String,Int] = {
		if(this.frecuenciasDePalabras==null) {
			this.frecuenciasDePalabras = this.mensajes
					.map(_.texto)
					.flatMap(_.split("[ \".,:();¿?¡!]"))
					.filter(x=>x.length>0 && !this.palabrasHuecas.contains(x))
					.counting
		}
		this.frecuenciasDePalabras
	}
	
  def getNumeroDePalabras: Int = {
		if(this.numeroDePalabras==null) {
			this.numeroDePalabras = Some(this.getFrecuenciasDePalabras.values.sum)
		}
		this.numeroDePalabras.get
	}
  
  def getFrecuenciasDePalabrasPorUsuario: Map[(String,String),Int] = {
		if(this.frecuenciasDePalabrasPorUsuario==null) {
			this.frecuenciasDePalabrasPorUsuario = this.mensajes
					.map(m=>(m.texto,m.usuario))
					.flatMap(p=>p._1.split("[ \".,:();¿?¡!]")
							.filter(x=>x.length>0 && !this.palabrasHuecas.contains(x))
							.map(x=>(x,p._2)))
					.counting
		}
		this.frecuenciasDePalabrasPorUsuario
	}
  
  def getNumeroDePalabrasPorUsuario: Map[String,Int] = {
		if(this.numeroDePalabrasPorUsuario==null) {
			this.numeroDePalabrasPorUsuario = this.getFrecuenciasDePalabrasPorUsuario.toSeq		
			  .grouping[String,Int,Int](fKey = {case ((p, u),f) => u}, fMap = {case ((p, u),f) => f}, fAcum= e=>e.sum)
		}
		this.numeroDePalabrasPorUsuario
	}
  
  def getFrecuenciasDePalabrasPorRestoDeUsuarios: Map[(String,String),Int] = {
		if(this.frecuenciasDePalabrasPorRestoDeUsuarios==null) {
			this.frecuenciasDePalabrasPorRestoDeUsuarios = 
			  this.getFrecuenciasDePalabrasPorUsuario
				.transform((k,v)=>this.getFrecuenciasDePalabras(k._1)-v)
		}
		return frecuenciasDePalabrasPorRestoDeUsuarios;
	}
  
  def getNumeroDePalabrasPorRestoDeUsuarios: Map[String,Int] = {
		if(this.numeroDePalabrasPorRestoDeUsuarios==null) {
			this.numeroDePalabrasPorRestoDeUsuarios = this.getFrecuenciasDePalabrasPorRestoDeUsuarios.toSeq
			.grouping[String,Int,Int](fKey = {case ((p, u),f) => u}, fMap = {case ((p, u),f) => f}, fAcum= e=>e.sum)
		}
		this.numeroDePalabrasPorRestoDeUsuarios
	}

	def getImportanciaDePalabrasDeUsuario(palabra: String,usuario: String): Double = {
	  val fpu: Double = this.getFrecuenciasDePalabrasPorUsuario((palabra, usuario))
	  val npu: Double = this.getNumeroDePalabrasPorUsuario(usuario)
	  val npru: Double = this.getNumeroDePalabrasPorRestoDeUsuarios(usuario)
	  val fpru: Double = this.getFrecuenciasDePalabrasPorRestoDeUsuarios((palabra, usuario))
		if(npu == 0 || fpru ==0) 0 else (fpu/npu) * (npru/fpru)
	}
	
	
	def getPalabrasCaracteristicasDeUsuario(usuario: String, umbral: Int ): Map[String,Double] = {	
		this.getFrecuenciasDePalabrasPorUsuario.toSeq	         
				.filter {case ((p, u),f) => 
				            u == usuario &&
		                this.getNumeroDePalabrasPorUsuario.get(u).getOrElse(0) > umbral &&
		                this.getFrecuenciasDePalabrasPorRestoDeUsuarios.get((p,u)).getOrElse(0) > umbral &&
		                 f > umbral
		             }
				.map {case ((p, u),f) => (p,this.getImportanciaDePalabrasDeUsuario(p,u))}
				.toMap
	}
	
}

object Grupo {
  
  def ig(s1: String, s2: String): Boolean = {
    s1 == s2
  }
  
  def of(mensajes: List[Mensaje], palabrasHuecas: Set[String]): Grupo = {
      new Grupo(mensajes, palabrasHuecas)
  }
  
  def ofFile(file: String): Grupo = {
		val mensajes = FileTools.lines(file,enc="UTF8")
				.filter(x=>x.length>0)
				.map(m=>Mensaje.parse(m))
				.filter(m=>m!=null)
				.toList
		val palabrasHuecas = FileTools.lines("resources/palabras_huecas.txt")
				.filter(x=>x.length>0)
				.toSet
		Grupo.of(mensajes, palabrasHuecas)
	}
  
  
}