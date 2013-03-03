package monitors

import java.sql.Timestamp;

class Status {
	Timestamp statusTime;
	static hasMany = [metrics: Metric]
	static belongsTo = [agent:Agent]
    static constraints = {
		statusTime nullable:false
    }
}
