import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;
import p06_TirePressureMonitoringSystem.Sensor;

public class AlarmTest {
  // 1. гума с нормално налягане: (17; 21)
    // мокване означава ние да си създаваме стойности
    @Test
    public void testAlarmWithNormalValue() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn( 19.3);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }

    //2. гума с ниско налягане: < 17 аларма е вклучена
    @Test
    public void testAlarmWithLowerValue() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn( 15.3);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }
    //3. гума с по високо налягане
    @Test
    public void testAlarmWithHigherValue() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn( 24.3);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }


}
