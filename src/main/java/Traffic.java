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
    private Integer portL;
    private Integer portR;
    private Long ipL;
    private Long ipR;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traffic traffic = (Traffic) o;

        if (direction != null ? !direction.equals(traffic.direction) : traffic.direction != null) return false;
        if (protocol != null ? !protocol.equals(traffic.protocol) : traffic.protocol != null) return false;
        if (portL != null ? portL > traffic.portL : traffic.portL != null) return false;
        if (portR != null ? portR < traffic.portR : traffic.portR != null) return false;
        if (ipL != null ? ipL > traffic.ipL : traffic.ipL != null) return false;
        return ipR != null ? ipR >= traffic.ipR : traffic.ipR == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + (portL != null ? portL.hashCode() : 0);
        result = 31 * result + (portR != null ? portR.hashCode() : 0);
        result = 31 * result + (ipL != null ? ipL.hashCode() : 0);
        result = 31 * result + (ipR != null ? ipR.hashCode() : 0);
        return result;
    }
}
