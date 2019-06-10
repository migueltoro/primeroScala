package us.lsi.tools

import scala.math.Numeric

object Graphics {
  
  def lineChart[E](fileOut: String,title:String,campos: List[String], datos: List[List[E]]): Unit = {		
		var result = FileTools.text("resources/LineChartPattern.html")
		val camposText = campos.map(x=>"'"+x+"'").mkString("[",",","]")
		val dataText = List.range(0,datos(0).length)
									.map(e=>Graphics.filaLineChart(e,datos))
									.mkString("",",\n","\n")
		val nTitle = "'"+title+"'"
		val reglas = Map("title"->nTitle,"campos"->camposText,"data"->dataText)
		result = StringTools.transform(result,reglas);
		FileTools.write(fileOut,result)
	}

	private def filaLineChart[E](e: Integer, datos: List[List[E]]): String = {
		List.range(0,datos.length).map(i=>datos(i)(e).toString).mkString("[",",","]")
	}

	/**
	 * 
	 * Muestra un diagrama de tarta
	 * @pre La lista de nombres tiene la misma longitud que la de de datos
	 * @pre la lista de campos tiene longitud dos
	 * @param fileOut Fichero de salida
	 * @param title Titulo 
	 * @param campos Campos 
	 * @param nombres Nombres de datos
	 * @param datos Datos
	 */
	def pieChart[E](fileOut: String,title: String,campos:List[String],nombres:List[String],datos:List[E]) : Unit = {
		var result = FileTools.text("resources/PieChartPattern.html");
		val camposText = campos.map(x=>"'"+x+"'").mkString("[",",","]")
		val dataText = List.range(0,datos.length)
									.map(e=>Graphics.filaPieChart(e,nombres,datos))
									.mkString("","\n","\n")
		val nTitle = "'"+title+"'"
		val reglas = Map("title"->nTitle,"campos"->camposText,"data"->dataText)
		result = StringTools.transform(result,reglas);
		FileTools.write(fileOut,result)
	}

	private def filaPieChart[E](e: Integer, nombres: List[String], datos:List[E]): String = {
		f"[${nombres(e)}%s',${datos(e)}%s]"
	}
	
	/**
	 * 
	 * Muestra un diagrama columnas de barras
	 * @pre La lista de nombresDatos tiene tiene longitud dos
	 * @param fileOut Fichero de salida
	 * @param title Titulo 
	 * @param nombresDatos Nombres de los datos 
	 * @param nombresColumnas Nombres de las columnas 
	 * @param datos Datos
	 */
	def columnsBarChart[E](fileOut:String,title:String,nombresDatos:List[String],nombresColumna:List[String],datos: List[E]): Unit = {
		var result = FileTools.text("resources/ColumnsBarPattern.html")
		val nTitle = "'"+title+"'"
		val nombresDatosText = "["+nTitle+","+nombresDatos.map(x=>"'"+x+"'").mkString("",",","]")
		val columnasText = List.range(0,datos.length)
									.map(e=>Graphics.columnaBarChart(e,nombresColumna,datos))
									.mkString("",",\n","\n")
		val reglas = Map("title"->nTitle,"nombresDatos"->nombresDatosText,"columnas"->columnasText)
		result = StringTools.transform(result,reglas);
		FileTools.write(fileOut,result);
	}

	private def columnaBarChart[E](e: Integer,nombresColumna: List[String],datos: List[E]): String = {
		f"[${nombresColumna(e)}%s',${datos(e)}%s]"
	}
  
}