package edu.sussex.coms223.lab10;

public class BSTUtils {
	/**
	 * Prints the tree.
	 *
	 * @param <T>  the generic type
	 * @param tree the tree
	 */
	static <T extends Comparable<T>> void printTree(BinarySearchTree<T> bst) {
		printTree(bst.tree.right, true, "");
		System.out.println(bst.tree.data);
		printTree(bst.tree.left, false, "");
	}

	/**
	 * Prints the tree.
	 *
	 * @param <T>     the generic type
	 * @param tree    the tree
	 * @param isRight the is right
	 * @param indent  the indent
	 */
	static <T> void printTree(BinaryTree<T> tree, boolean isRight, String indent) {
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
}
