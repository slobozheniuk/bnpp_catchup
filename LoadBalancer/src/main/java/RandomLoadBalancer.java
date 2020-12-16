import java.util.Random;

/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public class RandomLoadBalancer extends LoadBalancer {
    private final Random r;

    public RandomLoadBalancer() {
        r = new Random();
    }

    public RandomLoadBalancer(int seed) {
        r = new Random(seed); // For tests
    }

    @Override
    public Connection getConnection() {
        if (connections.size() == 0) {
            throw new IllegalStateException("No connections in the pool");
        }
        int randomInt = r.nextInt(connections.size());
        return connections.get(randomInt);
    }
}
