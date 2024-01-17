package base.TGR.Rules;

import base.Objects.Ball;
import base.TGR.Rules.Situation;

import java.util.function.BiFunction;

public class BallBall implements Situation {

    // Override methods:
    @Override
    public BiFunction<Ball, Ball, String> getFunction() {
        return this::rule;
    }

    // Private methods:
    private String rule(Ball ball1, Ball ball2) {
        return "objectA:bounce(Ball)/objectB:bounce(Ball)";
    }
}