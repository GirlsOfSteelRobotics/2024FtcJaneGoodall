package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Pivot {
    DcMotorSimple armPivotMotor;
    OverflowEncoder encoder;

//    public static double DEGREES_PER_TICK = 1; // (1.9 - 41.6) / (6240 - 3055);
    public static double DEGREES_PER_TICK = (-.8 + 91.6) / (42 - -2036);
    public static double KP = 0.16;
    public static boolean ENFORCE_LIMITS = false;
    public static int LOW_BASKET_SCORING_ANGLE = 158;
    public static double INTAKE_ANGLE = 270;
    public static double LOWER_LIMIT = 0;
    public static double UPPER_LIMIT = 250;
    public static double MAX_PID_POWER = 0.8;

    private static int tickOffset;

    public Pivot(HardwareMap hardwareMap) {
        armPivotMotor = hardwareMap.get(DcMotorSimple.class, "armPivotMotor");

        DcMotorEx hackEncoderMotor = hardwareMap.get(DcMotorEx.class, "rightBack");
        encoder = new OverflowEncoder(new RawEncoder(hackEncoderMotor));
    }

    public void armUp() {
        if (ENFORCE_LIMITS && getAngle()>UPPER_LIMIT) {
            armPivotMotor.setPower(0);
        } else {
            armPivotMotor.setPower(0.5);
        }
    }
    public void armDown() {
        if (ENFORCE_LIMITS && getAngle()<LOWER_LIMIT) {
            armPivotMotor.setPower(0);
        } else {
            armPivotMotor.setPower(-0.5);
        }

    }

    public void armStop() {
        armPivotMotor.setPower(0);
    }

    public double getAngle() {
        PositionVelocityPair ticks = encoder.getPositionAndVelocity();
        return -(ticks.position - tickOffset) * DEGREES_PER_TICK;
    }

    public boolean goToAngle(double angle) {
        double error = getAngle()-angle;
        double power = -KP * error;
        if (power > MAX_PID_POWER) {
            power = MAX_PID_POWER;
        } else if (power < -MAX_PID_POWER) {
            power = -MAX_PID_POWER;
        }
        armPivotMotor.setPower(power);
        if (Math.abs(error) > 0.5) {
            return false;
        }
        else {
            return true;
        }

    }
    public void zeroEncoder() {
        tickOffset = encoder.getPositionAndVelocity().position;
//        encoder.encoder.getController().setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        encoder.encoder.getController().setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
