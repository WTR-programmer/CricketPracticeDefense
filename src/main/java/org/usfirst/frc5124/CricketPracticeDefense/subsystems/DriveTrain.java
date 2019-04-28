package org.usfirst.frc5124.CricketPracticeDefense.subsystems;


import org.usfirst.frc5124.CricketPracticeDefense.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    private VictorSP left1;
    private VictorSP left2;
    private VictorSP left3;
    private SpeedControllerGroup left;
    private VictorSP right1;
    private VictorSP right2;
    private VictorSP right3;
    private SpeedControllerGroup right;
    private DifferentialDrive diffDrive;
    private AnalogPotentiometer pot;
    // private PowerDistributionPanel pdp = new PowerDistributionPanel();

    public DriveTrain() {
        
        left1 = new VictorSP(3);
        addChild("left1",left1);
        left1.setInverted(false);
        
        left2 = new VictorSP(4);
        addChild("left2",left2);
        left2.setInverted(false);

        left3 = new VictorSP(5);
        addChild("left3",left3);
        left3.setInverted(false);
        
        left = new SpeedControllerGroup(left1, left2, left3);
        addChild("left",left);
        
        
        right1 = new VictorSP(6);
        addChild("right1",right1);
        right1.setInverted(false);
        
        right2 = new VictorSP(7);
        addChild("right2",right2);
        right2.setInverted(false);

        right3 = new VictorSP(8);
        addChild("right3",right3);
        right3.setInverted(false);
        
        right = new SpeedControllerGroup(right1, right2, right3);
        addChild("right",right);
        
        
        diffDrive = new DifferentialDrive(left, right);
        addChild("diffDrive",diffDrive);
        diffDrive.setSafetyEnabled(true);
        diffDrive.setExpiration(0.1);
        diffDrive.setMaxOutput(1.0);

        pot = new AnalogPotentiometer(0);
        addChild("Potentiometer",pot);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Sub_DriveTrain());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Power", left.get());
        SmartDashboard.putNumber("Right Power", right.get());
        SmartDashboard.putNumber("left1", left1.get());
        SmartDashboard.putNumber("left2", left2.get());
        SmartDashboard.putNumber("right1", right1.get());
        SmartDashboard.putNumber("right2", right2.get());
    }

    public void tankDrive (double l, double r) {
        diffDrive.tankDrive(l, r);
    }

    public void arcadeDrive (double xSpeed, double zRotation) {
        diffDrive.arcadeDrive(xSpeed, zRotation);
    }

    public void drive() {
        left1.set(.7);
        left2.set(.7);
        left3.set(.7);
        // right2.set(.7);
    }

    public double getPot () {
        return pot.get();
    }
    // public double getPDPVoltage(){
    //     return pdp.getVoltage();
    // }
    // public double getPDPCurrent(int channel){
    //     return pdp.getCurrent(channel);
    // }

}

