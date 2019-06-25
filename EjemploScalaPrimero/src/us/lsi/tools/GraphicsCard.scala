package us.lsi.tools

import us.lsi.montecarlo.Card

object GraphicsCard {
  
  
   def cartas(fileOut: String, cartas: List[Card], fuerza: Double,  tipo: String): Unit = {
		var result = FileTools.text("resources/CartasPattern.html")
		val cartasText = cartas.map(c=>"<img src=\""+c.nameFile+"\" width=\"120px\" height=\"180px\">").mkString("\n","\n","\n")
		val reglas = Map("cartas"->cartasText,"fuerza"->f"${fuerza}%.3f","tipo"->tipo)
		result = StringTools.transform(result,reglas)
		FileTools.write(fileOut,result)
	}
  
}