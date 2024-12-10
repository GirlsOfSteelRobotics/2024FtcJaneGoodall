package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoIntakeAction;
import org.firstinspires.ftc.teamcode.actions.ScoreIntoLowBasket;
import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

@Autonomous
public class LowBasketAuto extends LinearOpMode {
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(-35, -61, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        IntakeServo intake = new IntakeServo(hardwareMap);
        Pivot pivot = new Pivot(hardwareMap);

        waitForStart();

        Actions.runBlocking(drive.actionBuilder(beginPose)
                // START COPY AND PASTE
                .setReversed(true)
                .splineTo(new Vector2d(-53,-53), Math.toRadians(-135))
//                .stopAndAdd(new AutoIntakeAction(intake, pivot))
                .stopAndAdd(new ScoreIntoLowBasket(intake, pivot))


                // END COPY AND PASTE
                .build());

        }
    }



