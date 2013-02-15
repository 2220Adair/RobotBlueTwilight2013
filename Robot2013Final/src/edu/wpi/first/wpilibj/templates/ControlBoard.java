/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Alec 
 */
public class ControlBoard {
    private DriveInfo left;
    private DriveInfo right;
    private ShooterInfo shoot;
    private BTController controller;
    private ClimberInfo climber;
    DigitalInput shooter_switch;
    DigitalInput climber_switch;
    DigitalInput dt_switch;
    
    public ControlBoard() {
        // 
        shooter_switch = new DigitalInput(1);
        climber_switch = new DigitalInput(2);
        dt_switch = new DigitalInput(3);
        controller = new BTController();
    }
    
    public void update(){
        
        //MANUAL: currently manual shoot when implemented it will be shoot.canAim for camera targeting 
        shoot.canAim = controller.canAim();
        shoot.canShoot = controller.canShoot();
        shoot.isShooterMotorOn = controller.isShooterMotorOn();
        shoot.isShooterMotorOff = controller.isShooterMotorOff();
        climber.canClimb = controller.canClimb();
        updateCycles();
    }
    public void updateCycles()
    {
        if (left.cycles < 1)
        {
            left.percent = controller.getLMotorSpeed();
            right.percent = controller.getRMotorSpeed();
            left.cycles = 1;
            right.cycles = 1;
            left.shifterSetting = controller.getShifterSetting();
        }
        if (shoot.cycles < 1)
        {
            shoot.windowMotor = controller.getShooterYaw();
        }
    }
    
    public boolean getShooterSetting() {
        if(shooter_switch.get()) {
            return true; // radial shooter
        }
        return false; // linear shooter
    }
    
    public boolean getClimberSetting() {
        if(climber_switch.get()) {
            return true; // high climber
        }
        return false; // low climber
    }
    
    public boolean getDriveTrainSetting() {
        if(dt_switch.get()) {
            return true; // CAN Drive Train
        }
        return false; // PWM Drive Train
    }
     
    public double getShootMotorSpeed() {
        //add slider for adjust speed
        return -1.0;
    }
    
    public DriveInfo getDriveLeft(){
        return left;
    }
    
    public DriveInfo getDriveRight(){
        return right;
    }
    
    public ClimberInfo getClimber(){
        return climber;
    }
    
    public void setDrive(DriveInfo leftTemp, DriveInfo rightTemp){
        left = leftTemp;
        right = rightTemp;
    }
    
    public void setShooter(ShooterInfo shootInfo){
        shoot = shootInfo;
    }
    
    public ShooterInfo getShooter(){
        return shoot;
    }
}
