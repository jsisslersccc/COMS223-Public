package edu.sussex.coms223.lab1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
	static List<Integer> l = null;

	@BeforeEach
	void setUp() throws Exception {
		l.clear();
	}

	@Test
	void testAdd() {
		assertEquals(0, l.size());
		assertEquals(true, l.add(0));
		assertEquals(1, l.size());
		assertEquals(0, l.get(0));
		assertThrows(IllegalArgumentException.class, () -> {
			l.add(null);
		});
	}

	@Test
	void testGet() {
		assertEquals(0, l.size());
		assertEquals(true, l.add(0));
		assertEquals(1, l.size());
		assertEquals(0, l.get(0));
		assertThrows(IllegalArgumentException.class, () -> {
			l.add(null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			l.get(-1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			l.get(1);
		});
		l.clear();
		assertThrows(IllegalArgumentException.class, () -> {
			l.get(0);
		});
	}

	@Test
	void testRemove() {
		assertEquals(0, l.size());
		assertEquals(true, l.add(0));
		assertEquals(1, l.size());
		assertEquals(0, l.get(0));
		assertEquals(true, l.remove(0));
		assertEquals(0, l.size());
		assertEquals(false, l.remove(0));
	}

	@Test
	void testPerformance() {
		final int ITERATIONS = 1000;
		for (int i = 0; i < ITERATIONS; i++) {
			final int COUNT = 1000;
			l.clear();

			for (int j = 0; j < COUNT; j++)
				assertEquals(true, l.add(j));

			Random rng = new Random();

			for (int j = 0; j < COUNT; j++) {
				int index = rng.nextInt(COUNT);
				assertEquals(index, l.get(index));
			}

			int removed = 0;
			for (int j = 0; j < COUNT; j++) {
				int index = rng.nextInt(COUNT);
				if (l.remove(index))
					removed++;
			}
			assertEquals(COUNT - removed, l.size());
		}
	}

}
