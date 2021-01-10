/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public class Connection implements Comparable<Connection> {
    private final String connectionUri;
    private int avgDelayMilliseconds;

    public Connection(String connectionUri) {
        this.connectionUri = connectionUri;
        this.avgDelayMilliseconds = 0;
        updateDelay();
    }

    public String getConnectionUri() {
        return connectionUri;
    }

    public void updateDelay() {
        this.avgDelayMilliseconds = (this.avgDelayMilliseconds + ping()) / 2;
    }

    public int ping() {
        return 0; // Here should real ping have to be implemented, for now using Mockito for this
    }

    @Override
    public int compareTo(Connection o) {
        return this.avgDelayMilliseconds - ((Connection) o).avgDelayMilliseconds;
    }
}
