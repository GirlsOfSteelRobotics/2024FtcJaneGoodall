package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Actions;

import org.firstinspires.ftc.teamcode.subsystems.IntakeServo;

public class IntakeOutAction implements Action {
    private final IntakeServo m_intake;
    private final double m_secondsToRun;
    private double m_startTime;
    private boolean m_initialized;

    public IntakeOutAction(IntakeServo intake, double secondsToRun) {
        m_intake = intake;
        m_secondsToRun = secondsToRun;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        m_intake.intakeOut();

        double t;
        if (!m_initialized) {
            m_initialized = true;
            m_startTime = Actions.now();
            t = 0;
        } else {
            t = Actions.now() - m_startTime;
        }

        boolean finished = t < m_secondsToRun;
        if (finished) {
            m_intake.intakeStop();
        }

        return !finished;
    }
}
