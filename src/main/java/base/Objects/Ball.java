package base.Objects;

import base.DTO.CoordinatesDTO;
import base.DTO.VectorDTO;
import base.ENUM.VODState;
import base.TheGameModel;

import static java.lang.Thread.sleep;

import java.awt.*;
import java.util.Random;

public class Ball implements Runnable, VOD {

    // Attributes:

    //posicion
    private CoordinatesDTO coordinates;
    //velocidad
    private VectorDTO speed;
    //aceleracion
    private VectorDTO aceleration;

    private final float maxSpeed;

    private final int diameter;

    private VODState state;

    private final TheGameModel gameModel;

    // Constructors:
    public Ball() {
        maxSpeed = 0;
        diameter = 0;
        gameModel = null;
    }
    public Ball(int x, int y, TheGameModel lm) {
        this.gameModel = lm;
        this.coordinates = new CoordinatesDTO(x, y);
        maxSpeed = 10;
        diameter = 10;
        this.speed = new VectorDTO(new Random().nextFloat(-maxSpeed, maxSpeed), new Random().nextFloat(-maxSpeed, maxSpeed));
        state = VODState.PAUSED;
    }

    // Override methods:
    @Override
    public HitBox getHitbox() {
        return new HitBox(coordinates.getX() - (diameter/2), coordinates.getY() - (diameter/2), coordinates.getX() + (diameter/2), coordinates.getY() + (diameter/2));
    }
    @Override
    public HitBox getFutureHitbox(VectorDTO newCoords){
        return new HitBox((int) newCoords.getX() - (diameter/2), (int) newCoords.getY() - (diameter/2), (int) newCoords.getX() + (diameter/2), (int) newCoords.getY() + (diameter/2));
    }

    @Override
    public void nextMove(int x, int y) {
        gameModel.collideDetection(this, calcNewPosition());
    }

    @Override
    public void bounce(String action, VectorDTO newCoords) {
        switch (action) {
            case "Ball" -> {
                calcBounceBall();
            }
            case "Bounds" -> {
                calcBounceBounds(newCoords);
            }
        }
    }
    @Override
    public void kill() {

    }

    @Override
    public void explode() {

    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(coordinates.getX() - diameter / 2, coordinates.getY() - diameter / 2, diameter, diameter);

        g.setColor(Color.WHITE);
        g.fillOval(coordinates.getX() - diameter / 2, coordinates.getY() - diameter / 2, diameter, diameter);
    }

    // Run:
    @Override
    public void run() {
        setState(0);
        while (!state.equals(VODState.DEAD)) {
            move();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setState(int newState) {
        switch (newState) {
            case 0 -> state = VODState.ALIVE;
            case 1 -> state = VODState.DEAD;
            case 2 -> state = VODState.PAUSED;
        }
    }

    // Private methods:
    private void move(){

    }

    private VectorDTO calcNewPosition() {
        int newX = (int) (coordinates.getX() + speed.getX());
        int newY = (int) (coordinates.getY() + speed.getY());

        return new VectorDTO(newX, newY);
    }
    private void calcBounceBall() {
        speed.setX(-speed.getX());
        speed.setY(-speed.getY());

        setX((int)(coordinates.getX() + speed.getX()));
        setY((int)(coordinates.getY() + speed.getY()));
    }
    private void calcBounceBounds(VectorDTO newCoords) {
        int coordX = (int) newCoords.getX();
        int coordY = (int) newCoords.getY();

        if (coordX - diameter <= 2) {
            speed.setX(-speed.getX());
            setX(2 + diameter);
        }
        if (coordX + diameter >= gameModel.getWidth() - 2) {
            speed.setX(-speed.getX());
            setX(gameModel.getWidth() - 2 - diameter);
        }

        if (coordY - diameter <= 2) {
            speed.setY(-speed.getY());
            setY(2 + diameter);
        }
        if (coordY + diameter >= gameModel.getHeight() - 2) {
            speed.setY(-speed.getY());
            setY(gameModel.getHeight() - 2 - diameter);
        }
    }

    // Setter:
    public void setX(int x) {
        this.coordinates.setX(x);
    }
    public void setY(int y) {
        this.coordinates.setY(y);
    }
    public void setSpeedX(float speedX) {
        this.speed.setX(speedX);
    }
    public void setSpeedY(float speedY) {
        this.speed.setY(speedY);
    }
    public void setAcelerationX(float ax) {
        aceleration.setX(ax);
    }
    public void setAcelerationY(float ay) {
        aceleration.setY(ay);
    }

    // Getter:
    public int getX() {
        return coordinates.getX();
    }
    public int getY() {
        return coordinates.getY();
    }
    public double getSpeedX() {
        return speed.getX();
    }
    public double getSpeedY() {
        return speed.getY();
    }
    public double getAcelerationX() {
        return aceleration.getX();
    }
    public double getAy() {
        return aceleration.getY();
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public int getDiameter() {
        return diameter;
    }
}
