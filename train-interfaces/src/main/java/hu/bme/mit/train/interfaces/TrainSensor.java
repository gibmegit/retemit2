package hu.bme.mit.train.interfaces;
import com.google.guava.*;

public interface TrainSensor {

	int getSpeedLimit();
	Table getLog();

	void overrideSpeedLimit(int speedLimit);

	void emergencyBreak();
	void log();

}
