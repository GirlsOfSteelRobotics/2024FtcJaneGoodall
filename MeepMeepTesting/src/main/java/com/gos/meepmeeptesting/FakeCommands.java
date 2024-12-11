package com.gos.meepmeeptesting;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;

import org.jetbrains.annotations.NotNull;

public class FakeCommands {

    public static class CombinedActions {

        public static Action createScorePieceInLowBasketAction(Object pivot, Object intake) {
            return new SleepAction(1);
        }

        public static Action createIntakePieceAction(Object pivot, Object intake, double secondsToRunIntake) {
            return new SleepAction(1);

        }
    }
    
    public static class Pivot {
        public static double INTAKE_ANGLE = 0;
    }

    public static class ArmToAngleAction implements Action {
        public ArmToAngleAction(Object pivot, double intakeAngle) {
        }

        @Override
        public boolean run(@NotNull TelemetryPacket telemetryPacket) {
            return false;
        }
    }
}
