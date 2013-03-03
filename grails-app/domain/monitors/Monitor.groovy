package monitors

class Monitor {
	String name;
	static hasMany = [agents:Agent]
    static constraints = {
		name unique: true
		name blank:false
    }
}
