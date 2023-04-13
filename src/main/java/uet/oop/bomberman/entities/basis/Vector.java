package uet.oop.bomberman.entities.basis;

public class Vector {
  protected int x;
  protected int y;

  public Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Vector() {
    this(0, 0);
  }

  public Vector(Vector v) {
    this(v.x, v.y);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
