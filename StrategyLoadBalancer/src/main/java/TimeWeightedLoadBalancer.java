import java.util.List;

/**
 * Created by Evgeniy Slobozheniuk on 15 0121.
 */
public class TimeWeightedLoadBalancer implements LoadBalancer {
    @Override
    public Connection getConnection(List<Connection> connections) {
        if (connections.size() == 0) {
            throw new IllegalStateException("No connections in the pool");
        }
        return connections.stream().min(Connection::compareTo).get(); //TODO: Not really elegant, everytime we look through all objects
    }

    public void addFeedback(Connection connection, int respTime) {
        connection.updateDelay(respTime);
    }
}
