package set;

public class UniqueElements {

	public static void main(String[] args) {
		int[] noOrder = {7,5,3,5,2,2,7};
		int[] inOrder = uniqueElements(noOrder);
		for(int i : inOrder) {
			System.out.println(i);
		}

	}
	
	public static int[] uniqueElements(int[] ints) {
		MaxSet<Integer> nbrs = new MaxSet<Integer>();
		for (int s : ints) {
			nbrs.add(new Integer(s));
		}
		int length = nbrs.size();
		int[] order = new int[length];
		for (int i = 0; i < order.length; i++) {
			order[length - 1] = nbrs.getMax();
			nbrs.remove(nbrs.getMax());
			length--;
		}
		return order;

	}

}
