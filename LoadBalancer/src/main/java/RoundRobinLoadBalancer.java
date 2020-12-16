/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public class RoundRobinLoadBalancer extends LoadBalancer {
    int counter = -1;

    @Override
    public Connection getConnection() {
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
