package com.mycompany.tg.base;

import com.mycompany.tg.base.DTO.DTOAcceleration;
import com.mycompany.tg.base.DTO.DTOPosition;
import com.mycompany.tg.base.DTO.DTOVelocity;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Balls implements VisualObject, Runnable {

    TheGameModel model;
    DTOVelocity velocity;
    DTOAcceleration acceleration;
    DTOPosition position;
    float mass;

    public Balls(TheGameModel model, int x, int y) {
        this.model = model;
        this.velocity = new DTOVelocity();
        this.acceleration = new DTOAcceleration();
        this.position = new DTOPosition();
        this.mass = 0;
        this.position.setPosX(x);
        this.position.setPosY(y);
    }

    @Override
    public void run() {
        int times = 0;
        while (times < 300) {
            times += 1;
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Balls.class.getName()).log(Level.SEVERE, null, ex);
            }
            move();
        }
        this.model.controller.delete(this.position.getPosX(), this.position.getPosY());
    }

    //To do
    @Override
    public void paint() {
        int x = this.position.getPosX();
        int y = this.position.getPosY();
        this.model.controller.paint(x, y);
    }

    @Override
    public void move() {
        int x = this.position.getPosX();
        int y = this.position.getPosY();

        x = (int) (x + this.velocity.getVelocityX());
        y = (int) (y + this.velocity.getVelocityY());

        this.position.setPosX(x);
        this.position.setPosY(y);

        this.model.controller.delete(x, y);

        if (x > this.model.controller.getVWXSize() || x < 0 || y < 0 || y > this.model.controller.getVMYSize()) {
            bounce();
        }
        this.model.controller.paint(x, y);

    }

    public void bounce() {
        this.velocity.setVelocityX(-this.velocity.getVelocityX());
        this.velocity.setVelocityY(-this.velocity.getVelocityY());
    }
}
