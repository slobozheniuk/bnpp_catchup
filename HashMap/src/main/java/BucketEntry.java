/**
 * Created by Evgeniy Slobozheniuk on 15 0121.
 */
public class BucketEntry<K, V> {
    private final K key;
    private final V value;

    public BucketEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

}
