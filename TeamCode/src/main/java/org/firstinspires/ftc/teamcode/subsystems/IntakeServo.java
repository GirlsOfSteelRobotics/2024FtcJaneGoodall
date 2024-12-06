package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeServo {
    CRServo continuousRotationServo;

    public IntakeServo(HardwareMap hardwareMap) {
        continuousRotationServo = hardwareMap.get(CRServo.class, "intakeServo");
    }

    public void intakeIn() {
        continuousRotationServo.setPower(1);
    }

    public void intakeOut() {
        continuousRotationServo.setPower(-1);
    }

    public void intakeStop() {
        continuousRotationServo.setPower(0);
    }

}
