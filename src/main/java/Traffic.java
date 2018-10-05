import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lyupingdu
 * @date 10/5/18.
 */
@Data
@AllArgsConstructor
public class Traffic {

    private String direction;
    private String protocol;
    private String port;
    private String ip;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traffic traffic = (Traffic) o;

        if (direction != null ? !direction.equals(traffic.direction) : traffic.direction != null) return false;
        if (protocol != null ? !protocol.equals(traffic.protocol) : traffic.protocol != null) return false;
        if (port != null ? !port.equals(traffic.port) : traffic.port != null) return false;
        return ip != null ? ip.equals(traffic.ip) : traffic.ip == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Traffic{" +
                "direction='" + direction + '\'' +
                ", protocol='" + protocol + '\'' +
                ", port='" + port + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

}
