package week3.trees.bandwidthmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BandwidthManager {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		
		int n = Integer.parseInt(reader.readLine());		
		String input;
		
		for (int i = 0; i < n; i++) {
			input = reader.readLine();
			CommandParser.parse(input);			
		}
	}

	private static PriorityQueue queue = new PriorityQueue(100000);
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
	
	private static class CommandParser {

		public static void parse(String command) {
			if (command.startsWith("rcv")) {
				receive(command);
			} else if (command.equals("send")) {
				System.out.println(BandwidthManager.send());
			}
		}

		private static void receive(String command) {
			String[] array = command.split(" ");
			String protocol = array[1];
			String payload = array[2];
			BandwidthManager.rcv(protocol, payload);
		}

	}
	
	private static class PriorityQueue {

		private QueueElement[] array;
		private int accommodated = 0;

		public PriorityQueue(int capacity) {
			array = new QueueElement[capacity + 1];
		}

		public String getMin() {
			return array[1].value;
		}

		public void insert(String element, int priority) {
			accommodated++;
			int index = accommodated;
			array[index] = new QueueElement(element, priority);

			while (index / 2 > 0) {
				if (array[index].priority < array[index / 2].priority) {
					switchElements(index, index / 2);
					index /= 2;
				} else {
					break;
				}
			}
		}

		private void switchElements(int i, int j) {
			QueueElement save = array[i];
			array[i] = array[j];
			array[j] = save;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 1; i < array.length; i++) {
				sb.append(array[i].value + ", ");
			}
			sb.append("]");

			return sb.toString();
		}

		public void removeMin() {
			array[1] = array[accommodated];
			array[accommodated] = null;
			accommodated--;

			int index = 1;
			while (index * 2 <= accommodated) {
				if (index * 2 + 1 <= accommodated) {
					if (array[index * 2].priority > array[index * 2 + 1].priority
							&& array[index].priority > array[index * 2 + 1].priority) {
						switchElements(index, index * 2 + 1);
						index = index * 2 + 1;
						continue;
					}
				}

				if (array[index].priority > array[index * 2].priority) {
					switchElements(index, index * 2);
					index *= 2;
				} else {
					break;
				}
			}
		}

		public boolean isEmpty() {
			return accommodated == 0;
		}

	}
	
	private static class QueueElement {

		public String value;
		public int priority;

		public QueueElement(String value, int priority) {
			this.value = value;
			this.priority = priority;
		}

	}

}
