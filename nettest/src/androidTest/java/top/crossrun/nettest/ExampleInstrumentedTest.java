package top.crossrun.nettest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.KVParam;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("top.crossrun.nettest", appContext.getPackageName());
    }

    @Test
    public void useAppContextApp() {
        // Context of the app under test.

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("top.crossrun.nettest", appContext.getPackageName());
    }
}
