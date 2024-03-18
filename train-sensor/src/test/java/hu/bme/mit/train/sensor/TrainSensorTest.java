package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.*;

public class TrainSensorTest {

    TrainUser mockUser;
    TrainController mockController;
    TrainSensorImpl sensor;

    @Before
    public void init() {
        mockUser = mock(TrainUser.class);
        mockController = mock(TrainController.class);
        sensor = new TrainSensorImpl(mockController,mockUser);
    }
    // Teszteset: 500 fölé növelt sebességnél beriaszt
    @Test
    public void setAlarmState_true_over500_() {
        //when(mockController.setSpeedLimit(500)).thenReturn(null);
        this.sensor.overrideSpeedLimit(501);
        verify(mockUser, times(1)).setAlarmState(true);
        
    }
    // Teszteset: 0 alá csökkentett sebességnél beriaszt
    @Test
    public void setAlarmState_true_under0() {
        sensor.overrideSpeedLimit(-10);
        verify (mockUser,times(1)).setAlarmState(true);
    }
    // Teszteset: A vonat ment 100-zal, lecsökkentettük 50-re a sebességét --> beriaszt
    @Test
    public void setAlarmState_true_100to50() {
        when(mockController.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(50);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    // Teszteset: A vonat ment 100-zal és 51-re csökkentettük a sebességét --> nem riaszt
    @Test
    public void setAlarmState_false_100to51() {
        when(mockController.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(51);
        verify(mockUser, times(0)).setAlarmState(true);
    }
}