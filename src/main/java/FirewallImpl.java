import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author lyupingdu
 * @date 10/5/18.
 */
public class FirewallImpl implements Firewall{

    private Util util = new Util();
    private Map<Long, LinkedList<Traffic>> inTcp = new HashMap<>();
    private Map<Long, LinkedList<Traffic>> outTcp = new HashMap<>();
    private Map<Long, LinkedList<Traffic>> inUdp = new HashMap<>();
    private Map<Long, LinkedList<Traffic>> outUdp = new HashMap<>();

    /**
     * Read rules from csv file, in which each line contains exactly four columns: direction, protocol, ports, and IP address
     *
     * @param csvPath
     * @throws IOException
     */
    public FirewallImpl(String csvPath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                processOneRecord(csvRecord);
            }
        }
    }

    /**
     * Determine if a packet matches a valid rule
     *
     * @param direction “inbound” or “outbound”
     * @param protocol exactly one of “tcp” or “udp”, all lowercase
     * @param port an integer in the range [1,65535]
     * @param ip a single well-formed IPv4 address
     * @return
     */
    @Override
    public boolean acceptPacket(String direction, String protocol, String port, String ip) {
        val map = getMap(util.clean(direction), util.clean(protocol));
        val rules = map.get(util.hash(port, ip));
        val traffic = new Traffic(direction, protocol, port, ip);
        if (rules != null) {
            for (Traffic rule : rules) {
                if (rule.equals(traffic)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Process one csv record, add rules to their corresponding map
     *
     * @param csvRecord
     */
    private void processOneRecord(CSVRecord csvRecord) {
        val dir = util.clean(csvRecord.get(0));
        val pro = util.clean(csvRecord.get(1));
        val port = csvRecord.get(2);
        val ip = csvRecord.get(3);
        val traffic = new Traffic(dir, pro, port, ip);
        val hash = util.hash(port, ip);
        val map = getMap(dir, pro);
        map.putIfAbsent(hash, new LinkedList<>());
        map.get(hash).add(traffic);
    }

    /**
     * Get corresponding rules according to direction and protocol
     *
     * @param dir
     * @param pro
     * @return
     */
    private Map<Long, LinkedList<Traffic>> getMap(String dir, String pro) {
        Map<Long, LinkedList<Traffic>> map;
        String str = toString(dir, pro);
        switch (str) {
            case "inboundtcp":
                map = inTcp;
                break;
            case "inboundudp":
                map = inUdp;
                break;
            case "outboundtcp":
                map = outTcp;
                break;
            case "outboundudp":
                map = outUdp;
                break;
            default:
                throw new RuntimeException("Invalid direction or protocol");
        }
        return map;
    }

    private String toString(String dir, String pro) {
        return dir + pro;
    }

}
