package edu.sussex.coms223.lab9;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Stack;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

	/**
	 * Prints the tree.
	 *
	 * @param <T>  the generic type
	 * @param tree the tree
	 */
	<T> void printTree(BinaryTree<T> tree) {
		printTree(tree.right, true, "");
		System.out.println(tree.data);
		printTree(tree.left, false, "");
	}

	/**
	 * Prints the tree.
	 *
	 * @param <T>     the generic type
	 * @param tree    the tree
	 * @param isRight the is right
	 * @param indent  the indent
	 */
	<T> void printTree(BinaryTree<T> tree, boolean isRight, String indent) {
		if (tree == null)
			return;
		printTree(tree.right, true, indent + (isRight ? "        " : " |      "));
		System.out.print(indent);
		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("----- ");
		System.out.println(tree.data);
		printTree(tree.left, false, indent + (isRight ? " |      " : "        "));
	}

	/**
	 * Clone a binary tree, ignoring parent field and reusing data reference.
	 *
	 * @param <T>    the generic type
	 * @param tree   the tree
	 * @param parent the parent
	 * @return the binary tree
	 */
	<T> BinaryTree<T> clone(final BinaryTree<T> tree) {
		// Base case, return null if tree is empty
		if (tree == null)
			return tree;

		// Create a new root node.
		BinaryTree<T> copy = new BinaryTree<T>();

		// Assign the data by reference.
		copy.setData(tree.data);

		// Recursively clone the left and right subtrees.
		copy.attachLeft(clone(tree.left));
		copy.attachRight(clone(tree.right));

		// Return the copy.
		return copy;
	}

	/**
	 * Modify and return a mirror image of a binary tree by swapping all left and
	 * right subtrees. This implements P9.2 of the textbook.
	 *
	 * @param <T>  the generic type
	 * @param tree the tree
	 */
	<T> BinaryTree<T> mirrorImage(BinaryTree<T> tree) {
		// Base case, return null if tree is empty
		if (tree == null)
			return null;

		// Detach the left and right subtrees.
		BinaryTree<T> left = tree.detachLeft();
		BinaryTree<T> right = tree.detachRight();

		// Re-attach the mirror image of the right subtree as the left, and the mirror
		// image of the left subtree as the right.
		tree.attachLeft(mirrorImage(right));
		tree.attachRight(mirrorImage(left));

		// Return the mirror image of the tree.
		return tree;
	}

	/**
	 * Calculate the height of a binary tree.
	 *
	 * Recursive definition:
	 * 
	 * The height of a empty binary tree (tree == null) is -1. The height of a
	 * non-empty binary tree is one plus the maximum of the heights of the left and
	 * right branches of the tree. This implements programming problem P9.3 in the
	 * textbook.
	 * 
	 * @param <T>  the generic type of tree node data
	 * @param tree the root node of a binary tree
	 * @return the height of the binary tree rooted at tree
	 */
	<T> int height(BinaryTree<T> tree) {

		// TODO 1: Implement this method!

		return 0;
	}

	/**
	 * Return whether or not a tree is height balanced. This is programming problem
	 * P9.4 in the textbook. A binary tree is height balanced if, for every node in
	 * the tree, the height of its left subtree differs from that of its right
	 * subtree by no more than one.
	 * 
	 * The recursive definition of a height balanced binary tree is: A binary tree
	 * is height balanced if it is null or the absolute difference in height between
	 * the left subtree and the right is less than two and both the left and right
	 * subtrees are height balanced.
	 *
	 * @param <T>  the generic type
	 * @param tree the tree
	 * @return true, if tree is height balanced otherwise false
	 */
	<T> boolean heightBalanced(BinaryTree<T> tree) {

		// TODO 2: Implement this function!

		return false;
	}

	/**
	 * Traverse a binary tree and visit each Node in post-order.
	 *
	 * @param root    the root of the binary tree
	 * @param visitor the visitor
	 */
	static void postorder(BinaryTree<String> root, Visitor<String> visitor) {
		if (root != null) {
			postorder(root.left, visitor);
			postorder(root.right, visitor);
			visitor.visit(root);
		}
	}

	/*-
	 * EvaluationVisitor extends the Visitor<String> class and uses a Stack<Integer>
	 * to track the evaluation of an arithmetic expression.
	 * 
	 * You can assume that the data of each node consists of a single-character
	 * string with either an operator value ('+', '-', '*', '/') or an integer
	 * value (e.g. "1", "7").
	 * 
	 * Upon each call to visit, the node data should be inspected. If the node data string
	 * can be parsed as an integer using Integer.parseInt, then simply push that integer
	 * value onto the stack.
	 * 
	 * If the node data is an operator ('+', '-', '*', '/'), then you should pop the two operands
	 * off the stack (right operand first, then left operand), perform the operation, and push the 
	 * result onto the stack.
	 * 
	 * The only error checking expected is that if you encounter a node value that is not
	 * an integer string or a valid operator then you should throw an IllegalArgumentException.
	 * 
	 */
	class EvaluationVisitor extends Visitor<String> {
		Stack<Integer> stack = new Stack<Integer>();
		
		// TODO 3: Optional, for 5 points extra-credit, implement this class and
		// uncomment the unit test below.

	}

	@Test
	void testHeight() throws IOException {
		assertEquals(-1, height(null));
		assertTrue(heightBalanced(null));

		/*-          A
		 */
		BinaryTree<String> tree = new BinaryTree<String>();
		tree.setData("A");
		assertEquals(0, height(tree));
		assertTrue(heightBalanced(tree));

		/*-          A
		 *          /
		 *         B
		 */
		BinaryTree<String> leftsubtree = new BinaryTree<String>();
		leftsubtree.setData("B");
		tree.attachLeft(leftsubtree);
		assertEquals(1, height(tree));
		assertTrue(heightBalanced(tree));

		/*-          A
		 *          /
		 *         B
		 *        /
		 *       D 
		 */
		BinaryTree<String> leftsubsubtree = new BinaryTree<String>();
		leftsubsubtree.setData("D");
		leftsubtree.attachLeft(leftsubsubtree);
		assertEquals(2, height(tree));
		assertFalse(heightBalanced(tree));

		/*-          A
		 *          / \
		 *         B   C
		 *        /
		 *       D 
		 */
		BinaryTree<String> rightsubtree = new BinaryTree<String>();
		rightsubtree.setData("C");
		tree.attachRight(rightsubtree);
		assertEquals(2, height(tree));
		assertTrue(heightBalanced(tree));

		/*-          A
		 *          / \
		 *         B   C
		 *        /     \
		 *       D       E
		 */
		BinaryTree<String> rightsubsubtree = new BinaryTree<String>();
		rightsubsubtree.setData("E");
		rightsubtree.attachRight(rightsubsubtree);
		assertEquals(2, height(tree));
		assertTrue(heightBalanced(tree));

		/*-          A
		 *          / \
		 *         B   C
		 *        /     \
		 *       D       E
		 *      /         \
		 *     F           G
		 */
		BinaryTree<String> leftsubsubsubtree = new BinaryTree<String>();
		leftsubsubsubtree.setData("F");
		leftsubsubtree.attachLeft(leftsubsubsubtree);
		BinaryTree<String> rightsubsubsubtree = new BinaryTree<String>();
		rightsubsubsubtree.setData("G");
		rightsubsubtree.attachLeft(rightsubsubsubtree);
		assertEquals(3, height(tree));
		assertFalse(heightBalanced(tree));

		BinaryTreeDriver.readfile("bintreefile.dat");
		assertEquals(3, height(BinaryTreeDriver.myTree));
		assertFalse(heightBalanced(BinaryTreeDriver.myTree));
	}

	// Uncomment if implementing the extra-credit problem.
	
	/*
	@Test
	void testEvaluationVisitor() throws IOException {
		String[] inorder = { "1", "+", "3", "-", "5", "+", "7", "*", "10" };
		String[] preorder = { "+", "1", "*", "-", "3", "+", "5", "7", "10" };

		BinaryTree<String> tree = BinaryTreeDriver.buildTree(preorder, 0, inorder, 0, preorder.length - 1);
		EvaluationVisitor visitor = new EvaluationVisitor();
		postorder(tree, visitor);
		assertEquals(-89, visitor.stack.pop());
	}
	*/

	@Test
	void testMirrorImage() throws IOException {
		String[] inorder = { "D", "E", "B", "A", "C" };
		String[] preorder = { "A", "B", "D", "E", "C" };

		BinaryTree<String> tree = BinaryTreeDriver.buildTree(preorder, 0, inorder, 0, preorder.length - 1);
		BinaryTree<String> mirrorImage = mirrorImage(clone(tree));
		System.out.println("tree:\n");
		printTree(tree);
		System.out.println("\nmirror image:\n");
		printTree(mirrorImage);

		assertEquals(tree.left.data, mirrorImage.right.data);
		assertEquals(tree.left.left.data, mirrorImage.right.right.data);
		assertEquals(tree.left.left.right.data, mirrorImage.right.right.left.data);
	}

}
