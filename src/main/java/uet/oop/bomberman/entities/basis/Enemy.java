package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Map;
import uet.oop.bomberman.processors.SoundPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Enemy extends DynamicEntity {
  public static final int DEFAULT_DYING_COUNT_DOWN = 40;
  public static final int DEFAULT_SPEED_CHANGE_COUNT_DOWN = 50;
  public static final int DEFAULT_DIRECTION_CHANGE_COUNT_DOWN = 100;
  public static Random random = new Random();
  protected int minSpeed;
  protected int maxSpeed;
  protected int speedChangeCountDown = 0;
  protected int directionChangeCountDown = 0;
  protected Sprite[] leftSprites = new Sprite[3];
  protected Sprite[] rightSprites = new Sprite[3];
  protected Sprite[] sprites = new Sprite[3];
  protected Sprite deadSprite;

  public Enemy(int xUnit, int yUnit, Image image, int minSpeed, int maxSpeed) {
    super(xUnit, yUnit, image);
    this.minSpeed = minSpeed;
    this.maxSpeed = maxSpeed;
  }

  public static void toggleAll() {
    BombermanGame.paused = !BombermanGame.paused;
    for (Enemy enemy : Map.enemies) {
      enemy.locked = !enemy.locked;
    }
  }

  // TO DO: xử lý khi Enemy bị Bomber tấn công
  @Override
  public void update() {
    if (isCollidingExplosion()) {
      setDead(true);
    }
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
    updateDirectionChangeCountDown();
    direction = getRandomDirection();
  }

  public void updateDirectionChangeCountDown() {
    if (directionChangeCountDown > 0) {
      directionChangeCountDown--;
    } else {
      directionChangeCountDown = DEFAULT_DIRECTION_CHANGE_COUNT_DOWN;
    }
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
    sound = new SoundPlayer("/sounds/enemyDying.wav", 0, 20);
  }

  public Direction getRandomDirection() {
    if (directionChangeCountDown != DEFAULT_DIRECTION_CHANGE_COUNT_DOWN) {
      return direction;
    }
    List<Direction> directions = new ArrayList<>();
    directions.add(Direction.STAND);
    for (Direction direction : Direction.values()) {
      int newX = getNewX(direction);
      int newY = getNewY(direction);
      if (newX == x && newY == y) continue;
      directions.add(direction);
    }
    return directions.get(random.nextInt(directions.size()));
  }
}
