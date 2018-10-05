/**
 * @author lyupingdu
 * @date 10/5/18.
 */
public interface Firewall {

    /**
     *
     * @param direction
     * @param protocol
     * @param port
     * @param ip
     * @return
     */
    public boolean acceptPacket(String direction, String protocol, String port, String ip);


}
