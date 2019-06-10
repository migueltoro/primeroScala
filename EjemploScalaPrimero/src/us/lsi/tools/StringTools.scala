package us.lsi.tools

import java.util.regex.{Pattern,Matcher}

object StringTools {
  
  def transform(in: String, reglas: Map[String,String]): String = {
		var out = in
		for(p <-reglas.keys) {
			val pattern = Pattern.compile("\\{"+p+"\\}")
			out = pattern.matcher(out).replaceAll(reglas(p))
		}
		out;
	}
  
}