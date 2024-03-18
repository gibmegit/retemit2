package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainUser mockUser;
    TrainController mockController;

    @Before
    public void init() {
        mockUser = mock(DataAccess.class);
        mockController = mock(DataAccess.class)
        sensor = new TrainSensorImpl(mockController,mockUser);
    }
    // Teszteset: 500 fölé növelt sebességnél beriaszt
    @Test
    public void setAlarmState_true_over500_() {
        //when(mockController.setSpeedLimit(500)).thenReturn(null);
        sensor.overrideSpeedLimit(500);
        verify(mockUser, times(1)).setAlarmState(true);
        
    }
    // Teszteset: 0-ra csökkentett sebességnél nem riaszt be
    @Test
    public void setAlarmState_false_equals0() {
        sensor.overrideSpeedLimit(0);
        verify (mockUser,times(0)).setAlarmState(true);
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