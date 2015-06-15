package week2.searching.roots;

public class MyMath {

	public static double sqrt(int number) {
		return binary(number, 1, number, 0.0001);
	}

	private static double binarySearchDummyApproach(int number, double left, double right,
			double interval, boolean precise) {
		if (number < 0) {
			return -1;
		}

		if (number == 0) {
			return 0;
		}

		double diff = right * right - left * left;
		System.out.println("diff r - l " + diff);
		while (left < right) {		
			double middle = Math.floor(left + (right - left) / 2);
			if (precise) {
				middle = left + (right - left) / 2;
			}
			System.out.println(middle);
			double squaredMiddleValue = middle * middle;
			double squaredNextMiddleValue = (middle + 1) * (middle + 1);
			double squaredPreviousMiddleValue = (middle - 1) * (middle - 1);

			if (!precise && squaredMiddleValue == number) {
				return middle;
			}

			System.out.println("squared " + squaredMiddleValue);
			System.out.println("number " + number);
			System.out.println("diff " + Math.abs(squaredMiddleValue - number));
			if (precise && Math.abs(squaredMiddleValue - number) < interval) {
				return middle;
			}

			if (!precise && number > squaredMiddleValue
					&& number < squaredNextMiddleValue) {
				return binarySearchDummyApproach(number, middle, middle + 1, 0.00001, true);
			} else if (!precise && number < squaredMiddleValue
					&& number > squaredPreviousMiddleValue) {
				return binarySearchDummyApproach(number, middle - 1, middle, 0.00001, true);
			} else if (squaredMiddleValue > number) {
				return binarySearchDummyApproach(number, left, middle - interval, interval,
						precise);
			} else {
				return binarySearchDummyApproach(number, middle + interval, right, interval,
						precise);
			}
		}

		return left + (right - left) / 2;
	}
	
	private static double binary(int number, double left, double right, double interval){
		if (number < 0) {
			return -1;
		}

		if (number == 0) {
			return 0;
		}
		
		while (Math.abs(left - right) > interval) {
			double middle = left + (right - left) / 2;
			
			double squaredMiddleValue = middle * middle;
									
			if (squaredMiddleValue > number) {
				return binary(number, left, middle - interval, interval);
			} else {
				return binary(number, middle + interval, right, interval);
			}
		}
		
		return left + (right - left) / 2;
	}

}
