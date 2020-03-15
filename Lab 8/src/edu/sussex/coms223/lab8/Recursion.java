package edu.sussex.coms223.lab8;

public class Recursion {

	/**
	 * Compute the factorial of the integer parameter n. This is the product of n
	 * times each of its non-zero integer predecessors, i.e. n * (n-1) * (n-2) * ...
	 * 1.
	 *
	 * @param n the value to compute the factorial of
	 * @return the factorial of parameter n
	 */
	static public int factorial(int n) {
		// If n is non-positive, throw an exception
		if (n <= 0)
			throw new IllegalArgumentException("n must be a positive integer");

		// If n is greater than 1, return n * the factorial of n - 1.
		if (n > 1) {
			int factn = n * factorial(n - 1);
			return factn;
		}

		return 1;
	}

	/**
	 * Checks if a string is a palindrome. Per chapter 8, exercise 8.15, a string of
	 * characters is defined as a palindrome if it reads the same both forwards and
	 * backwards. For example, the string "abba" and "madam" are palindromes. The
	 * empty string and a single-character string are considered to be palindromes.
	 * 
	 * Hint 1: to access the first character of a string, use s.charAt(0), to access
	 * the last character of a string, use s.charAt(s.length() - 1).
	 * 
	 * Hint 2: to access a substring of a string, use the method s.substring(int
	 * beginIndex, int endIndex) noting that the substring starts at beginIndex and
	 * ends at endIndex - 1.
	 *
	 * @param s the string to check if palindrome
	 * @throws IllegalArgumentException if null string passed
	 * @return true, if s is palindrome else false
	 */
	static public boolean isPalindrome(String s) {

		// TODO 1: Implement this function!

		return false;
	}

	static class Node<T> {
		T value;
		Node<T> next;

		Node(T value) {
			this.value = value;
			next = null;
		}
	}

	/**
	 * Append newNode at the tail of the list L. See P8.5 in the textbook.
	 *
	 * @param <T>     the generic Node value type
	 * @param L       the head of the list to append
	 * @param newNode the new node
	 * @throws IllegalArgumentException if L or newNode is null
	 */
	static public <T> void insert(Node<T> L, Node<T> newNode) {

		// TODO 2: Implement this function!
		
	}
}
