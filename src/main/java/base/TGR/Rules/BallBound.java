package base.TGR.Rules;

import base.Objects.Ball;
import base.Objects.Bound;

import java.util.function.BiFunction;

public class BallBound implements Situation {

    // Override methods:
    @Override
    public BiFunction<Ball, Bound, String> getFunction() {
        return this::rule;
    }

    // Private methods:
    private String rule(Ball ball, Bound bound) {
        return "objectA:bounce(Bound)/objectB:nothing()";
    }
}