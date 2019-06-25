package us.lsi.sevici



object Pojo{
  
case class Network(
	license: License,
	name:String,
	location:Location,
	href:String,
	id: String,
	source:String,
	stations: List[Station]
	)
	
case class Networks(
	networks: List[Network]
	)

case class Station(
	free_bikes: String,
	extra:Extra,
	latitude:String,
	name:String,
	id:String,
	empty_slots:String,
	longitude:String,
	timestamp:String
	)
	
case class Extra(
	uid:String,
	slots:String,
	address:String,
	bonus:String,
	last_update:String,
	banking:String
	)
	
case class OneNetwork(
	network: Network
	)

case class Location(
	country:String,
	city:String,
	latitude:String,
	longitude:String
	)
	
case class License(
	name: String,
	url: String
	)
}
