
public class TrafficLightController {      

	enum TrafficLightSetting {
		GREEN, AMBER,  RED
	}

	static TrafficLightSetting color  =
		TrafficLightSetting.valueOf(readLineFromUser().toUpperCase());
	
	public TrafficLightSetting getColor() { return color; }

	public static void main(String[] args) {
		System.out.println("The traffic light is " + color);
	}

	// Simplification, just to make this compile
	static String readLineFromUser() {
		return "amber";
	}
}
