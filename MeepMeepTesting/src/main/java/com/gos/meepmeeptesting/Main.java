package com.gos.meepmeeptesting;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.gos.meepmeeptesting.FakeCommands.CombinedActions;
import com.gos.meepmeeptesting.FakeCommands.ArmToAngleAction;
import com.gos.meepmeeptesting.FakeCommands.Pivot;

import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        Object intake = null;
        Object pivot = null;


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -61, Math.toRadians(-90)))
                // START COPY AND PASTE
                .setReversed(true)
                .splineTo(new Vector2d(-53,-53), Math.toRadians(-135))
                .stopAndAdd(CombinedActions.createScorePieceInLowBasketAction(pivot, intake))
                .strafeToSplineHeading(new Vector2d(-44, -41), Math.toRadians(0))
                .stopAndAdd(new ArmToAngleAction(pivot, Pivot.INTAKE_ANGLE))
                .strafeTo(new Vector2d(-44, -27))
                .strafeTo(new Vector2d(-55, -27))
                .afterTime(0.0, CombinedActions.createIntakePieceAction(pivot, intake, 7))
                // END COPY AND PAS





//                .turn(Math.toRadians(90))
//                .lineToY(30)
//                .turn(Math.toRadians(90))
//                .lineToX(0)
//                .turn(Math.toRadians(90))
//                .lineToY(0)
//                .turn(Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}