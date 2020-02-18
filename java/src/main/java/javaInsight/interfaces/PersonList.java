package javaInsight.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PersonList implements List<Person> {

	List<Person> mList = new ArrayList<>();

	@Override
	public boolean add(Person pers) {
		return mList.add(pers);
	}

	@Override
	public void add(int index, Person element) {
		mList.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends Person> c) {
		return mList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Person> c) {
		return mList.addAll(c);
	}

	@Override
	public void clear() {
		mList.clear();
	}

	@Override
	public boolean contains(Object obj) {
		return mList.contains(obj);
	}

	public boolean contains(CustomObject iObj) {
		for (Person p : mList) {
			if (p.getId() == iObj.getId()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return mList.containsAll(c);
	}

	@Override
	public Person get(int index) {
		return mList.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return mList.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return mList.isEmpty();
	}

	@Override
	public Iterator<Person> iterator() {
		return mList.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return mList.lastIndexOf(o);
	}

	@Override
	public ListIterator<Person> listIterator() {
		return mList.listIterator();
	}

	@Override
	public ListIterator<Person> listIterator(int index) {
		return mList.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return mList.remove(o);
	}

	@Override
	public Person remove(int index) {
		return mList.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return mList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return mList.retainAll(c);
	}

	@Override
	public Person set(int index, Person element) {
		return mList.set(index, element);
	}

	@Override
	public int size() {
		return mList.size();
	}

	@Override
	public List<Person> subList(int fromIndex, int toIndex) {
		return mList.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return mList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return mList.toArray(a);
	}

}
