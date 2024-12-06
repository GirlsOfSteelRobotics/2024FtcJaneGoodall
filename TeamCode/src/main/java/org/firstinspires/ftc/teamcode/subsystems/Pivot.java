package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Pivot {
    DcMotorSimple armPivotMotor;
    OverflowEncoder encoder;

    public static double DEGREES_PER_TICK = 1;

    public Pivot(HardwareMap hardwareMap) {
        armPivotMotor = hardwareMap.get(DcMotorSimple.class, "armPivotMotor");

        DcMotorEx hackEncoderMotor = hardwareMap.get(DcMotorEx.class, "rightFront");
        encoder = new OverflowEncoder(new RawEncoder(hackEncoderMotor));
    }

    public void armUp() {
        armPivotMotor.setPower(-1);
    }
    public void armDown() {
        armPivotMotor.setPower(1);
    }

    public void armStop() {
        armPivotMotor.setPower(0);
    }

    public double getAngle() {
        PositionVelocityPair ticks = encoder.getPositionAndVelocity();
        return ticks.position * DEGREES_PER_TICK;
    }

    public void goToAngle(double angle) {
        
    }
}
