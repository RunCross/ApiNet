package top.crossrun.net.api;

import org.junit.Test;

import top.crossrun.net.api.param.KVUrlParam;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void post_simple() {
        ApiNet.init(new ApiNet.Builder());
        ApiNet.post(new KVUrlParam());
        assertEquals(4, 2 + 2);
    }
}