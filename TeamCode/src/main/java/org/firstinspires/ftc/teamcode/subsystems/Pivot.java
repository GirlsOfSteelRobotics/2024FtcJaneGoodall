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
    public static boolean ENFORCE_LIMITS = true;
    public static int LOW_BASKET_SCORING_ANGLE = 155;
    public static double INTAKE_ANGLE = 250;
    public static double LOWER_LIMIT = 0;
    public static double UPPER_LIMIT = 250;

    private int tickOffset;

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

    public void goToAngle(double angle) {
        double error = getAngle()-angle;
        double power = -KP * error;
        armPivotMotor.setPower(power);
    }
    public void zeroEncoder() {
        tickOffset = encoder.getPositionAndVelocity().position;
//        encoder.encoder.getController().setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        encoder.encoder.getController().setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
