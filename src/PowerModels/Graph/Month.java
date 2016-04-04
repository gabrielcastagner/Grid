package PowerModels.Graph;

/**
 * Enumeration For months and Annual (ANN)
 */
public enum Month {
	//This Order is IMPORTANT for Parsing, IT MUST remain this way.
	JAN("Jan"), FEB("Feb"), MAR("Mar"), APR("Apr"), MAY("May"), JUN("Jun"), 
	JUL("Jul"), AUG("Aug"), SEP("Sep"), OCT("Oct"), NOV("Nov"), DEC("Dec"), 
	ANN("Ann");

	private String month;

	/**
	 * @param month String expected from parse
	 */
	private Month(String month) {
		this.month = month;
	}

	/**
	 * Name of the month abbreviated
	 * @param month month name
	 * @return Month instance with name month
	 */
	public Month getMonth(String month) {
		//Return corresponding month based on initial characters
		for (Month m : values())
			if (m.month.equalsIgnoreCase(month) || m.month.equalsIgnoreCase(month.substring(0, 3)))
				return m;
		return null;
	}
	
	/**
	 * @return Month instance abbreviation 
	 */
	public String getMonthName(){
		return this.month;
	}
}
