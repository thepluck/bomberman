package uet.oop.bomberman.entities.bombers;

import uet.oop.bomberman.entities.basis.Entity;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
  public static final int TICKING_CYCLE = 90;
  public static final int EXPLODING_CYCLE = 20;

  private int length = 2;
  private int animationStep;
  private boolean exploded;

  public Bomb(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    animationStep = 0;
  }

  public Bomb(int xUnit, int yUnit, Image img, int length) {
    super(xUnit, yUnit, img);
    animationStep = 0;
    this.length = length;
  }

  @Override
  public void update() {
/*    if (!this.isExploded() && this.checkTouchingExplosion()) {
      this.setExploded(true);
    }*/
    updateImage();
  }

  @Override
  public void updateImage() {
    if (!isExploded()) {
      setImg(
        Sprite.movingSprite(
          Sprite.bomb,
          Sprite.bomb_1,
          Sprite.bomb_2,
          animationStep,
          Sprite.ANIMATION_CYCLE
        ).getFxImage()
      );
      animationStep = animationStep + 1;
      if (animationStep == TICKING_CYCLE) {
        this.setExploded(true);
      }
    } else {
      if (animationStep == EXPLODING_CYCLE) {
        return;
      }
      setImg(
        Sprite.movingSprite(
          Sprite.bomb_exploded,
          Sprite.bomb_exploded1,
          Sprite.bomb_exploded2,
          animationStep,
          Sprite.ANIMATION_CYCLE
        ).getFxImage()
      );
      animationStep = animationStep + 1;
    }
  }

  public boolean isExploded() {
    return exploded;
  }

  public void setExploded(boolean exploded) {
    this.exploded = exploded;
  }

  public int getAnimationStep() {
    return animationStep;
  }

  public void setAnimationStep(int animationStep) {
    this.animationStep = animationStep;
  }
}
