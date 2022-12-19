class MyMap<K, V> {
    private MyArrayList<MyHashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;

    public MyMap() {
        bucketArray = new MyArrayList<>();
        numBuckets = 10;
        size = 0;

        for (int i = 0; i < 10; i++) {
            bucketArray.add(null);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int hashcode = hashCode(key);
        MyHashNode<K, V> head = bucketArray.get(bucketIndex);
        MyHashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key) && hashcode == head.hashCode)
                break;
            prev = head;
            head = head.next;
        }
        if (head == null)
            return null;
        size--;
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        return head.value;
    }

    public V get(K key) {

        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        MyHashNode<K, V> head = bucketArray.get(bucketIndex);


        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }

        return null;
    }

    public void add(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        MyHashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }


        size++;
        head = bucketArray.get(bucketIndex);
        MyHashNode<K, V> newNode
                = new MyHashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.7) {
            MyArrayList<MyHashNode<K, V>> temp = bucketArray;
            bucketArray = new MyArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            for (MyHashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
