package queue;

public class Testerer {
	
	public static void main(String[] args) {
		FifoQueue<String> q1 = new FifoQueue<String>();
		FifoQueue<String> q2 = new FifoQueue<String>();
		
		q1.offer("1");
		q1.offer("1");
		q1.offer("1");
		q1.offer("1");
		
		q2.offer("2");
		
		System.out.println(q1.peek() + ", with size: " + q1.size());
		System.out.println(q2.peek() + ", with size: " + q2.size());
	}

}
