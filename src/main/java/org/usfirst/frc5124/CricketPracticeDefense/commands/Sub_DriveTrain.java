package org.usfirst.frc5124.CricketPracticeDefense.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.CricketPracticeDefense.Robot;

/**
 *
 */
public class Sub_DriveTrain extends Command {

    public Sub_DriveTrain() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double x = Robot.oi.getDriver().getX();
        double y = Robot.oi.getDriver().getY();
        double z = Robot.oi.getDriver().getZ();
        // x = deadZone(x);
        // y = deadZone(y);
        // z = deadZone(z);

        double max = x;

        if (Math.abs(z) > Math.abs(x)) {
            max = z;
        }

        // Robot.driveTrain.arcadeDrive(y, max);

        //z = Math.abs(x) > Math.abs(z) ? x : z;
        // Robot.driveTrain.arcadeDrive(y, z);

        // Robot.driveTrain.drive();
        SmartDashboard.putNumber("Runtime:", timeSinceInitialized());
        SmartDashboard.putNumber("Pot Value", Robot.driveTrain.getPot());
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

    public static double deadZone (double raw) {
        return Math.abs(raw) < 0.12 ? 0 : raw;
    }

}
