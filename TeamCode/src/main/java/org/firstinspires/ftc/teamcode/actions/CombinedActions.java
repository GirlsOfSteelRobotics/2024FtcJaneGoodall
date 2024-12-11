package org.firstinspires.ftc.teamcode.actions;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

public class CombinedActions {

    public static Action createScorePieceInLowBasketAction(Pivot pivot, IntakeServo intake) {
        return new SequentialAction(
            new ArmToAngleAction(pivot, Pivot.LOW_BASKET_SCORING_ANGLE),
            new IntakeOutAction(intake, 3),
                new ArmToAngleAction(pivot, 140)
        );
    }

    public static Action createIntakePieceAction(Pivot pivot, IntakeServo intake, double secondsToRunIntake) {
        return new ParallelAction(
                new ArmToAngleAction(pivot, Pivot.INTAKE_ANGLE),
                new IntakeOutAction(intake, secondsToRunIntake)
        );
    }
}
