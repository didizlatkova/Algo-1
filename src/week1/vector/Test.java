package week1.vector;

public class Test {

	public static void main(String[] args) {
		Vector vector = new Vector(2);
		vector.add(3);
		vector.insert(0, 5);
		
		System.out.println(vector.toString());
		
		vector.insert(0, 2);
		System.out.println(vector.toString());
		
		vector.add(3);
		vector.add(4);
		System.out.println(vector.capacity()); // 4
		
		vector.pop();
		System.out.println(vector.size()); // 3
		System.out.println(vector.toString());
		
		vector.remove(1);
		System.out.println(vector.toString());
		vector.add(3);
		vector.add(4);
		vector.add(3);
		vector.add(4);
		vector.add(3);
		vector.add(4);
		vector.add(5);
		System.out.println(vector.toString());
		System.out.println(vector.capacity());
		
		long start1 = System.nanoTime();
		addToBack(vector);
		System.out.println(System.nanoTime() - start1);
		
		long start2 = System.nanoTime();
		addToFront(vector);
		System.out.println(System.nanoTime() - start2);
	}
	
	private static void addToBack(Vector vector){
		for (int i = 0; i < 100; i++) {
			vector.add(42);			
		}
	}

	private static void addToFront(Vector vector){
		for (int i = 0; i < 100; i++) {
			vector.insert(1, 42);			
		}
	}
	
}
