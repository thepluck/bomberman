package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Map;
import uet.oop.bomberman.processors.Library;

public abstract class DynamicEntity extends Entity {
  public enum Direction {
    UP, DOWN, LEFT, RIGHT, STAND
  }
  public static final int MAX_STEP = 10000;
  protected boolean dead;
  protected int speed;
  protected Direction direction;
  protected int dyingCountDown;
  protected int animationStep;
  protected boolean locked;

  public DynamicEntity(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image);
    this.dead = false;
    this.speed = 4;
    this.direction = Direction.STAND;
    this.dyingCountDown = 0;

  }

  public void move() {
    if (isLocked()) {
      return;
    }
    int newX = x, newY = y;
    switch (direction) {
      case UP -> newY -= speed;
      case DOWN ->newY += speed;
      case LEFT -> newX -= speed;
      case RIGHT -> newX += speed;
    }
    boolean canMove = true;
    for (int shiftX = 0; shiftX <= 1; shiftX++)
      for (int shiftY = 0; shiftY <= 1; shiftY++) {
        int gridX = newX / Sprite.SCALED_SIZE + shiftX;
        int gridY = newY / Sprite.SCALED_SIZE + shiftY;
        if (Library.isIntersecting(newX, newY,
            gridX * Sprite.SCALED_SIZE,
            gridY * Sprite.SCALED_SIZE)
            && !(Map.getEntity(gridX, gridY) instanceof Grass)) {
          canMove = false;
        }
      }
    if (canMove) {
      x = newX; y = newY;
    }
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public boolean isDead() {
    return dead;
  }

  public abstract void setDead(boolean dead);

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public int getDyingCountDown() {
    return dyingCountDown;
  }

  public void setDyingCountDown(int dyingCountDown) {
    this.dyingCountDown = dyingCountDown;
  }

  public int getAnimationStep() {
    return animationStep;
  }

  public void setAnimationStep(int animationStep) {
    this.animationStep = animationStep;
  }

  public void animate() {
    if (animationStep == MAX_STEP) {
      animationStep = 0;
    } else {
      animationStep++;
    }
    if (dyingCountDown > 0) {
      dyingCountDown--;
    }
  }

  public boolean isFullyDead() {
    return dead && dyingCountDown == 0;
  }
}
