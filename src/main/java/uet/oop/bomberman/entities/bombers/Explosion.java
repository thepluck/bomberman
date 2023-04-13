package uet.oop.bomberman.entities.bombers;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {
  public static int EXPLOSION_TIME = 20;
  public static int EXPLOSION_CYCLE = EXPLOSION_TIME / 3;
  private int animationStep = 0;
  private final Sprite sprite;
  public Explosion(int xUnit, int yUnit, Image img, Direction direction) {
    super(xUnit, yUnit, img);
    sprite = getSprite(direction);
  }

  @Override
  public void update() {
    updateImage();
  }

  boolean isFullyExploded() {
    return animationStep == EXPLOSION_TIME;
  }

  @Override
  public void updateImage() {
    if (isFullyExploded()) return;
    setImg(sprite.getFxImage());
    animationStep++;
  }

  private Sprite getSprite(Direction direction) {
    return switch (direction) {
      case UP -> Sprite.movingSprite(
          Sprite.explosion_vertical_up_last,
          Sprite.explosion_vertical_up_last1,
          Sprite.explosion_vertical_up_last2,
          animationStep, EXPLOSION_CYCLE
      );
      case DOWN -> Sprite.movingSprite(
          Sprite.explosion_vertical_down_last,
          Sprite.explosion_vertical_down_last1,
          Sprite.explosion_vertical_down_last2,
          animationStep, EXPLOSION_CYCLE
      );
      case LEFT -> Sprite.movingSprite(
          Sprite.explosion_horizontal_left_last,
          Sprite.explosion_horizontal_left_last1,
          Sprite.explosion_horizontal_left_last2,
          animationStep, EXPLOSION_CYCLE);
      case RIGHT -> Sprite.movingSprite(
          Sprite.explosion_horizontal_right_last,
          Sprite.explosion_horizontal_right_last1,
          Sprite.explosion_horizontal_right_last2,
          animationStep, EXPLOSION_CYCLE
      );
      case VERTICAL -> Sprite.movingSprite(
          Sprite.explosion_vertical,
          Sprite.explosion_vertical1,
          Sprite.explosion_vertical2,
          animationStep, EXPLOSION_CYCLE
      );
      case HORIZONTAL -> Sprite.movingSprite(
          Sprite.explosion_horizontal,
          Sprite.explosion_horizontal1,
          Sprite.explosion_horizontal2,
          animationStep, EXPLOSION_CYCLE
      );
      default -> null;
    };
  }

  enum Direction {
    LEFT, RIGHT, UP, DOWN, VERTICAL, HORIZONTAL
  }
}
