package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.subsystems.Pivot;

public class ArmToAngleAction implements Action {
    private final Pivot m_pivot;
    private final double m_goalAngle;

    public ArmToAngleAction(Pivot pivot, double goalAngle) {
        m_pivot = pivot;
        m_goalAngle = goalAngle;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        boolean isArmAtAngle = m_pivot.goToAngle(m_goalAngle);

        telemetryPacket.put("Arm At Angle", isArmAtAngle);
        telemetryPacket.put("Arm Angle", m_pivot.getAngle());

        if (isArmAtAngle) {
            m_pivot.armStop();
        }

        return !isArmAtAngle;
    }
}
