import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public class RoundRobinLoadBalancerTest {
    @Test
    public void basicTest() {
        LoadBalancer lb = new RoundRobinLoadBalancer();
        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");
        lb.addConnection(a);
        lb.addConnection(b);
        Assert.assertEquals("http://a.com", lb.getConnection().getConnectionUri());
        Assert.assertEquals("http://b.com", lb.getConnection().getConnectionUri());
    }

    @Test(expected = IllegalStateException.class)
    public void emptyBalancerTest() {
        LoadBalancer lb = new RoundRobinLoadBalancer();
        lb.getConnection();
    }

    @Test
    public void roundRobinTest() {
        LoadBalancer lb = new RoundRobinLoadBalancer();
        Connection a = new Connection("http://a.com");
        Connection b = new Connection("http://b.com");
        Connection c = new Connection("http://c.com");
        lb.addConnection(a);
        lb.addConnection(b);
        lb.addConnection(c);
        Assert.assertEquals("http://a.com", lb.getConnection().getConnectionUri());
        Assert.assertEquals("http://b.com", lb.getConnection().getConnectionUri());
        Assert.assertEquals("http://c.com", lb.getConnection().getConnectionUri());
        Assert.assertEquals("http://a.com", lb.getConnection().getConnectionUri());
    }
}
