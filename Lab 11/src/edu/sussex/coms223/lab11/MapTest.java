package edu.sussex.coms223.lab11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void testPut() {
		Map<String, Integer> m = new HashMap<String, Integer>(16, 0.75f);
		assertEquals(0, m.size());
		assertEquals(null, m.put("1", 1));
		assertEquals(null, m.put("0", 0));
		assertEquals(null, m.put("2", 2));
		assertEquals(0, m.put("0", 0));
		assertEquals(3, m.size());
		assertEquals(0, m.get("0"));
		assertThrows(IllegalArgumentException.class, () -> {
			m.put(null, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			m.put("" + 0, null);
		});
	}

	@Test
	void testRemove() {
		Map<String, Integer> m = new HashMap<String, Integer>(16, 0.75f);
		assertEquals(0, m.size());
		Random rng = new Random();
		Set<Integer> mapped = new HashSet<Integer>();
		int COUNT = 100;
		while (mapped.size() != COUNT) {
			Integer value = rng.nextInt(COUNT);
			m.put(value.toString(), value);
			mapped.add(value);
		}
		while (mapped.size() != 0) {
			Integer value = rng.nextInt(COUNT);
			Integer existing = m.remove(value.toString());
			if (mapped.contains(existing)) {
				assertEquals(value, existing);
				mapped.remove(value);
			} else
				assertNull(existing);
		}
		assertEquals(0, m.size());
	}

	@Test
	void testPerformance() {
		Object[] maps = { new HashMap<String, Integer>(16, 0.75f), new HashMap<String, Integer>(16, Float.MAX_VALUE) };
		long[] timeInMS = new long[2];

		for (int i = 0; i < 2; i++) {
			long startTimeMS = System.currentTimeMillis();

			@SuppressWarnings("unchecked")
			Map<String, Integer> m = (Map<String, Integer>) maps[i];

			final int ITERATIONS = 1000;
			Random rng = new Random();

			for (int j = 0; j < ITERATIONS; j++) {
				m.clear();
				assertEquals(0, m.size());

				final int COUNT = 1000;

				for (int k = 0; k < COUNT; k++) {
					int value = rng.nextInt(COUNT);
					Integer existing = m.put("" + value, value);
					assertTrue(existing == null || existing == value);
				}

				for (int k = 0; k < COUNT; k++) {
					int value = rng.nextInt(COUNT);
					Integer existing = m.get("" + value);
					assertTrue(existing == null || existing == value);
				}

				for (int k = 0; k < COUNT; k++) {
					int value = rng.nextInt(COUNT);
					Integer existing = m.remove("" + value);
					assertTrue(existing == null || existing == value);
				}
			}

			timeInMS[i] = System.currentTimeMillis() - startTimeMS;
			System.out.println("timeInMS=" + timeInMS[i]);
		}

		// Uncomment out to test performance difference after implementing dynamic
		// resizing.
		// assertTrue(timeInMS[0] * 10 < timeInMS[1]);
	}

}
