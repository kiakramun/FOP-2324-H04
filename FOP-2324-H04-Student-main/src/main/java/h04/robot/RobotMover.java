package h04.robot;

import fopbot.Field;
import h04.selection.FieldSelectionListener;
import h04.strategy.MoveStrategy;
import fopbot.Robot;

public class RobotMover implements FieldSelectionListener {
    public Robot[] robots = new Robot[0];
    public MoveStrategy moveStrategy;
    public RobotMover(MoveStrategy moveStrategy){
        this.moveStrategy = moveStrategy;
    }
    public void addRobot(Robot robot) {
        var oldRobots = robots;
        this.robots = new Robot[oldRobots.length + 1];
        for (int i = 0; i < oldRobots.length; i++) {
            robots[i] = oldRobots[i];
        }
        robots[oldRobots.length] = robot;
    }

    @Override
    public void onFieldSelection(Field field) {
        for (Robot r : robots) {
            moveStrategy.start(r, field);
        }
    }
}
