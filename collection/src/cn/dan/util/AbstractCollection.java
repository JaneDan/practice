package cn.dan.util;

public abstract class AbstractCollection<E> implements Collection<E> {
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

}
