package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root = null;
	private int size = 0, height = 0;
	
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() { }

	/**
	 * Inserts the specified element in the tree if no duplicate exists. 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		int oldSize = size;
		if (size == 0) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		add(x, root);
		return size > oldSize;
	}
	
	private void add(E x, BinaryNode<E> n) {
		if (x.compareTo(n.element) < 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
			} else {
				add(x,n.left);
			}
		} else if (x.compareTo(n.element) > 0) {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
			} else {
				add(x, n.right);
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		height = 0;
		if (root != null) {
			treeHeight(root, 0);
		}
		return height + 1;
	}

	private void treeHeight(BinaryNode<E> n, int branchHeight) {
		if (n.isLeaf() && branchHeight > height) {
			height = branchHeight;
		} else if (n.left != null) {
			treeHeight(n.left, branchHeight + 1);
		} else if (n.right != null) {
			treeHeight(n.right, branchHeight + 1);
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree(String s) {
		if (s.equals("preorder")) {
			printPreorder(root);
		} else if (s.equals("inorder")) {
			printInorder(root);
		} else if (s.equals("postorder")) {
			printPostorder(root);
		}
	}

	private void printPreorder(BinaryNode<E> node) {
		if (node != null) {
			printPreorder(node.left);
			printPreorder(node.right);
		}
	}

	private void printInorder(BinaryNode<E> node) {
		if (node != null) {
			printInorder(node.left);
			printInorder(node.right);
		}
	}

	private void printPostorder(BinaryNode<E> node) {
		if (node != null) {
			printPostorder(node.left);
			printPostorder(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	@SuppressWarnings("unchecked")
	public void rebuild() {
		E[] array = (E[]) new Comparable[size];
		toArray(root, array, 0);
		root = null;
		root = buildTree(array, 0, size-1);
	}

	/**
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n != null) {
			index = toArray(n.left, a, index);
			a[index] = n.element;
			index = toArray(n.right, a, index + 1);
		}
		return index;
	}

	/**
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if (first > last) {
			return null;
		} else if (first == last) {
			return new BinaryNode<E>(a[first]);
		}
		int mid = (first + last) / 2;
		BinaryNode<E> n = new BinaryNode<E>(a[mid]);
		n.left = buildTree(a, first, mid - 1);
		n.right = buildTree(a, mid + 1, last);
		return n;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	public void buildString(BinaryNode<E> n, StringBuilder sb) {
		if (n != null) {
			buildString(n.left, sb);
			sb.append(n.element);
			sb.append('\n');
			buildString(n.right, sb);
		}
	}

	public int nbrOfLeaves() {
		return nbrOfLeaves(root);
	}

	private int nbrOfLeaves(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		} else if (n.isLeaf()) {
			return 1;
		} else {
			return nbrOfLeaves(n.left) + nbrOfLeaves(n.right);
		}
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		} 

		private boolean isLeaf() {
			return left == null && right == null;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BSTVisualizer pic = new BSTVisualizer("Tree", 1000, 800);
		
		tree.add(1);
		tree.add(3);
		tree.add(5);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		tree.add(13);
//		
//		tree.add(4);
//		tree.add(2);
//		tree.add(1);
//		tree.add(3);
//		tree.add(6);
//		tree.add(5);
//		tree.add(7);
		
//		tree.add(1);
//		tree.add(2);
//		tree.add(3);
//		tree.add(4);
//		tree.add(5);
//		tree.add(6);
//		tree.add(7);
		
		tree.rebuild();
		
//		String[] orders = {"preorder","inorder","postorder"};
//		for (int i = 0; i < 3; i++) {
//			System.out.println(orders[i] + ":");
//			tree.printTree(orders[i]);
//		}
		pic.drawTree(tree);
		System.out.println("size(): " + tree.size());
		System.out.println("height(): " + tree.height());
//		System.out.println(tree.toString());
		System.out.println("antal löv: " + tree.nbrOfLeaves());
	}
}
