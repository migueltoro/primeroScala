package us.lsi.whatsapp

import java.time.LocalTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.matching.Regex


class Mensaje(fechaP: LocalDate,horaP: LocalTime,usuarioP: String,textoP: String) {
    val fecha = fechaP
		val hora = horaP
		val usuario = usuarioP
		val texto = textoP
  
  override def toString(): String = {
		f"${this.fecha.toString}%s,${this.hora.toString}%s,${this.usuario}%10s,${this.texto}%s"
	}
}

object Mensaje {
 
  def pattern = raw"(\d\d?/\d\d?/\d\d?) (\d\d?:\d\d) - ([^:]+): (.+)".r
  
  def parse(mensaje: String) = {
		var ms: Mensaje = null
		mensaje match {
		  case Mensaje.pattern(ftext,horatex,usuario,texto) => {
		    val pf = ftext.split("/")
		    val fecha = LocalDate.of(Integer.parseInt("20"+pf(2)),Integer.parseInt(pf(1)),Integer.parseInt(pf(0)))
		    val ph =  horatex.split(":")
		    val hora = LocalTime.of(Integer.parseInt(ph(0)),Integer.parseInt(ph(1)))
		    ms = new Mensaje(fecha, hora, usuario, texto)  
		  }
		  case _ => 
		}
		ms
	}
}