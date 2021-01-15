import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Slobozheniuk on 15 0121.
 */
public interface LoadBalancer {
    Connection getConnection(List<Connection> connections);
}
