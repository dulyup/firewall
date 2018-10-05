import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lyupingdu
 * @date 10/5/18.
 */
public class UtilTest {
    @Test
    public void hash() throws Exception {
        Util u = new Util();
        u.hash("81", "191.2.29.15");
        u.hash("1081", "0.0.0.1080");
        u.hash("1080", "0.0.0.1080");
        u.hash("1079", "0.0.0.1080");
        u.hash("65", "192.168.2.20");
        u.hash("30", "0.0.0.1080");
    }

}