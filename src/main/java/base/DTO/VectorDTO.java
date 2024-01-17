package base.DTO;

public class VectorDTO {

    float x;
    float y;

    public VectorDTO(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float velocityX) {
        this.x = velocityX;
    }

    public float getY() {
        return y;
    }

    public void setY(float velocityY) {
        this.y = velocityY;
    }
}
