package week4.graphs.closestcofeeshop;

public class Test {

	public static void main(String[] args) {
		ClosestCoffeeShop shop = new ClosestCoffeeShop();

		boolean graph[][] = { { false, true, false, true, false, false },
				{ true, false, true, false, false, false },
				{ false, true, false, false, true, false },
				{ true, false, false, false, false, false },
				{ false, false, true, false, false, true },
				{ false, false, false, false, true, false } };

		boolean[] isCoffeeStore = { false, false, true, false, false, true };

		int startingPoint = 0;

		System.out.println(shop.closestCoffeeStore(graph, isCoffeeStore,
				startingPoint));
	}

}
