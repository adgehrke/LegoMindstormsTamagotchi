public class Sensor {
	private String port;
	private SensorType sensorType;
	
	public Sensor(String port, SensorType sensorType){
		this.port = port;
		this.sensorType = sensorType;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
