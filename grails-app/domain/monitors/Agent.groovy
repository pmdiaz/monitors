package monitors

class Agent {

	String agentName;
	Status currentStatus
	static belongsTo = Monitor
	static hasMany = [monitors:Monitor]
	
    static constraints = {
		agentName blank: true
		agentName unique: true
    }
}
