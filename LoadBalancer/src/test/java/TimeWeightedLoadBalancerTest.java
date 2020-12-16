import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

/**
 * Created by Evgeniy Slobozheniuk on 17-Dec-20.
 */
public class TimeWeightedLoadBalancerTest {
    int[] lowestDelay = new int[] {20, 21, 20, 24, 25, 21};
    int[] highestDelay = new int[] {60, 61, 60, 64, 65, 61};

    @Test
    public void basicTest() {
        LoadBalancer lb = new TimeWeightedLoadBalancer();

        Connection a = createConnectionWithDelay(lowestDelay, "http://a.com");
        Connection b = createConnectionWithDelay(highestDelay, "http://b.com");

        lb.addConnection(a);
        lb.addConnection(b);

        checkThatLoadBalReturnsConnection(lb, "http://a.com");
    }

    @Test
    public void pingChangedTest() {
        LoadBalancer lb = new TimeWeightedLoadBalancer();

        Connection a = createConnectionWithDelay(lowestDelay, "http://a.com");
        Connection b = createConnectionWithDelay(highestDelay, "http://b.com");

        lb.addConnection(a);
        lb.addConnection(b);

        checkThatLoadBalReturnsConnection(lb, "http://a.com");

        Mockito.when(a.ping()).thenReturn(highestDelay[new Random().nextInt(highestDelay.length)]);
        Mockito.when(b.ping()).thenReturn(lowestDelay[new Random().nextInt(lowestDelay.length)]);

        checkThatLoadBalReturnsConnection(lb, "http://b.com");
    }

    private void checkThatLoadBalReturnsConnection(LoadBalancer lb, String s) {
        for (int i = 0; i < 10; i++) {
            lb.getConnection();
        }
        Assert.assertEquals(s, lb.getConnection().getConnectionUri());
    }

    private Connection createConnectionWithDelay(int[] delay, String connectionUri) {
        Connection a = Mockito.spy(new Connection(connectionUri));
        Mockito.when(a.ping()).thenReturn(delay[new Random().nextInt(delay.length)]);
        return a;
    }
}
