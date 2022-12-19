package hashTableImplementation;

import myArrayListImplementation.MyArrayList;

import java.util.Objects;

public class MyHashNode<K, V> {
    K key;
    V value;
    final int hashCode;

    MyHashNode<K, V> next;

    public MyHashNode(K key, V value, int hashCode){
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
