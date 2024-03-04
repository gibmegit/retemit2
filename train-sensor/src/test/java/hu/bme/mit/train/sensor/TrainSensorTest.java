package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    @Before
    public void before() {
        // TODO Add initializations
        TrainUser trainuser;
    }

    @Test
    public void emergencyBreakTest() {
        trainuser.setJoystickPosition(1);
        trainuser.setJoystickPosition(2);
        trainuser.setJoystickPosition(3);
        emergencyBreak();
        assertEquals(0, trainuser.getSpeedLimit());
    }
}
