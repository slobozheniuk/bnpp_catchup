import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public class RandomLoadBalancerTest {
    @Test
    public void basicTest() {
        Random r = new Random(5);
        LoadBalancerProvider lb = new LoadBalancerProvider(new RandomLoadBalancer(5));
        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");
        Connection c = new Connection("http://c.com");
        lb.addConnection(a);
        lb.addConnection(b);
        lb.addConnection(c);
        List<Connection> connectionList = new ArrayList<>();
        connectionList.add(a);
        connectionList.add(b);
        connectionList.add(c);
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(connectionList.get(r.nextInt(connectionList.size())).getConnectionUri(), lb.getConnection().getConnectionUri());
        }
    }
}
