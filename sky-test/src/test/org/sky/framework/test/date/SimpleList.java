package org.sky.framework.test.date;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author Danmy
 *	实现线程安全的简单List，适用读多写少的场景。
 *
 * @param <E>
 */
public class SimpleList<E> implements List<E>, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final ReadWriteLock lock = new ReentrantReadWriteLock();
	final Lock r = lock.readLock();
	final Lock w = lock.writeLock();
	final List<E> list;

	public SimpleList(List<E> list) {
		this.list = list;
		if (list == null)
			throw new NullPointerException();
	}

	public void clear() {
		w.lock();
		try {
			list.clear();
		} finally {
			w.unlock();
		}
	}

	@Override
	public boolean contains(Object o) {
		r.lock();
		try {
			return list.contains(o);
		} finally {
			r.unlock();
		}
	}

	@Override
	public Iterator<E> iterator() {
		r.lock();
		try {
			return list.iterator();
		} finally {
			r.unlock();
		}
	}

	@Override
	public Object[] toArray() {
		r.lock();
		try {
			return list.toArray();
		} finally {
			r.unlock();
		}
	}

	@Override
	public <T> T[] toArray(T[] a) {
		r.lock();
		try {
			return list.toArray(a);
		} finally {
			r.unlock();
		}
	}

	@Override
	public boolean add(E e) {
		w.lock();
		try {
			return list.add(e);
		} finally {
			w.unlock();
		}
	}

	@Override
	public boolean remove(Object o) {
		w.lock();
		try {
			return list.remove(o);
		} finally {
			w.unlock();
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		r.lock();
		try {
			return list.containsAll(c);
		} finally {
			r.unlock();
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		w.lock();
		try {
			return list.addAll(c);
		} finally {
			w.unlock();
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		w.lock();
		try {
			return list.addAll(index, c);
		} finally {
			w.unlock();
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		w.lock();
		try {
			return list.removeAll(c);
		} finally {
			w.unlock();
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		w.lock();
		try {
			return list.retainAll(c);
		} finally {
			w.unlock();
		}
	}

	@Override
	public E get(int index) {
		r.lock();
		try {
			return list.get(index);
		} finally {
			r.unlock();
		}
	}

	@Override
	public E set(int index, E element) {
		w.lock();
		try {
			return list.set(index, element);
		} finally {
			w.unlock();
		}
	}

	@Override
	public void add(int index, E element) {
		w.lock();
		try {
			list.add(index, element);
		} finally {
			w.unlock();
		}
	}

	@Override
	public E remove(int index) {
		w.lock();
		try {
			return list.remove(index);
		} finally {
			w.unlock();
		}
	}

	@Override
	public int indexOf(Object o) {
		r.lock();
		try {
			return list.indexOf(o);
		} finally {
			r.unlock();
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		r.lock();
		try {
			return list.lastIndexOf(o);
		} finally {
			r.unlock();
		}
	}

	@Override
	public ListIterator<E> listIterator() {
		r.lock();
		try {
			return list.listIterator();
		} finally {
			r.unlock();
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		r.lock();
		try {
			return list.listIterator(index);
		} finally {
			r.unlock();
		}
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		r.lock();
		try {
			return list.subList(fromIndex, toIndex);
		} finally {
			r.unlock();
		}
	}

	@Override
	public int size() {
		r.lock();
		try {
			return list.size();
		} finally {
			r.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		r.lock();
		try {
			return list.isEmpty();
		} finally {
			r.unlock();
		}
	}

}
