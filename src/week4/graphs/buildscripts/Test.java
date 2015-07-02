package week4.graphs.buildscripts;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfProjects = Integer.parseInt(sc.nextLine());
		String[] projects = sc.nextLine().split(" ");
		String wanted = sc.nextLine();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < numberOfProjects; i++) {
			map.put(projects[i], i);
		}

		boolean graph[][] = new boolean[numberOfProjects][numberOfProjects];

		for (int i = 0; i < projects.length; i++) {
			String[] dependencies = sc.nextLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(dependencies[0]); j++) {
				graph[i][map.get(dependencies[j])] = true;
			}
		}

		BuildScripts scripts = new BuildScripts();
		List<Integer> order = scripts.getBuildOrder(map.get(wanted), graph);

		if (order == null) {
			System.out.print("BUILD ERROR");
		} else {
			for (int i = 0; i < order.size(); i++) {
				System.out.print(projects[order.get(i)] + " ");
			}
		}

		sc.close();
	}

}
