package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.ArmToAngleAction;
import org.firstinspires.ftc.teamcode.actions.CombinedActions;
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
                 //START COPY AND PASTE
                .setReversed(true)
                .splineTo(new Vector2d(-53,-53), Math.toRadians(-135))
                .stopAndAdd(CombinedActions.createScorePieceInLowBasketAction(pivot, intake))
                .turnTo(Math.toRadians(-90))
//
                .strafeTo(new Vector2d(-35,-61))
                .strafeTo(new Vector2d(-45,-9))
                .strafeToConstantHeading(new Vector2d(-45,-55))
                .strafeTo(new Vector2d(-60,-60))
                .strafeTo(new Vector2d(-45, - 38))
                .strafeTo(new Vector2d(-45, -9))
                .strafeTo(new Vector2d(-56,-9))
                .strafeTo(new Vector2d(-56,-60))
                .strafeTo(new Vector2d(-56,-40))
                .strafeTo(new Vector2d(-56,-8))
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-53,-8))
                .strafeTo(new Vector2d(-53,-29))
                .strafeTo(new Vector2d(-63,-29))
                .strafeTo(new Vector2d(-63,-60))



                // END COPY AND PASTE
                .build());

        }
    }



