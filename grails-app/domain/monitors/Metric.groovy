package monitors

class Metric {

	String metricCode;
	String metricValue;
	
    static constraints = {
		metricCode blank: false
		metricValue blank:false
    }
}
