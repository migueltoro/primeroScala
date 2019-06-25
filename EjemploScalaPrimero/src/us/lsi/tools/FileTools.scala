package us.lsi.tools

import scala.io.Source._
import java.io._

object FileTools {
  
  def lines(file: String, enc: String="UTF8"): List[String] = {
    scala.io.Source.fromFile(file,enc=enc).getLines.toList
  }
  
  def text(file: String): String = {
    scala.io.Source.fromFile(file).getLines.mkString("\n")
  }
  
  def write(fileIn: String, text: String): Unit = {
    val file = new File(fileIn)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(text)
    bw.close()
  }
  
}