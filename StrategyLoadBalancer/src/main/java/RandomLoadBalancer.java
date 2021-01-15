import java.util.List;
import java.util.Random;

/**
 * Created by Evgeniy Slobozheniuk on 15 0121.
 */
public class RandomLoadBalancer implements LoadBalancer {
    private final Random r;

    public RandomLoadBalancer() {
        r = new Random();
    }

    public RandomLoadBalancer(int seed) {
        r = new Random(seed); // For tests
    }

    @Override
    public Connection getConnection(List<Connection> connections) {
        if (connections.size() == 0) {
            throw new IllegalStateException("No connections in the pool");
        }
        int randomInt = r.nextInt(connections.size());
        return connections.get(randomInt);
    }
}
