package myArrayListImplementation;

public interface MyList<E> extends java.lang.Iterable<E> {
    public void add(E e);
    public void add(int index, E e);
    public E get(int index);
    public int size();
    public Object set(int index, E e);
    public boolean remove(E e);
    public E remove(int index);
    public int indexOf(E e);

}
