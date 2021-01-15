import java.util.List;

/**
 * Created by Evgeniy Slobozheniuk on 15 0121.
 */
public class RoundRobinLoadBalancer implements LoadBalancer {
    int counter = -1;

    @Override
    public Connection getConnection(List<Connection> connections) {
        if (connections.size() == 0) {
            throw new IllegalStateException("No connections in the pool");
        }
        counter++;
        if (counter == connections.size()) {
            counter = 0;
        }
        return connections.get(counter);
    }
}
