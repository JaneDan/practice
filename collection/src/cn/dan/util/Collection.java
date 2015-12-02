package cn.dan.util;

public interface Collection<E> extends Iterable<E> {
    Iterator<E> iterator();

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean containsAll(Collection<?> c);

    boolean add(E e);

    boolean addAll(Collection<? extends E> c);

    boolean remove(Object o);

    boolean removeAll(Collection<? extends E> c);

    boolean retainAll(Collection<? extends E> c);

    void clear();

    boolean equals(Object o);

    int hashCode();
}
