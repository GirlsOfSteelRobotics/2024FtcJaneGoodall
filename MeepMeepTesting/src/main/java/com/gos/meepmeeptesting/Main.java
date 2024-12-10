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

import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        Object intake = null;
        Object pivot = null;

        class AutoIntakeAction implements Action {
            public AutoIntakeAction(Object intake, Object pivot) {
            }

            @Override
            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                return false;
            }
        }
        class ScoreIntoLowBasket implements Action {
            public ScoreIntoLowBasket(Object intake, Object pivot) {
            }

            @Override
            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                return false;
            }
        }

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -61, Math.toRadians(-90)))
                // START COPY AND PASTE
                .setReversed(true)
                .splineTo(new Vector2d(-55,-55), Math.toRadians(-135))
//                .stopAndAdd(new AutoIntakeAction(intake, pivot))
                .stopAndAdd(new ScoreIntoLowBasket(intake, pivot))
                // END COPY AND PASTE





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