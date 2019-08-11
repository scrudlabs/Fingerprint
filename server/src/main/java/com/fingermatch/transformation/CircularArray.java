package com.fingermatch.transformation;

public class CircularArray {
	public Object[] array;
	public int head;
	public int size;

	public CircularArray(int capacity) {
		array = new Object[capacity];
	}

	public void validateItemIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
	}

	public void validateCursorIndex(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
	}

	public int location(int index) {
		return head + index < array.length ? head + index : head + index - array.length;
	}

	public void enlarge() {
		Object[] enlarged = new Object[2 * array.length];
		for (int i = 0; i < size; ++i)
			enlarged[i] = array[location(i)];
		array = enlarged;
		head = 0;
	}

	public Object get(int index) {
		validateItemIndex(index);
		return array[location(index)];
	}

	public void set(int index, Object item) {
		validateItemIndex(index);
		array[location(index)] = item;
	}

	public void move(int from, int to, int length) {
		if (from < to) {
			for (int i = length - 1; i >= 0; --i)
				set(to + i, get(from + i));
		} else if (from > to) {
			for (int i = 0; i < length; ++i)
				set(to + i, get(from + i));
		}
	}

	public void insert(int index, int amount) {
		validateCursorIndex(index);
		if (amount < 0)
			throw new IllegalArgumentException();
		while (size + amount > array.length)
			enlarge();
		if (2 * index >= size) {
			size += amount;
			move(index, index + amount, size - amount - index);
		} else {
			head -= amount;
			size += amount;
			if (head < 0)
				head += array.length;
			move(amount, 0, index);
		}
		for (int i = 0; i < amount; ++i)
			set(index + i, null);
	}

	public void remove(int index, int amount) {
		validateCursorIndex(index);
		if (amount < 0)
			throw new IllegalArgumentException();
		validateCursorIndex(index + amount);
		if (2 * index >= size - amount) {
			move(index + amount, index, size - amount - index);
			for (int i = 0; i < amount; ++i)
				set(size - i - 1, null);
			size -= amount;
		} else {
			move(0, amount, index);
			for (int i = 0; i < amount; ++i)
				set(i, null);
			head += amount;
			size -= amount;
			if (head >= array.length)
				head -= array.length;
		}
	}
}
