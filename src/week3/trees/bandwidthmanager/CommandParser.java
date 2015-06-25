package week3.trees.bandwidthmanager;

public class CommandParser {

	public static void parse(String command) {
		if (command.startsWith("rcv")) {
			receive(command);
		} else if (command.equals("send")) {
			System.out.println(BandwidthManager.send());
		} else {
			throw new IllegalArgumentException("Invalid command");
		}
	}

	private static void receive(String command) {
		String[] array = command.split(" ");
		if (array.length != 3) {
			throw new IllegalArgumentException("Invalid command");
		}

		String protocol = array[1];
		String payload = array[2];
		BandwidthManager.rcv(protocol, payload);
	}

}
