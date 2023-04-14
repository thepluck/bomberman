package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;

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
    switch (direction) {
      case UP -> {
        y -= speed;
      }
      case DOWN -> {
        y += speed;
      }
      case LEFT -> {
        x -= speed;
      }
      case RIGHT -> {
        x += speed;
      }
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
