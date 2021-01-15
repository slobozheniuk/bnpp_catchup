import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Evgeniy Slobozheniuk on 17-Dec-20.
 */
public class HashMap<K, V> {
    private int size;
    private int threshold;
    private final float factor;
    private LinkedList<BucketEntry<K, V>>[] buckets;

    HashMap() {
        size = 0;
        threshold = 10;
        factor = 0.75F;
        buckets = new LinkedList[threshold];
    }

    public V get(K key) {
        int bucketByHash = findBucketByHash(key);
        if (buckets[bucketByHash] != null) {
            for (BucketEntry<K, V> bucketItem : buckets[bucketByHash]) {
                if (bucketItem.getKey().equals(key)) {
                    return bucketItem.getValue();
                }
            }
        }
        return null;
    }

    public void put(K key, V value) {
        int bucketByHash = findBucketByHash(key);
        if (get(key) != null) {
            remove(key);
        }
        if (buckets[bucketByHash] == null) {
            buckets[bucketByHash] = new LinkedList<>();
        }
        buckets[bucketByHash].add(new BucketEntry<>(key, value));
        size++;
    }

    public void remove(K key) {
        int bucketByHash = findBucketByHash(key);
        if (buckets[bucketByHash] != null) {
            int index = 0;
            for (BucketEntry<K, V> bucketItem : buckets[bucketByHash]) {
                if (bucketItem.getKey().equals(key)) {
                    break;
                }
                index++;
            }
            buckets[bucketByHash].remove(index);
            size--;
        }
    }

    private int findBucketByHash(K key) {
        int hash = key.hashCode();
        int step = Integer.MAX_VALUE / threshold;
        return hash / step;
    }

    public int size() {
        return this.size;
    }
}
