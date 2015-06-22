package week2.binarytrees.kmin;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		// 5 2 3 6 1 4
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(1);
		list.add(4);
		list.add(42);
		
		System.out.println(KMin.kthMinimum(list, 7));
	}

}
