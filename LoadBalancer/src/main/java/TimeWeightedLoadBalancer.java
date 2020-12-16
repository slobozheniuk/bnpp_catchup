import java.util.*;

/**
 * Created by Evgeniy Slobozheniuk on 17-Dec-20.
 */
public class TimeWeightedLoadBalancer extends LoadBalancer {
    @Override
    public void addConnection(Connection connection) {
        connection.updateDelay();
        connections.add(connection);
    }

    @Override
    public Connection getConnection() {
        if (connections.size() == 0) {
            throw new IllegalStateException("No connections in the pool");
        }
        connections.forEach(Connection::updateDelay); //TODO: TimerTask to update not every call, but once fixed time elapsed
        return connections.stream().min(Connection::compareTo).get(); //TODO: Not really elegant, everytime we look through all objects
    }
}
