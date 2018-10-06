import lombok.val;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lyupingdu
 * @date 10/5/18.
 */
public class Util {

    static Set<Long> buildHashValueList(int portL, int portR, Long ipL, Long ipR) {
        Set<Long> hashSet = new HashSet<>();
        for (int i = portL; i <= portR; i++) {
            for (long j = ipL; j <= ipR; j++) {
                hashSet.add(buildHashValue(i, j));
            }
        }
        return hashSet;
    }

    public static long buildHashValue(int port, Long ip) {
        val low = ip & ~(~0 << 16);
        val high = (ip >> 16) & ~(~0 << 16);
        val addr = xor(low, high);
        val hash = xor(addr, port);
        val length = 65536; // length of map
        return (hash % length);
    }

    private static long xor(long low, long high) {
        return low ^ high;
    }

    /**
     * @param ip
     * @return
     */
    static Long[] parseIp(String ip) {
        val ips = parseRange(ip);
        val ipL = ips[0].replace(".", "");
        val ipR = ips[1].replace(".", "");
        return new Long[]{Long.parseLong(ipL), Long.parseLong(ipR)};
    }

    static Integer[] parsePort(String port) {
        val ports = parseRange(port);
        return new Integer[]{Integer.parseInt(ports[0]), Integer.parseInt(ports[1])};
    }

    /**
     * Divide string into two parts by '-'
     *
     * @param s
     * @return
     */
    private static String[] parseRange(String s) {
        if (s == null || s.length() == 0) return new String[2];
        int idx = s.indexOf("-");
        String left;
        String right;
        if (idx != -1) {
            left = s.substring(0, idx);
            right = s.substring(idx + 1);
        } else {
            left = right = s;
        }
        return new String[]{left, right};
    }

    static String toLowerCase(String s) {
        return s.toLowerCase().trim();
    }

}
