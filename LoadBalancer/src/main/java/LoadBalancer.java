import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public abstract class LoadBalancer<T> {
    List<Connection> connections = new ArrayList<>();

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public abstract Connection getConnection();
}
