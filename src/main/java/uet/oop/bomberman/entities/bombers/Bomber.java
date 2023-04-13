package uet.oop.bomberman.entities.bombers;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends DynamicEntity {
  private int bombLength = 2;
  private int bombLimit = 1;
  public static final int DEFAULT_DYING_COUNT_DOWN = 60;

  public Bomber(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    this.setDirection(RIGHT);
  }

  @Override
  public void update() {
    this.animate();
  }

  @Override
  public void updateImage() {
    Sprite sprite;
    if (getDirection() == LEFT) {
      if (isLocked()) {
        sprite = Sprite.player_left;
      } else {
        sprite = Sprite.movingSprite(
            Sprite.player_left_1, Sprite.player_left_2,
            getAnimationStep(), Sprite.ANIMATION_CYCLE
        );
      }
    } else if (getDirection() == RIGHT) {
      if (isLocked()) {
        sprite = Sprite.player_right;
      } else {
        sprite = Sprite.movingSprite(
            Sprite.player_right_1, Sprite.player_right_2,
            getAnimationStep(), Sprite.ANIMATION_CYCLE
        );
      }
    } else if (getDirection() == UP) {
      if (isLocked()) {
        sprite = Sprite.player_up;
      } else {
        sprite = Sprite.movingSprite(
            Sprite.player_up_1, Sprite.player_up_2,
            getAnimationStep(), Sprite.ANIMATION_CYCLE
        );
      }
    } else if (getDirection() == DOWN) {
      if (isLocked()) {
        sprite = Sprite.player_down;
      } else {
        sprite = Sprite.movingSprite(
            Sprite.player_down_1, Sprite.player_down_2,
            getAnimationStep(), Sprite.ANIMATION_CYCLE
        );
      }
    } else {
      sprite = Sprite.player_right;
    }
    move();
    setImg(sprite.getFxImage());
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
