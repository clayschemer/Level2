package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}
	
	@Test
	public final void appendEmptyToEmpty() {
		q1.append(q2);
		assertTrue(q1.size() == q2.size());
		assertEquals("Size of q1 after appension", q1.size(), 0);
		assertEquals("Size of q2 after appension", q2.size(), 0);
	}
	
	@Test
	public final void appendEmpty() {
		for (int i = 1; i <= 10; i++) {
			q1.add(i);
		}
		q1.append(q2);
		assertEquals("Size should be 10", q1.size(), 10);
	}
	
	@Test
	public final void appendToEmpty() {
		for (int i = 1; i <= 10; i++) {
			q1.add(i);
		}
		q2.append(q1);
		assertEquals("Size should be 10", q2.size(), 10);
		assertEquals("Size should be 0", q1.size(), 0);
	}
	
	@Test
	public final void testOrderAfterAppend() {
		for (int i = 1; i <= 10; i++) {
			q1.add(i);
		}
		for (int i = 11; i <= 20; i++) {
			q2.add(i);
		}
		q1.append(q2);
		int p = 0;
		for (int i = 1; i <= 20; i++) {
			p = q1.poll();
			assertEquals("Element expected: ", p, i);
		}
	}

}
