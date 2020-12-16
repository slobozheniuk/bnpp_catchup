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

        Connection a = Mockito.spy(new Connection("http://a.com"));
        Connection b = Mockito.spy(new Connection("http://b.com"));
        Mockito.when(a.ping()).thenReturn(lowestDelay[new Random().nextInt(lowestDelay.length)]);
        Mockito.when(b.ping()).thenReturn(highestDelay[new Random().nextInt(highestDelay.length)]);

        lb.addConnection(a);
        lb.addConnection(b);

        for (int i = 0; i < 10; i++) {
            lb.getConnection();
        }
        Assert.assertEquals("http://a.com", lb.getConnection().getConnectionUri());
    }

    @Test
    public void pingChangedTest() {
        LoadBalancer lb = new TimeWeightedLoadBalancer();

        Connection a = Mockito.spy(new Connection("http://a.com"));
        Connection b = Mockito.spy(new Connection("http://b.com"));
        Mockito.when(a.ping()).thenReturn(lowestDelay[new Random().nextInt(lowestDelay.length)]);
        Mockito.when(b.ping()).thenReturn(highestDelay[new Random().nextInt(highestDelay.length)]);

        lb.addConnection(a);
        lb.addConnection(b);

        for (int i = 0; i < 10; i++) {
            lb.getConnection();
        }
        Assert.assertEquals("http://a.com", lb.getConnection().getConnectionUri());

        Mockito.when(a.ping()).thenReturn(highestDelay[new Random().nextInt(highestDelay.length)]);
        Mockito.when(b.ping()).thenReturn(lowestDelay[new Random().nextInt(lowestDelay.length)]);

        for (int i = 0; i < 10; i++) {
            lb.getConnection();
        }
        Assert.assertEquals("http://b.com", lb.getConnection().getConnectionUri());
    }
}
