package cn.dan.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class AbstractCollection<E> implements Collection<E> {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public abstract Iterator<E> iterator();

    public abstract int size();

    protected AbstractCollection() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        boolean result = false;
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    result = true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    result = true;

        }
        return result;
    }

    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            result = add((E) it.next());
        }
        return result;
    }

    public boolean remove(Object o) {
        boolean result = false;
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    result = true;
                    break;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean removeAll(Collection<? extends E> c) {
        boolean result = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    public boolean retainAll(Collection<? extends E> c) {
        boolean result = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.remove();
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < array.length; ++i) {
            if (!it.hasNext()) {
                array = Arrays.copyOf(array, i);
                break;
            }
            array[i] = it.next();
        }

        return it.hasNext() ? finishToArray(array, it) : array;
    }

    public <T> T[] toArray(T[] a) {
        T[] array = a.length >= size()
                ? a : (T[]) Array.newInstance(a.getClass().getComponentType(), size());
        Iterator<E> it = iterator();
        for (int i = 0; i < array.length; ++i) {
            if (!it.hasNext()) {
                if (a == array) {
                    array[i] = null;
                } else {
                    array = Arrays.copyOf(array, i);
                    break;
                }
            }
            array[i] = (T) it.next();
        }
        return it.hasNext() ? finishToArray(array, it) : array;
    }

    private static <T> T[] finishToArray(T[] array, Iterator<?> it) {
        int i = array.length;
        while (it.hasNext()) {
            int cap = array.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                if (newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                array = Arrays.copyOf(array, newCap);
            }
            array[i++] = (T) it.next();
        }
        return (i == array.length) ? array : Arrays.copyOf(array, i);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError
                    ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

}
