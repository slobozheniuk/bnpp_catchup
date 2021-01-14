/**
 * Created by Evgeniy Slobozheniuk on 16-Dec-20.
 */
public class Connection implements Comparable<Connection> {
    private final String connectionUri;
    private int avgDelayMilliseconds;

    public Connection(String connectionUri) {
        this.connectionUri = connectionUri;
        this.avgDelayMilliseconds = 0;
    }

    public String getConnectionUri() {
        return connectionUri;
    }

    public void updateDelay(int respTime) {
        this.avgDelayMilliseconds = (this.avgDelayMilliseconds + respTime) / 2;
    }

    @Override
    public int compareTo(Connection o) {
        return this.avgDelayMilliseconds - o.avgDelayMilliseconds;
    }
}
