package week9.lightswitches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class LightSwitches {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String initialState = reader.readLine().replace(" ", "").replace("on", "1").replace("off", "0");
		int initial = Integer.parseInt(initialState, 2);
		
		int[] switches = new int[17];
		for (int i = 1; i <= 16; i++) {
			switches[i] = Integer.parseInt(reader.readLine().replace(" ", ""), 2);
		}
				
		boolean[] visited = new boolean[(int) Math.pow(2, 16) + 1];
		visited[initial] = true;
		int[] prev = new int[(int) Math.pow(2, 16) + 1];
		prev[initial] = 0;
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		queue.add(initial);
		while(!queue.isEmpty()){
			int current = queue.poll();
			int newValue = 0;
			for (int i = 1; i < 17; i++) {
				newValue = current ^ switches[i];
				if (!visited[newValue]) {					
					queue.add(newValue);
					visited[newValue] = true;
					prev[newValue] = current;
					map.put(newValue, i);
					if (newValue == 0) {
						printResult(prev, newValue, map);
						return;
					}
				}
			}
		}		
	}
	
	private static void printResult(int[] prev, int lastSwitch, HashMap<Integer, Integer> map){
		int saveLast = map.get(lastSwitch);
		Stack<Integer> moves = new Stack<Integer>();
		while(map.containsKey(prev[lastSwitch])){
			lastSwitch = prev[lastSwitch];
			moves.push(map.get(lastSwitch));
		}
		
		while(!moves.isEmpty()){
			System.out.print(moves.pop() + " ");			
		}
		
		System.out.print(saveLast);
	}

}
