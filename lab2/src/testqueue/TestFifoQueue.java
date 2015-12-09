package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Iterator;

import queue.FifoQueue;

public class TestFifoQueue {
	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<String> myStringQueue;

	@Before
	public void setUp() throws Exception {
		myIntQueue = new FifoQueue<Integer>();
		myStringQueue = new FifoQueue<String>();
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue = null;
		myStringQueue = null;
	}

	/**
	 * Test if a newly created queue is empty.
	 */
	@Test
	public final void testNewFifoQueue() {
		assertTrue(myIntQueue.isEmpty());
		assertTrue(myIntQueue.size() == 0);
	}

	/** Test a single offer followed by a single peek. */
	@Test
	public final void testPeek() {
		myIntQueue.offer(1);
		int i = myIntQueue.peek();
		assertEquals("peek on queue of size 1", 1, i);
		assertTrue(myIntQueue.size() == 1);
	}

	/**
	 * Test a single offer followed by a single poll.
	 */
	@Test
	public final void testPoll() {
		myIntQueue.offer(1);
		int i = myIntQueue.poll();
		assertEquals("poll on queue of size 1", 1, i);
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
	}

	/**
	 * Test peek of empty queue.
	 */
	@Test
	public final void testPeekOfEmpty() {
		assertEquals("Front of empty queue not null", null, myIntQueue.peek());
	}

	/**
	 * Test poll of empty queue.
	 */
	@Test
	public final void testPollOfEmpty() {
		assertEquals("Poll of empty queue should return null", null, myIntQueue
				.poll());
	}

	/**
	 * Test that implementation works for a queue of strings.
	 */
	@Test
	public final void testStringQueue() {
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
		assertTrue("Wrong size of queue", myStringQueue.size() == 3);
		assertEquals("peek on queue of strings", "First", myStringQueue.peek());
		assertEquals("String First expected", "First", myStringQueue.poll());
		assertEquals("String Second expected", "Second", myStringQueue.poll());
		assertEquals("String Third expected", "Third", myStringQueue.poll());
		assertTrue("Queue of strings should be empty", myStringQueue.isEmpty());
	}

	/**
	 * Test that polling gives elements in right order.
	 */
	@Test
	public final void testOrder() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		for (int i = 1; i <= 5; i++) {
			int k = myIntQueue.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Queue not empty", myIntQueue.isEmpty());
	}
	
	/**
	 * Test that polling all elements gives an empty queue.
	 */
	@Test
	public final void testMakeQueueEmpty() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.poll();
		myIntQueue.poll();
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
		assertTrue("Queue not empty after poll", myIntQueue.isEmpty());
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		assertTrue("Wrong size after offer", myIntQueue.size() == 2);
		for (int i = 3; i <= 4; i++) {
			int k = myIntQueue.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
		assertTrue("Queue not empty after poll", myIntQueue.isEmpty());
	}
	
	@Test
	public final void testQueueIterator() {
		for (int i = 1; i <= 10; i++) {
			myIntQueue.offer(i);
		}
		Iterator<Integer> itr = myIntQueue.iterator();
		int count = 1;
		while (itr.hasNext() && count <= 10) {
			int p = itr.next();
			assertEquals("next() returns wrong element", p, count);
			count++;
		}
	}
	
	@Test
	public final void testEmptyQueueIterator() {
		Iterator<Integer> itr = myIntQueue.iterator();
		assertFalse("hasNext() should be false", itr.hasNext());
		try {
			itr.next();
			fail("Should throw NoSuchElementException");
		} catch (Exception e) {
			assertEquals("next() returns wrong exception-class", NoSuchElementException.class, e.getClass());
		}
	}
	
	@Test
	public final void testQueueIteratorRemoveFromEmpty() {
		Iterator<Integer> itr = myIntQueue.iterator();
		try {
			itr.remove();
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			assertEquals("remove() returns wrong exception-class", UnsupportedOperationException.class, e.getClass());
		}
	}
	
	@Test
	public final void testQueueIteratorRemove() {
		for (int i = 1; i <= 10; i++) {
			myIntQueue.offer(i);
		}
		Iterator<Integer> itr = myIntQueue.iterator();
		itr.remove();
		assertEquals("Does not return expected value", new Integer(2), itr.next());
	}
}
