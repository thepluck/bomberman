package uet.oop.bomberman.entities.bombers;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends DynamicEntity {
  public static final int DEFAULT_DYING_COUNT_DOWN = 60;
  private int bombLength = 2;
  private int bombLimit = 1;

  public Bomber(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image);
    this.setDirection(Direction.RIGHT);
    this.setLocked(true);
  }

  @Override
  public void update() {
    updateImage();
    this.animate();
  }

  @Override
  public void updateImage() {
    Sprite sprite;
    if (isDead()) {
      sprite = Sprite.movingSprite(
          Sprite.player_dead1, Sprite.player_dead2,
          Sprite.player_dead3, getAnimationStep(),
          Sprite.ANIMATION_CYCLE);
    } else {
      sprite = switch (direction) {
        case UP -> {
          if (isLocked()) {
            yield Sprite.player_up;
          } else {
            yield Sprite.movingSprite(
                Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, getAnimationStep(),
                Sprite.ANIMATION_CYCLE);
          }
        }
        case DOWN -> {
          if (isLocked()) {
            yield Sprite.player_down;
          } else {
            yield Sprite.movingSprite(
                Sprite.player_down, Sprite.player_down_1,
                Sprite.player_down_2, getAnimationStep(),
                Sprite.ANIMATION_CYCLE);
          }
        }
        case LEFT -> {
          if (isLocked()) {
            yield Sprite.player_left;
          } else {
            yield Sprite.movingSprite(
                Sprite.player_left, Sprite.player_left_1,
                Sprite.player_left_2, getAnimationStep(),
                Sprite.ANIMATION_CYCLE);
          }
        }
        case RIGHT -> {
          if (isLocked()) {
            yield Sprite.player_right;
          } else {
            yield Sprite.movingSprite(
                Sprite.player_right, Sprite.player_right_1,
                Sprite.player_right_2, getAnimationStep(),
                Sprite.ANIMATION_CYCLE);
          }
        }
        default -> Sprite.player_right;
      };
    }
    move();
    setImage(sprite.getFxImage());
  }

  public int getBombLength() {
    return bombLength;
  }

  public void setBombLength(int bombLength) {
    this.bombLength = bombLength;
  }

  public int getBombLimit() {
    return bombLimit;
  }

  public void setBombLimit(int bombLimit) {
    this.bombLimit = bombLimit;
  }

  @Override
  public void setDead(boolean dead) {
    assert !this.dead && dead;
    this.dead = true;
    dyingCountDown = DEFAULT_DYING_COUNT_DOWN;
  }
}
