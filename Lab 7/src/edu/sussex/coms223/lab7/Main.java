package edu.sussex.coms223.lab7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class Main benchmarks worst-case search performance of an OrderedList vs.
 * ArrayList in terms of elapsed time. Note that the ratio of computational
 * efficiency O(n)/O(log n) is different than the ratio of elapsed time.
 */
public class Main {
	enum ListType {
		array, linked
	};

	public static void main(String[] args) {
		final int ITERATIONS = 10000;
		final int COUNT = 2000;
		final int LAST_VALUE = COUNT - 1; // This is the worst-case value to search for.

		OrderedList<Integer> olist = new OrderedList<>();

		for (int j = 0; j < COUNT; j++) {
			olist.add(j);
		}

		long startMS = System.currentTimeMillis();

		for (int i = 0; i < ITERATIONS; i++) {
			for (int j = 0; j < COUNT; j++) {
				olist.binarySearch(LAST_VALUE);
			}
		}

		final long oltime = System.currentTimeMillis() - startMS;
		System.out.println("OrderedList search elapsed time: " + oltime + " ms");

		for (ListType listType : ListType.values()) {

			List<Integer> list = listType == ListType.array ? new ArrayList<>() : new LinkedList<>();

			for (int j = 0; j < COUNT; j++) {
				list.add(j);
			}

			startMS = System.currentTimeMillis();

			for (int i = 0; i < ITERATIONS; i++) {
				for (int j = 0; j < COUNT; j++) {
					list.contains(LAST_VALUE);
				}
			}

			final long ltime = System.currentTimeMillis() - startMS;

			System.out.println(list.getClass().getName() + " search elapsed time: " + ltime + " ms");
			System.out.println("elapsed time acceleration: " + ltime / oltime + "x");
		}

		System.out.println("N/LOG N: " + COUNT / log2(COUNT));
	}

	static int log2(int n) {
		int sum = 0;
		while (n != 0) {
			n /= 2;
			sum++;
		}
		return sum;
	}

}
