import java.util.*;

/**
 * Created by Evgeniy Slobozheniuk on 17-Dec-20.
 */
public class TimeWeightedLoadBalancer extends LoadBalancer {

    @Override
    public Connection getConnection() {
        if (connections.size() == 0) {
            throw new IllegalStateException("No connections in the pool");
        }
        return connections.stream().min(Connection::compareTo).get(); //TODO: Not really elegant, everytime we look through all objects
    }

    public void addFeedback(Connection connection, int respTime) {
        connection.updateDelay(respTime);
    }
}
