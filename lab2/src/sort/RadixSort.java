package sort;
import queue.FifoQueue;


public class RadixSort {
	public static void radixSort(int[] a, int maxNbrOfDigits) {
		@SuppressWarnings("unchecked")
		FifoQueue<Integer>[] subQ = (FifoQueue<Integer>[]) new FifoQueue[10];
		
		//Initialize array of queues
		for (int i = 0; i < 10; i++) {
			subQ[i] = new FifoQueue<Integer>();
		}
		
		
		//Initialize and fill nbrQueue
		FifoQueue<Integer> numberQueue = new FifoQueue<Integer>();
		for (int i = 0; i < a.length; i++) {
			numberQueue.offer(a[i]);
		}
		
		
		//sort...
		int number = 0;
		int subQueueIndex = 0;
		for (int currentDigitNbr = 1; currentDigitNbr <= maxNbrOfDigits; currentDigitNbr++) {
			//System.out.println(currentDigitNbr + ": " + maxNbrOfDigits);
			while (!numberQueue.isEmpty()) {
				number = numberQueue.poll();
				subQueueIndex = getDigitNbr(number,currentDigitNbr);
				subQ[subQueueIndex].offer(number);
				System.out.print(number + " ");
			}
			System.out.println();
			for (int i = 0; i < 10; i++) {
				numberQueue.append(subQ[i]);
			}
			System.out.println("Size of numberQ: " + numberQueue.size());
		}
		
		while (!numberQueue.isEmpty()) {
			System.out.print(numberQueue.poll() + " ");
		}
		/*
		//Put back to int-array
		for (int i = 0; i < a.length; i++) {
			a[i] = numberQueue.poll();
		}
		*/
	}
	
	private static int getDigitNbr(int toAnalyze, int nbr) {
		return (int) ((toAnalyze / Math.pow(10, nbr - 1)) % 10);
	}
	
	public static void main(String[] args) {
		//int[] g = { 2, 34, 7, 67, 74, 43, 5, 89, 87 };
		int[] h = { 73, 2 };
		//radixSort(g, 2);
		radixSort(h, 2);
		//for (int i = 0; i < g.length; i++) { System.out.print(g[i] + " "); }
		System.out.println();
		//for (int i = 0; i < h.length; i++) { System.out.print(h[i] + " "); }
		
	}
}
