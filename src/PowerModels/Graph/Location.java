package PowerModels.Graph;

public class Location {
	private final double latitude, longitude;
	
	/**
	 * Instantiates a Location ADT with the following params
	 * @param latitude -latitude of the Location 
	 * @param longitude -longitude of the Location
	 */
	public Location(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Instantiates a Location ADT with the following params
	 * @param latitude -latitude of the Location 
	 * @param longitude -longitude of the Location
	 */
	public Location(String latitude, String longitude){
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}

	
	/**
	 * @return -latitude of the Location
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return -longitude of the Location
	 */
	public double getLongitude() {
		return longitude;
	}

	@Override
	public boolean equals(Object obj) {
		//True iff the lat and long values are identical
		if(obj instanceof Location){
			Location o = (Location) obj;
			if(o.getLatitude() == this.getLatitude() && o.getLongitude() == this.getLongitude())
				return true;
		}
		return false;
	}
	/**
	 * checks to see if a node is adjacent to another node
	 * @param obj - the comparing object
	 * @return - true if long or lat is in range, else false
	 */
	public boolean adjacent(Object obj){
		//True iff the node has a long or lat value +1 or -1 from desired node
		if(obj instanceof Location){
			Location o = (Location) obj;
			if(o.getLatitude() == this.getLatitude() +1 || o.getLongitude() == this.getLongitude()+1 ||
					o.getLatitude() == this.getLatitude() -1 || o.getLongitude() == this.getLongitude()-1){
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Gets the kilometer value of the distance between the two points.
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param long2
	 * @return the kilometer value
	 */
	public double Haversine(double lat1i,double lon1i,double lat2i,double lon2i){
		double lat1 = lat1i, lon1 = lon1i,lat2 = lat2i,lon2 = lon2i;
		double R = 6371;
		double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = R*c;
		return distance;
	}
}
