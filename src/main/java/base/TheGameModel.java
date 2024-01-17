package base;

import base.DTO.VectorDTO;
import base.Objects.Ball;
import base.Objects.Bound;
import base.Objects.VOD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TheGameModel {

    // Attributes:
    private final int width, height;
    private final ArrayList<VOD> dynamicObjects;

    private final TheGameController gameController;

    // Constructor:
    public TheGameModel(int width, int height, TheGameController lc) {
        this.width = width;
        this.height = height;
        this.dynamicObjects = new ArrayList<>();
        createDefaultBounds();

        this.gameController = lc;
    }

    // Getter:
    public ArrayList<VOD> getDynamicObjects() {
        return dynamicObjects;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // Public methods:
    public void createBall(int x, int y) {
        Ball ball = new Ball(x, y, this);
        dynamicObjects.add(ball);

        Thread thread = new Thread(ball);
        thread.start();
    }
    public void collideDetection(VOD object, VectorDTO newCoords) {
        //se obtiene el objecto con el que se va a colisionar en las próximas coordenadas
        VOD vod = isColliding(object, newCoords);

        if (vod != null) {
            String actions = gameController.solveSituation(object, vod);
            executeInstructions(actions, object, vod, newCoords);
        } else {
            object.nextMove((int) newCoords.getX(), (int) newCoords.getY());
        }
    }

    // Private methods:
    private void createDefaultBounds() {
        Bound N = new Bound(0, 0, width, 2);
        Bound S = new Bound(0, height - 2, width, height);
        Bound E = new Bound(width - 2, 0, width, height);
        Bound W = new Bound(0, 0, 2, height);

        dynamicObjects.add(N);
        dynamicObjects.add(S);
        dynamicObjects.add(E);
        dynamicObjects.add(W);
    }
    private VOD isColliding(VOD object, VectorDTO newCoords) {
        for (VOD vod : dynamicObjects) {
            if (vod != object) {
                if (object.getFutureHitbox(newCoords).intersects(vod.getHitbox())) {
                    return vod;
                }
            }
        }
        return null;
    }
    private void executeInstructions(String actions, VOD objectA, VOD objectB, VectorDTO newCoords) {
        if (actions != null) {
            Map<String, VOD> objects = new HashMap<>();
            objects.put("objectA", objectA);
            objects.put("objectB", objectB);

            String[] individualActions = actions.split("/");

            for (String action : individualActions) {

                String[] parts = action.split(":");
                VOD object = objects.get(parts[0]);
                String objectAction = parts[1];
                if (object != null) {
                    executeAction(object, objectAction, newCoords);
                }
            }
        }
    }
    private void executeAction(VOD object, String action, VectorDTO newCoords) {
        switch (action) {
            case "bounce(Ball)" -> object.bounce("Ball", newCoords);
            case "bounce(Bound)" -> object.bounce("Bounds", newCoords);
            case "terminate()" -> {
                Ball b = (Ball) object;
                b.setState(1);
                dynamicObjects.remove(object);
            }
        }
    }
}