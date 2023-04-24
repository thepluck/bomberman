package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends DynamicEntity {
  public static final int DEFAULT_DYING_COUNT_DOWN = 40;
  public static final int DEFAULT_SPEED_CHANGE_COUNT_DOWN = 50;
  public static final int DEFAULT_DIRECTION_CHANGE_COUNT_DOWN = 100;
  protected int minSpeed;
  protected int maxSpeed;
  protected int speedChangeCountDown = DEFAULT_SPEED_CHANGE_COUNT_DOWN;
  protected int directionChangeCountDown = DEFAULT_DIRECTION_CHANGE_COUNT_DOWN;
  protected Sprite[] leftSprites = new Sprite[3];
  protected Sprite[] rightSprites = new Sprite[3];
  protected Sprite[] sprites = new Sprite[3];
  protected Sprite deadSprite;
  public static Random random = new Random();

  public Enemy(int xUnit, int yUnit, Image image, int minSpeed, int maxSpeed) {
    super(xUnit, yUnit, image);
    this.minSpeed = minSpeed;
    this.maxSpeed = maxSpeed;
  }

  // TO DO: xử lý khi Enemy bị Bomber tấn công
  @Override
  public void update() {
    if (!isDead()) {
      updateSpeed();
      updateDirection();
      updateImage();
    }
    animate();
  }

  public void updateSpeed() {
    if (speedChangeCountDown > 0) {
      speedChangeCountDown--;
      return;
    }
    speedChangeCountDown = DEFAULT_SPEED_CHANGE_COUNT_DOWN;
    int bias = minSpeed + random.nextInt(maxSpeed - minSpeed + 1);
    if (bias > speed) {
      speed++;
    } else if (bias < speed) {
      speed--;
    }
  }

  public void updateDirection() {
    if (directionChangeCountDown > 0) {
      directionChangeCountDown--;
      return;
    }
    directionChangeCountDown = DEFAULT_DIRECTION_CHANGE_COUNT_DOWN;
    direction = getBestDirection();
  }

  // TO DO: xử lý hình ảnh
  @Override
  public void updateImage() {
    /// Chỉ thay đổi sprite khi quay trái hoặc phải
    sprites = switch (direction) {
      case LEFT -> leftSprites;
      case RIGHT -> rightSprites;
      default -> sprites;
    };
    setImage(
        Sprite.movingSprite(
            sprites[0],
            sprites[1],
            sprites[2],
            getAnimationStep(),
            Sprite.ANIMATION_CYCLE * 3
        ).getFxImage()
    );
    move();
  }

  @Override
  public void setDead(boolean dead) {
    assert !this.dead && dead;
    this.dead = true;
    dyingCountDown = DEFAULT_DYING_COUNT_DOWN;
    setImage(deadSprite.getFxImage());
  }

  public abstract Direction getBestDirection();

  public Direction getRandomDirection() {
    int randomInt = random.nextInt(4);
    return switch (randomInt) {
      case 0 -> Direction.UP;
      case 1 -> Direction.DOWN;
      case 2 -> Direction.LEFT;
      case 3 -> Direction.RIGHT;
      default -> Direction.STAND;
    };
  }
}
