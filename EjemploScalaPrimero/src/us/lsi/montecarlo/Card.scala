package us.lsi.montecarlo

import us.lsi.tools.Preconditions

class Card(valorP: Int,paloP: Int) extends Ordered[Card]{
  val valor: Int = valorP // [0,14)
  val palo: Int  = paloP // [0,4)
  val id: Int = palo*4+valor // [0,54)
	
	override def toString(): String = {
    val nv = Card.nombreValores(valor)
    val np = Card.symbolsPalos(palo)
		f"$nv%s$np%s"
	}
	
	def nameFile: String = {
	  val nv = Card.nombreValores(valor)
	  val np = Card.nombrePalos(palo)
	   
		val r: String = valor match {
			case x if x < 9 => f"resources/images/$nv%s_of_$np%s.svg"
			case 9 | 10 | 11 | 12 => f"resources/images/jack_of_$np%s.svg"
			case _ => "Identificador no posible"
			}
	  r
	}

	def compare(card: Card): Int = {
	  this.valor.compare(card.valor)
	}
	  
}

object Card {
  
  def of(text: String): Card = {
		val pc = text.charAt(text.length()-1);
		val ind = text.substring(0,text.length()-1);
		val palo = symbolsPalos.indexOf(pc);
		val id = nombreValores.indexOf(ind);
		Card.of(id, palo);
	}
	
	def of(id: Int): Card =  {
		val palo = id % 4
		val valor = id % 13
		new Card(valor,palo)
	}
	
	def  of(valor: Int, palo:Int): Card = {
		new Card(valor, palo)
	}

	def nombreValores: List[String] = List("2","3","4","5","6","7","8","9","10","J","Q","K","A")
	def symbolsPalos: List[Character] = List('C', 'H', 'S', 'D')
	def nombrePalos: List[String] = List("clubs","hearts","spades","diamonds")
	
}