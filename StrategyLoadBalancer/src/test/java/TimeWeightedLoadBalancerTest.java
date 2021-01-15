import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Evgeniy Slobozheniuk on 17-Dec-20.
 */
public class TimeWeightedLoadBalancerTest {
    @Test
    public void basicTest() {
        LoadBalancerProvider lb = new LoadBalancerProvider(new TimeWeightedLoadBalancer());

        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");

        lb.addConnection(a);
        lb.addConnection(b);

        checkThatLoadBalReturnsConnection(lb, "http://a.com");
    }

    @Test
    public void pingChangedTest() {
        LoadBalancerProvider lb = new LoadBalancerProvider(new TimeWeightedLoadBalancer());

        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");

        lb.addConnection(a);
        lb.addConnection(b);

        checkThatLoadBalReturnsConnection(lb, "http://a.com");

        ((TimeWeightedLoadBalancer) lb.getImpl()).addFeedback(a, 100);
        ((TimeWeightedLoadBalancer) lb.getImpl()).addFeedback(b, 50);

        checkThatLoadBalReturnsConnection(lb, "http://b.com");
    }

    private void checkThatLoadBalReturnsConnection(LoadBalancerProvider lb, String s) {
        for (int i = 0; i < 10; i++) {
            lb.getConnection();
        }
        Assert.assertEquals(s, lb.getConnection().getConnectionUri());
    }
}
