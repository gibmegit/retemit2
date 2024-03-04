package hu.bme.mit.train.interfaces;
import com.google.common.collect.*;

public interface TrainSensor {

	int getSpeedLimit();
	Table getLog();

	void overrideSpeedLimit(int speedLimit);

	void emergencyBreak();
	void log();

}
