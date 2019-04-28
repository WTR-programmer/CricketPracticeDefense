package org.usfirst.frc5124.CricketPracticeDefense;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.usfirst.frc5124.CricketPracticeDefense.commands.*;
import org.usfirst.frc5124.CricketPracticeDefense.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static DriveTrain driveTrain;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        driveTrain = new DriveTrain();
        oi = new OI();

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Auto mode", chooser);

        new Thread(() -> {
            UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
            camera1.setResolution(320, 240);
            UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
            camera2.setResolution(320, 240);
            
            CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
            CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
            CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 640, 480);
            
            Mat image = new Mat();
            
            while(!Thread.interrupted()) {
                if(oi.getDriver().getRawButton(1)){
                  cvSink1.grabFrame(image);
                } else{
                  cvSink2.grabFrame(image);
                }
                
                outputStream.putFrame(image);
            }
        }).start();

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        // SmartDashboard.putNumber("PDP Current port 4 ", Robot.driveTrain.getPDPCurrent(4));
        // SmartDashboard.putNumber("PDP Current port 5 ", Robot.driveTrain.getPDPCurrent(5));
        // SmartDashboard.putNumber("PDP Voltage", Robot.driveTrain.getPDPVoltage());

        // driveTrain.drive();

    }
}
