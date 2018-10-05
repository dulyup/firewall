import lombok.val;

/**
 * @author lyupingdu
 * @date 10/5/18.
 */
public class Util {

    public long hash(String p, String i) {
        val port = Long.parseLong(p);
        val ip = parseIp(i);
        val low = ip & ~(~0 << 16);
        val high = (ip >> 16) & ~(~0 << 16);
        val length = 255255255L; // length of map
        val address = xor(low, high);
        val hash = xor(address, port);
        return (hash % length);
    }

    private long xor(long low, long high) {
        return low ^ high;
    }

    private Long parseIp(String ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) != '.') {
                sb.append(ip.charAt(i));
            }
        }
        return Long.parseLong(sb.toString());
    }

    public String clean(String s) {
        return s.toLowerCase().trim();
    }
}
