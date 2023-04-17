package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends DynamicEntity {
  public static final int DEFAULT_DYING_COUNT_DOWN = 40;
  protected int minSpeed;
  protected int maxSpeed;
  protected int speedChangeCountDown;
  protected int directionChangeCountDown;
  protected Sprite[] leftSprites = new Sprite[3];
  protected Sprite[] rightSprites = new Sprite[3];
  protected Sprite[] sprites = new Sprite[3];
  protected Sprite deadSprite;

  public Enemy(int xUnit, int yUnit, Image image, int minSpeed, int maxSpeed) {
    super(xUnit, yUnit, image);
    this.minSpeed = minSpeed;
    this.maxSpeed = maxSpeed;
  }

  // TO DO: xử lý khi Enemy bị Bomber tấn công
  @Override
  public void update() {

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
}
