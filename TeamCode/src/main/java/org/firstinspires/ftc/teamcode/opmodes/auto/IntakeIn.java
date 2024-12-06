package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;
@Autonomous
public class IntakeIn extends LinearOpMode {
    public void runOpMode() {
        IntakeServo intakeServo = new IntakeServo(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            intakeServo.intakeIn();
        }

    }
}
