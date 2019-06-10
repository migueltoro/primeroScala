package us.lsi.data

class Coordenadas3D(latitudeP: Double, longitudeP: Double, altitudeP: Double) {
  val latitude: Double = latitudeP
	val longitude: Double = longitudeP 
	val altitude: Double = altitudeP
	
	
  def to2D(): Coordenadas2D = {
		Coordenadas2D.of(latitude, longitude);
	}
  
  def distance(c: Coordenadas3D): Double = {
		Coordenadas3D.distance(this,c);
	}
  
  override def toString(): String = {
		f"($latitude%f,$longitude%f,$altitude%f)"
	}
  
}

object Coordenadas3D {
  
  def of(latitude: Double, longitude: Double, altitude: Double): Coordenadas3D = {
    new Coordenadas3D(latitude, longitude, altitude)
  }
  
  def distance(c1: Coordenadas3D, c2: Coordenadas3D): Double = {
    val c12D = Coordenadas2D.of(c1.latitude, c1.longitude);
		val c22D = Coordenadas2D.of(c2.latitude, c2.longitude);
		Math.sqrt(Math.pow(Coordenadas2D.distance(c12D,c22D),2)+Math.pow(c1.altitude-c1.altitude,2));
  }
  
}