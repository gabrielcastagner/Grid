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
}
