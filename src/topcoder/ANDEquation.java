package topcoder;

public class ANDEquation {

	public static void main(String[] args) {
		int[] input = {2, 3, 7, 19};
		ANDEquation e = new ANDEquation();
		System.out.println(e.restoreY(input));
	}

	public int restoreY(int[] A) {
		for (int i = 0; i < A.length; i++) {
			int result = -1;
			for (int j = 0; j < A.length; j++) {
				if (i != j) {
					if (j < 2) {
						result = A[j];
					} else {
						result &= A[j];
					}
				}
			}
			if (result == A[i]) {
				return result;
			}
		}

		return -1;
	}

}
