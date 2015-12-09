package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private int tableSize;
	private int size = 0;
	private Entry<K,V>[] table;
	private static final double loadFactor = 0.75;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and
	 * the default load factor 0.75.
	 */
	public SimpleHashMap() {
		this(16);
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor 0.75.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashMap(int capacity) {
		if (capacity > 0) {
			tableSize = capacity;
			table = (Entry<K,V>[]) new Entry[capacity];
		}
	}

	public V get(Object obj) {
		@SuppressWarnings("unchecked")
		K key = (K) obj;
		Entry<K,V> entry = find(index(key), key);
		if (entry != null) return entry.value;
		return null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V put(K key, V value) {
		int index = index(key);
		if (find(index, key) != null) return find(index, key).setValue(value);
		if ((double) (size + 1) / (double) tableSize > loadFactor) {
			rehash();
			index = index(key);
		}
		if (table[index] == null) {
			table[index] = new Entry<K,V>(key, value);
		} else {
			Entry<K, V> cur = table[index];
			while (cur.next != null) cur = cur.next;
			cur.next = new Entry<K,V>(key, value);
		}
		size++;
		return null;
	}

	public V remove(Object obj) {
		@SuppressWarnings("unchecked")
		K key = (K) obj;
		V value = null;
		int index = index(key);
		if (table[index] == null) return null;
		Entry<K,V> cur = table[index];
		if (key.equals(table[index].key)) {
			table[index] = table[index].next;
			value = cur.value;
		} else {
			while (cur != null && cur.next != null) {
				if (key.equals(cur.next.key)) {
					value = cur.next.value;
					cur.next = cur.next.next;
				}
				cur = cur.next;
			}
		}
		if (value != null) size--;
		return value;
	}

	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		Entry<K,V>[] oldTable = table;
		tableSize = 2 * tableSize;
		table = (Entry<K,V>[]) new Entry[tableSize];
		Entry<K,V> cur;
		size = 0;
		for (int i = 0; i < tableSize / 2; i++) {
			cur = oldTable[i];
			while (cur != null) {
				put(cur.key, cur.value);
				cur = cur.next;
			}
		}
	}

	private int index(K key) {
		int index = key.hashCode() % tableSize;
		return index > 0 ? index : -index;
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K,V> cur = table[index];
		while (cur != null) {
			if (key.equals(cur.key)) return cur;
			cur = cur.next;
		}
		return null;
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		Entry<K,V> cur = null;
		for (int i = 0; i < tableSize; i++) {
			cur = table[i];
			sb.append(i + "\t");
			while (cur != null) {
				sb.append(cur.toString() + "  ");
				cur = cur.next;
			}
			sb.append("\n");
		}
		return new String(sb);
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}

		public String toString() {
			return key.toString() + "=" + value.toString();
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V val) {
			V temp = value;
			value = val;
			return temp;
		}
	}

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map1 = new SimpleHashMap<Integer, Integer>();
		for (int i = 1; i < 17; i++) {
			map1.put(i * 16, i);
		}
		String s1 = map1.show();
		System.out.println(s1);
		
		
		
		SimpleHashMap<Integer, Integer> map2 = new SimpleHashMap<Integer, Integer>(10);
		for (int i = 0; i < 31; i++) {
			map2.put(i, i);
		}
		String s2 = map2.show();
		System.out.println(s2);
		
		SimpleHashMap<Integer, Integer> map3 = new SimpleHashMap<Integer, Integer>(10);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
			map3.put(i*j, i);
		}
		String s3 = map3.show();
		System.out.println(s3);
	}
}