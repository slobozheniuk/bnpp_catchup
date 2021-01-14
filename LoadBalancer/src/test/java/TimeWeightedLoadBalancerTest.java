import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

/**
 * Created by Evgeniy Slobozheniuk on 17-Dec-20.
 */
public class TimeWeightedLoadBalancerTest {
    @Test
    public void basicTest() {
        TimeWeightedLoadBalancer lb = new TimeWeightedLoadBalancer();

        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");

        lb.addConnection(a);
        lb.addConnection(b);

        checkThatLoadBalReturnsConnection(lb, "http://a.com");
    }

    @Test
    public void pingChangedTest() {
        TimeWeightedLoadBalancer lb = new TimeWeightedLoadBalancer();

        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");

        lb.addConnection(a);
        lb.addConnection(b);

        checkThatLoadBalReturnsConnection(lb, "http://a.com");

        lb.addFeedback(a, 100);
        lb.addFeedback(b, 50);

        checkThatLoadBalReturnsConnection(lb, "http://b.com");
    }

    private void checkThatLoadBalReturnsConnection(LoadBalancer lb, String s) {
        for (int i = 0; i < 10; i++) {
            lb.getConnection();
        }
        Assert.assertEquals(s, lb.getConnection().getConnectionUri());
    }
}
