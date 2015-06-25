package week3.trees.bandwidthmanager;

import java.util.HashMap;

public class BandwidthManager {

	private static PriorityQueue queue = new PriorityQueue(100);
	private static HashMap<String, Integer> protocolPriorities;

	static {
		protocolPriorities = new HashMap<String, Integer>();
		protocolPriorities.put("ICMP", 1);
		protocolPriorities.put("UDP", 2);
		protocolPriorities.put("RTM", 3);
		protocolPriorities.put("IGMP", 4);
		protocolPriorities.put("DNS", 5);
		protocolPriorities.put("TCP", 6);
	}

	// receives a packet with specified protocol and payload
	public static void rcv(String protocol, String payload) {
		if (protocolPriorities.get(protocol) == null) {
			throw new IllegalArgumentException("Invalid command");
		}

		queue.insert(payload, protocolPriorities.get(protocol));
	}

	// returns the payload of the packet which should be sent
	public static String send() {
		if (!queue.isEmpty()) {

			String payload = queue.getMin();
			queue.removeMin();
			return payload;
		}

		return "Nothing to send!";
	}

}
