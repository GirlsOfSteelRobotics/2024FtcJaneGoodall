package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

@TeleOp
public class FieldRelativeDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        IntakeServo intakeServo = new IntakeServo(hardwareMap);
        Pivot armPivot = new Pivot(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            drive.fieldRelativeDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

            drive.updatePoseEstimate();

            Pose2d pose = drive.getPose();

            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.addData("angle", armPivot.getAngle());
            telemetry.update();

            if(gamepad1.start && gamepad1.back) {
                drive.zeroWheels();
            }

            //intake
            if(gamepad1.right_trigger > 0.5) {
                intakeServo.intakeIn();
            }
            else if (gamepad1.a) {
                intakeServo.intakeOut();
            }
            else {
                intakeServo.intakeStop();
            }

            //arm pivot
            if (gamepad1.dpad_up) {
                armPivot.armUp();
            }
            else if (gamepad1.dpad_down) {
                armPivot.armDown();
            }
            else {
                armPivot.armStop();
            }


        }



    }
}
