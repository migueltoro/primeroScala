package us.lsi.montecarlo

import scala.util.Random
import us.lsi.tools.GraphicsCard

class Mano(cartasP : List[Card]) extends Ordered[Mano] {
  val cartas = cartasP
  var frecuenciasDeValores: Map[Int,Int] = null
	var valoresOrdenadosPorFrecuencias: List[Int] = null
	var son5ValoresConsecutivos = None: Option[Boolean]
	var frecuenciasDePalos : Map[Int,Int] = null;
	var palosOrdenadosPorFrecuencias: List[Int] = null
	var jugada = None: Option[Int]
	var fuerza = None: Option[Double]
  
	def compare(mano: Mano) =  {
	  var r = -this.getJugada.compare(mano.getJugada)
		if (r == 0) {
			r = this.getValoresOrdenadosPorFrecuencias()(0).compare(mano.getValoresOrdenadosPorFrecuencias()(0))
		}
		r
	}
	
	def getSon5ValoresConsecutivos():Boolean =  {
		val son5 = getValoresOrdenadosPorFrecuencias().size == 5
		this.son5ValoresConsecutivos match {
		  case None =>
		      val ls = cartas.map(c=>c.valor).sorted.toList
		      val r = son5 && List.range(0,ls.size-1).forall(x=>ls(x+1)-ls(x)==1)
		      this.son5ValoresConsecutivos = Some(r)
		  case _ => 
		}
		this.son5ValoresConsecutivos.get
	}
	
	def getFrecuenciasDeValores(): Map[Int,Int] = {
		if(this.frecuenciasDeValores == null) {
			 this.frecuenciasDeValores = cartas.groupBy(c=>c.valor).mapValues(x=>x.length)
		}
		this.frecuenciasDeValores
	}
	
	def getValoresOrdenadosPorFrecuencias(): List[Int] = {
		if(this.valoresOrdenadosPorFrecuencias == null) {
			this.valoresOrdenadosPorFrecuencias = this.getFrecuenciasDeValores().toSeq
				.sortWith((x,y)=>x._2 > y._2)
				.map(x=>x._1)
				.toList	
		}
		this.valoresOrdenadosPorFrecuencias
	}
	
	def getFrecuenciasDePalos(): Map[Int,Int] = {
		if(this.frecuenciasDePalos == null) {
			this.frecuenciasDePalos = cartas.groupBy(c=>c.palo).mapValues(x=>x.length)
		}
		this.frecuenciasDePalos
	}
	
	
	def getPalosOrdenadosPorFrecuencias(): List[Int] = {
		if(this.palosOrdenadosPorFrecuencias == null) {
			this.palosOrdenadosPorFrecuencias = this.getFrecuenciasDePalos().toSeq
				.sortWith((x,y)=>x._2 > y._2)
				.map(x=>x._1)
				.toList	
		}
		this.palosOrdenadosPorFrecuencias
	}
	
	def esColor: Boolean =  {
	  val pal0 = this.getPalosOrdenadosPorFrecuencias()(0)
		getFrecuenciasDePalos()(pal0) == 5
	}
	
	def esEscalera: Boolean = {
		this.getSon5ValoresConsecutivos()
	}
	
	def esPoker: Boolean = {
		val val0 = this.getValoresOrdenadosPorFrecuencias()(0)
		this.getFrecuenciasDeValores()(val0) == 4
	}
	
	def esEscaleraDeColor: Boolean = {
		val pal0 = this.getPalosOrdenadosPorFrecuencias()(0)
		this.getSon5ValoresConsecutivos() && this.getFrecuenciasDePalos()(pal0) == 5
	}
	
	def esFull: Boolean = {
		val val0 = this.getValoresOrdenadosPorFrecuencias()(0)
		val val1 = this.getValoresOrdenadosPorFrecuencias()(1)
		this.getFrecuenciasDeValores()(val0) == 3 && this.getFrecuenciasDeValores()(val1) == 2
	}
	
	def esTrio: Boolean = {
		val val0 = this.getValoresOrdenadosPorFrecuencias()(0)
		this.getFrecuenciasDeValores()(val0) == 3
	}
	
	def esDoblePareja: Boolean = {
		val val0 = this.getValoresOrdenadosPorFrecuencias()(0)
		val val1 = this.getValoresOrdenadosPorFrecuencias()(1)
		this.getFrecuenciasDeValores()(val0) == 2 && this.getFrecuenciasDeValores()(val1) == 2
	}
	
	def esPareja: Boolean = {
		val val0 = this.getValoresOrdenadosPorFrecuencias()(0)
		this.getFrecuenciasDeValores()(val0) == 2
	}
	
	def esEscaleraReal() : Boolean = {
		this.esEscaleraDeColor &&
				this.cartas.map(x=>x.valor).toSet.contains(12)
	}
	
	def esCartaMasAlta: Boolean = {
		true;
	}
						
	
	def getJugada: Int =  {
	  this.jugada match {
	    case None => {
	       val r = List.range(0, Mano.jugadas.size).indexWhere(i=>Mano.jugadas(i)(this))
				this.jugada = Some(r)
	    }
	    case _ =>
		}
		return this.jugada.get
	}
	
	
	def fuerza(n: Integer): Double = {
	  this.fuerza match {
	    case None => {
	      var gana : Double = 0
		    var pierde: Double = 0
		    var empata: Double = 0
		    for(i <- 1 to n) {
			    val mr = Mano.random()
			    if(this < mr) {
				    pierde += 1
			    } else if(this > mr) {
				    gana += 1
			    } else {
				    empata += 1
			    }
		    }
		    this.fuerza = Some(gana/(gana+pierde+empata))
	    }
	    case _ =>
	  }	  
		return this.fuerza.get
	}

	override def toString(): String = {
		return this.cartas.map(c=>c.toString()).mkString("(",",",")")
	}
	
	def muestraMano(fileOut: String): Unit= {
	  val fuerza = this.fuerza(5000)
	  val nombreJugada = Mano.nombreJugadas(this.getJugada) 
	  GraphicsCard.cartas(fileOut,this.cartas,fuerza,nombreJugada)
	}

  
}

object Mano {
  
  val numeroDeCartas = 5
  
  def of(cartas: List[Card]): Mano = {
		new Mano(cartas)
	}
  
	def of(txt: String): Mano = {
		val r: String = txt.slice(1,txt.length-1)
		val partes = r.split(",")		
		val cartas = partes.map(x=>Card.of(x)).toList
		Mano.of(cartas);
	}
	
	def random(): Mano = {
		var cartas: List[Card] = List()
		for (_ <- (1 to Mano.numeroDeCartas)) {
			val n: Int = Random.nextInt(52)
			val c: Card = Card.of(n)
			cartas = cartas:+c
		}
		Mano.of(cartas)
	}
	
	def jugadas : List[Mano=>Boolean] = List(
			x=>x.esEscaleraReal, 
			x=>x.esEscaleraDeColor,
			x=>x.esPoker, 
			x=>x.esFull, 
			x=>x.esColor, 
			x=>x.esEscalera, 
			x=>x.esTrio,
			x=>x.esDoblePareja, 
			x=>x.esPareja,
			x=>x.esCartaMasAlta)

  def nombreJugadas: List[String] = List("EscaleraReal","EscaleraDeColor","Poker",
      "Full","Color","Escalera","Trio","DoblePareja","Pareja","CartaAlta")
      
}