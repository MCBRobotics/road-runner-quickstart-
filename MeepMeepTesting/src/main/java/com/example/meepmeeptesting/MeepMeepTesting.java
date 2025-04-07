package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)



                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(24, -64.25, Math.toRadians(270)))
                        .setReversed(true)
                        //.forward(25)
                        //.strafeRight(36)
                        //.turn(Math.toRadians(90))
                        //.strafeRight(50) //finish
                        .splineToLinearHeading(new Pose2d(3, -33, Math.toRadians(270)), Math.toRadians(90)) // park
                        //.splineTo(new Vector2d(9, -10), 0)
                        //.splineTo(new Vector2d(10, 15), 0)
//                        .forward(30)
//                        .turn(Math.toRadians(90))
//                        .forward(30)
//                        .turn(Math.toRadians(90))
//                        .forward(30)
//                        .turn(Math.toRadians(90))
//                        .forward(30)
                        //.turn(Math.toRadians(90))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}