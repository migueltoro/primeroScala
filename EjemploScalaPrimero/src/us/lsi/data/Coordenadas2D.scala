package us.lsi.data

class Coordenadas2D(latitudeP: Double, longitudeP: Double) {
  val latitude: Double = latitudeP
	val longitude: Double = longitudeP
	
	def toRadians() : Coordenadas2D = {
		val latitude = Math.toRadians(this.latitude);
		val longitude = Math.toRadians(this.longitude);
		Coordenadas2D.of(latitude, longitude);
	}

	def distance(c: Coordenadas2D): Double = {
		Coordenadas2D.distance(this,c);
	}
	
	def esCercana(c: Coordenadas2D, d: Double): Boolean = {
		Coordenadas2D.distance(this,c) <=d;
	}
	
	override def toString(): String = {
		f"($latitude%f,$longitude%f)"
	}
	
}

object Coordenadas2D {
  
  def of(latitude: Double, longitude: Double): Coordenadas2D = {
    new Coordenadas2D(latitude, longitude)
  }
  
  def distance(c1: Coordenadas2D, c2: Coordenadas2D): Double = {
		val radio_tierra = 6373.0
		val c1R = c1.toRadians()
		val c2R = c2.toRadians()		
		val incLat  = c2R.latitude-c1R.latitude
		val incLong = c2R.longitude-c1R.longitude;
		val a = Math.pow(Math.sin(incLat/2),2)+
				Math.cos(c1R.latitude)*Math.cos(c2R.latitude)*Math.pow(Math.sin(incLong/2),2)
		val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
		radio_tierra*c
	}
  
  
	def center(coordenadas: List[Coordenadas2D]): Coordenadas2D = {
		val averageLat = coordenadas.map(x=>x.latitude).sum/coordenadas.length
		val averageLng = coordenadas.map(x=>x.longitude).sum/coordenadas.length
		Coordenadas2D.of(averageLat,averageLng)
	}

}