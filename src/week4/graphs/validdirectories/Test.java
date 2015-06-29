package week4.graphs.validdirectories;

public class Test {

	public static void main(String[] args) {

		int graph[][] = { 
				{ 0, 1, 0, 1, 0, 2 }, 
				{ 0, 0, 2, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 2, 0 },
				{ 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0 } };

		ValidDirectories dirs = new ValidDirectories();
		System.out.println(dirs.isValid(graph));
	}
}
