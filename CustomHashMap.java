import java.util.Objects;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 1;
    private static final int DEFAULT_CAPACITY_MULTIPLIER = 2;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;


    private Entry<K, V>[] buckets;
    private int actualSize = 0;


    public CustomHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public CustomHashMap(int initialCapacity) {
        buckets = new Entry[initialCapacity];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        if (entry == null) {
            buckets[index] = new Entry<>(key, value);
            actualSize++;
        } else {
            while (entry.next != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            if (entry.key.equals(key)) {
                entry.value = value;
            } else {
                entry.next = new Entry<>(key, value);
                actualSize++;
            }
        }

        if (actualSize >= buckets.length * DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    public int size() {
        return actualSize;
    }

    public boolean delete(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        if (entry == null) {
            return false;
        }
        if (entry.key.equals(key)) {
            if (entry.next == null) {
                buckets[index] = null;
            } else {
                buckets[index] = entry.next;
            }
            actualSize--;
            return true;
        }

        Entry<K, V> prev = entry;
        while (entry.next != null) {
            if (entry.key.equals(key)) {
                prev.next = entry.next;
                actualSize--;
                return true;
            }
            prev = entry;
            entry = entry.next;
        }
        if (entry.key.equals(key)) {
            prev.next = null;
            actualSize--;
            return true;
        }

        return false;
    }

    public V getValue(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        if (entry == null) {
            return null;
        }
        if (entry.key.equals(key)) {
            return entry.value;
        }

        while (entry.next != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        if (entry.key.equals(key)) {
            return entry.value;
        }

        return null;
    }

    private int getIndex(K key) {
        return Objects.hash(key) % buckets.length;
    }

    private void rehash() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[oldBuckets.length * DEFAULT_CAPACITY_MULTIPLIER];
        actualSize = 0;
        for (Entry<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.put(1, "One");
        System.out.println(map.getValue(1));
        map.put(2, "Two");
        System.out.println(map.getValue(2));
        System.out.println(map.getValue(3));
        map.put(3, "Three");
        System.out.println(map.getValue(3));

        System.out.println(map.delete(3));
        System.out.println(map.delete(2));
        System.out.println(map.delete(1));
        System.out.println(map.delete(0));
        System.out.println(map.delete(4));
        System.out.println(map.size());
    }
}
