package base.TGR.Rules;

import java.util.function.BiFunction;

public interface Situation {
    BiFunction<?, ?, String> getFunction();
}