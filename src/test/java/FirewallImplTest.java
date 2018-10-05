import org.junit.Test;

/**
 * @author lyupingdu
 * @date 10/5/18.
 */
public class FirewallImplTest {
    @Test
    public void acceptPacket() throws Exception {
        String csvPath = getClass().getClassLoader().getResource("rules.csv").getPath();
        FirewallImpl firewallImpl = new FirewallImpl(csvPath);
        firewallImpl.acceptPacket("inbound", "tcp", "80", "192.168.2.20");
        firewallImpl.acceptPacket("outbound", "udp", "81", "191.2.29.15");
        firewallImpl.acceptPacket("inbound", "udp", "65535", "255.255.255.255");
        firewallImpl.acceptPacket("outbound", "tcp", "1", "0.0.0.0");
        firewallImpl.acceptPacket("inbound", "tcp", "1141", "1.0.0.1140");
        firewallImpl.acceptPacket("outbound", "udp", "1081", "0.0.0.1080");
        firewallImpl.acceptPacket("outbound", "tcp", "1080", "0.0.0.1079");
        firewallImpl.acceptPacket("outbound", "tcp", "80", "192.168.2.20");
        firewallImpl.acceptPacket("outbound", "udp", "81", "255.255.255.255");
        firewallImpl.acceptPacket("outbound", "ftp", "81", "255.255.255.255");
    }

}