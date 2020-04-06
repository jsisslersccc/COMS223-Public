package edu.sussex.coms223.lab10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class BSTTest {
	static BinarySearchTree<Integer> bst = new BinarySearchTree<>();

	@BeforeAll
	static void beforeAll() {
		int[] values = { 8, 6, 16, 4, 10, 20, 2, 9, 12 };
		for (int value : values)
			bst.insert(value);
		BSTUtils.printTree(bst);
	}

	@Test
	void testFindNode() {
		BinaryTree<Integer> n = bst.findNode(12);
		assertNotNull(n);
		assertEquals(12, n.data);
		n = bst.findNode(13);
		assertNull(n);
		n = bst.findNode(2);
		assertNotNull(n);
		assertEquals(2, n.data);
	}

	@Test
	void testDepth() {
		assertEquals(-1, bst.depth(7));
		assertEquals(2, bst.depth(4));
		assertEquals(0, bst.depth(8));
		assertEquals(2, bst.depth(20));
		assertEquals(1, bst.depth(6));
	}

}
