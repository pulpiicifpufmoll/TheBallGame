package base.Objects;

import base.DTO.VectorDTO;

import java.awt.*;

public interface VOD {

    HitBox getHitbox();
    HitBox getFutureHitbox(VectorDTO coords);
    void nextMove(int x, int y);
    void bounce(String action, VectorDTO coords);
    void kill();
    void explode();
    void paint(Graphics g);
}
