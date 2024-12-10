package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

public class AutoIntakeAction implements Action  {
    IntakeServo m_intake;
    Pivot m_pivot;
    public AutoIntakeAction(IntakeServo intake, Pivot pivot) {
        m_intake = intake;
        m_pivot = pivot;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        boolean isArmAtAngle = m_pivot.goToAngle(Pivot.INTAKE_ANGLE);
        m_intake.intakeIn();

        telemetryPacket.put("Arm At Angle", isArmAtAngle);
        telemetryPacket.put("Arm Angle", m_pivot.getAngle());

        if (isArmAtAngle) {
            m_pivot.armStop();
            m_intake.intakeStop();
        }

        return !isArmAtAngle;
    }
}
