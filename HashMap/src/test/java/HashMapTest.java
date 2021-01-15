import jdk.internal.jline.internal.TestAccessible;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Evgeniy Slobozheniuk on 18 1220.
 */
public class HashMapTest {
    @Test
    public void basicPutTest() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Test");
        Assert.assertEquals(1, hashMap.size());
    }

    @Test
    public void basicGetTest() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Test");
        String s = hashMap.get(1);
        Assert.assertEquals("Test", s);
    }

    @Test
    public void basicRemoveTest() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "TestOne");
        Assert.assertEquals("TestOne", hashMap.get(1));
        Assert.assertEquals(1, hashMap.size());
        hashMap.remove(1);
        Assert.assertNull(hashMap.get(1));
        Assert.assertEquals(0, hashMap.size());
    }


    @Test
    public void putDifferentKeysTest() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "TestOne");
        hashMap.put(2, "TestTwo");
        Assert.assertEquals("TestOne", hashMap.get(1));
        Assert.assertEquals("TestTwo", hashMap.get(2));
    }

    @Test
    public void whenExistingKeyPutThenReplaceTest() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "TestOne");
        hashMap.put(1, "TestTwo");
        Assert.assertEquals("TestTwo", hashMap.get(1));
        Assert.assertEquals(1, hashMap.size());
    }
}
