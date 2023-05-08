package uet.oop.bomberman.entities.bombers;

import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {
  public static int EXPLOSION_TIME = 30;
  public static int EXPLOSION_CYCLE = EXPLOSION_TIME / 3;
  private final Sprite[] sprites = new Sprite[3];
  private int animationStep = 0;

  public Explosion(int xUnit, int yUnit, Direction direction) {
    super(xUnit, yUnit, null);
    switch (direction) {
      case UP -> {
        sprites[0] = Sprite.explosion_vertical_up_last;
        sprites[1] = Sprite.explosion_vertical_up_last1;
        sprites[2] = Sprite.explosion_vertical_up_last2;
      }
      case DOWN -> {
        sprites[0] = Sprite.explosion_vertical_down_last;
        sprites[1] = Sprite.explosion_vertical_down_last1;
        sprites[2] = Sprite.explosion_vertical_down_last2;
      }
      case LEFT -> {
        sprites[0] = Sprite.explosion_horizontal_left_last;
        sprites[1] = Sprite.explosion_horizontal_left_last1;
        sprites[2] = Sprite.explosion_horizontal_left_last2;
      }
      case RIGHT -> {
        sprites[0] = Sprite.explosion_horizontal_right_last;
        sprites[1] = Sprite.explosion_horizontal_right_last1;
        sprites[2] = Sprite.explosion_horizontal_right_last2;
      }
      case VERTICAL -> {
        sprites[0] = Sprite.explosion_vertical;
        sprites[1] = Sprite.explosion_vertical1;
        sprites[2] = Sprite.explosion_vertical2;
      }
      case HORIZONTAL -> {
        sprites[0] = Sprite.explosion_horizontal;
        sprites[1] = Sprite.explosion_horizontal1;
        sprites[2] = Sprite.explosion_horizontal2;
      }
    };
    setImage(sprites[0].getFxImage());
  }

  @Override
  public void update() {
    updateImage();
  }

  public boolean isFullyExploded() {
    return animationStep == EXPLOSION_TIME;
  }

  @Override
  public void updateImage() {
    if (isFullyExploded()) return;
    setImage(
        Sprite.movingSprite(
            sprites[0],
            sprites[1],
            sprites[2],
            animationStep,
            EXPLOSION_CYCLE
        ).getFxImage());
    animationStep++;
  }

  enum Direction {
    LEFT, RIGHT, UP, DOWN, VERTICAL, HORIZONTAL
  }
}
