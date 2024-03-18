package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import com.google.common.collect.*;
import java.util.*;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table <Date, Integer, Integer> tachograph = HashBasedTable.create(); //date, speed, joystick

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);

		//Abszolút határ
		if (speedLimit < 0 || speedLimit > 500)
			user.setAlarmState(true);
		//Relatív határ
		if (speedLimit*0,5 <= referenceSpeed)
			user.setAlarmState(true);

	}

	@Override
	public void emergencyBreak(){
		overrideSpeedLimit(0);
	}

	@Override
	public void log(){
		this.tachograph.put(new Date(), controller.getReferenceSpeed(), user.getJoystickPosition());
	}

	@Override
	public Table getLog(){
		return tachograph;
	}

}
