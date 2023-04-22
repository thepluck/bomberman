package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.Map;

public abstract class DynamicEntity extends Entity {
  public static final int MAX_STEP = 10000;
  public static final int DEFAULT_SPEED = 4;
  protected boolean dead;
  protected int speed;
  protected Direction direction;
  protected int dyingCountDown;
  protected int animationStep;
  protected boolean locked;
  public DynamicEntity(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image);
    this.dead = false;
    this.speed = DEFAULT_SPEED;
    this.direction = Direction.STAND;
    this.dyingCountDown = 0;

  }

  public void move() {
    if (isLocked()) {
      return;
    }
    /// Calculate the max distance that the entity can move
    int maxMoveable = speed;
    switch (direction) {
      case UP-> {
        for (int shiftY = -1; shiftY <= 0; shiftY++) {
          for (int shiftX = -1; shiftX <= 1; shiftX++) {
            int gridX = x / Sprite.SCALED_SIZE + shiftX;
            int gridY = y / Sprite.SCALED_SIZE + shiftY;
            if (!Library.isInside(gridX, gridY)) {
              continue;
            }
            if (Library.getIntersection(x, gridX * Sprite.SCALED_SIZE) > 0
                && !(Map.getEntity(gridX, gridY) instanceof Grass)) {
              maxMoveable = Math.min(maxMoveable,
                  -Library.getIntersection(y, gridY * Sprite.SCALED_SIZE));
            }
          }
        }
      }
      case DOWN -> {
        for (int shiftY = 1; shiftY <= 2; shiftY++) {
          for (int shiftX = -1; shiftX <= 1; shiftX++) {
            int gridX = x / Sprite.SCALED_SIZE + shiftX;
            int gridY = y / Sprite.SCALED_SIZE + shiftY;
            if (!Library.isInside(gridX, gridY)) {
              continue;
            }
            if (Library.getIntersection(x, gridX * Sprite.SCALED_SIZE) > 0
                && !(Map.getEntity(gridX, gridY) instanceof Grass)) {
              maxMoveable = Math.min(maxMoveable,
                  -Library.getIntersection(y, gridY * Sprite.SCALED_SIZE));
            }
          }
        }
      }
      case LEFT -> {
        for (int shiftX = -1; shiftX <= 0; shiftX++) {
          for (int shiftY = -1; shiftY <= 1; shiftY++) {
            int gridX = x / Sprite.SCALED_SIZE + shiftX;
            int gridY = y / Sprite.SCALED_SIZE + shiftY;
            if (!Library.isInside(gridX, gridY)) {
              continue;
            }
            if (Library.getIntersection(y, gridY * Sprite.SCALED_SIZE) > 0
                && !(Map.getEntity(gridX, gridY) instanceof Grass)) {
              maxMoveable = Math.min(maxMoveable,
                  -Library.getIntersection(x, gridX * Sprite.SCALED_SIZE));
            }
          }
        }
      }
      case RIGHT -> {
        for (int shiftX = 1; shiftX <= 2; shiftX++) {
          for (int shiftY = -1; shiftY <= 1; shiftY++) {
            int gridX = x / Sprite.SCALED_SIZE + shiftX;
            int gridY = y / Sprite.SCALED_SIZE + shiftY;
            if (!Library.isInside(gridX, gridY)) {
              continue;
            }
            if (Library.getIntersection(y, gridY * Sprite.SCALED_SIZE) > 0
                && !(Map.getEntity(gridX, gridY) instanceof Grass)) {
              maxMoveable = Math.min(maxMoveable,
                  -Library.getIntersection(x, gridX * Sprite.SCALED_SIZE));
            }
          }
        }
      }
    }
    assert maxMoveable >= 0;
    switch (direction) {
      case UP -> y -= maxMoveable;
      case DOWN -> y += maxMoveable;
      case LEFT -> x -= maxMoveable;
      case RIGHT -> x += maxMoveable;
    }
/*    System.err.println(Map.getEntity((x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE,
        (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE).getClass().getName());*/
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

  public enum Direction {
    UP, DOWN, LEFT, RIGHT, STAND
  }
}
