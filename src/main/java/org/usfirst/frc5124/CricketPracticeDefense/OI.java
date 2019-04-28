package org.usfirst.frc5124.CricketPracticeDefense;

import org.usfirst.frc5124.CricketPracticeDefense.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick driver;

    public OI() {

        driver = new Joystick(0);

        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Sub_DriveTrain", new Sub_DriveTrain());

        LiveWindow.add(Robot.driveTrain);

    }

    public Joystick getDriver() {
        return driver;
    }

}

