package base.TGR;

import base.Objects.Ball;
import base.Objects.Bound;
import base.Objects.VOD;
import base.TGR.RuleKey;
import base.TGR.Rules.BallBall;
import base.TGR.Rules.BallBound;
import base.TGR.Rules.Situation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Codex {

    // Attributes:
    private Map<RuleKey, Situation> rules;

    // Constructor:
    public Codex() {
        rules = new HashMap<>();
        setCodex();
    }

    // Public methods:
    public String getRule(VOD objctA, VOD objctB) {
        //Creamos la rule key para distiguir entre rules
        RuleKey key = new RuleKey(objctA, objctB);

        Situation rule = rules.get(key);

        if (rule != null) {
            BiFunction<?, ?, String> function = rule.getFunction();
            return ((BiFunction<VOD, VOD, String>) function).apply(objctA, objctB);
        }
        return null;
    }

    // Private methods:
    private void setCodex() {
        rules.put(new RuleKey(new Ball(), new Ball()), new BallBall());
        rules.put(new RuleKey(new Ball(), new Bound()), new BallBound());
    }
}

//1. Canales (Dentro tendrá un socket + objeto de Clase de control de canal para
// ver si sigue funcionando (esto a través de Pings))