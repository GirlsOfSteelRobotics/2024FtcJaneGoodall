package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

public class ScoreIntoLowBasket implements Action  {
    IntakeServo m_intake;
    Pivot m_pivot;
    public final double SECONDS_TO_SCORE = 3;
    public double secondsPast = -1;

    public ScoreIntoLowBasket(IntakeServo intake, Pivot pivot) {
        m_intake = intake;
        m_pivot = pivot;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        boolean isArmAtAngle = m_pivot.goToAngle(Pivot.LOW_BASKET_SCORING_ANGLE);

        double t;
        if (secondsPast < 0) {
            if (isArmAtAngle) {
                secondsPast = Actions.now();
            }
            t = 0;
        } else {
            t = Actions.now() - secondsPast;
        }

        if (isArmAtAngle) {
            m_intake.intakeOut();
        }

        boolean spatPieceOut = false;
        if (t > SECONDS_TO_SCORE) {
            spatPieceOut = true;
        }

        telemetryPacket.put("Arm At Angle", isArmAtAngle);
        telemetryPacket.put("Spat Piece Out", spatPieceOut);
        telemetryPacket.put("t", t);
        telemetryPacket.put("Arm Angle", m_pivot.getAngle());

        if (spatPieceOut) {
            m_pivot.armStop();
            m_intake.intakeStop();
        }

        return !spatPieceOut;
    }
}
