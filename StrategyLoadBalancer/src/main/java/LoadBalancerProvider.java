import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Slobozheniuk on 15 0121.
 */
public class LoadBalancerProvider {
    private final LoadBalancer loadBalancer;
    private List<Connection> connections = new ArrayList<>();

    public LoadBalancerProvider(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public Connection getConnection() {
        return loadBalancer.getConnection(connections);
    }

    public LoadBalancer getImpl() {
        return loadBalancer;
    }
}
