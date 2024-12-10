package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Pivot;

@Config
@TeleOp
public class TestingPivot extends LinearOpMode {
    public static double ANGLE = 0;


    @Override
    public void runOpMode() throws InterruptedException {

        Pivot pivot = new Pivot(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.5) {
                pivot.goToAngle(ANGLE);
            }
            else if (gamepad1.dpad_up) {
                pivot.armUp();
            }
            else if (gamepad1.dpad_down) {
                pivot.armDown();
            }
            else {
                pivot.armStop();
            }

            if (gamepad1.a) {
                pivot.zeroEncoder();
            }

            telemetry.addData("Arm Position", pivot.getAngle());
            telemetry.update();
        }

    }
    // console angle -1198
    //actual angle is - 40.8
}
