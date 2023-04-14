package uet.oop.bomberman.entities.statics;

import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
  private boolean destroyed;
  private int animationStep;
  public Brick(int x, int y) {
    super(x, y, Sprite.brick.getFxImage());
    destroyed = false;
  }

  public boolean isDestroyed() {
    return destroyed;
  }

  public int getAnimationStep() {
    return animationStep;
  }

  public void setAnimationStep(int animationStep) {
    this.animationStep = animationStep;
  }

  public void setDestroyed(boolean destroyed) {
    if (this.isDestroyed()) {
      return;
    }
    this.destroyed = destroyed;
    if (destroyed) {
      setAnimationStep(Sprite.ANIMATION_CYCLE);
    }
  }

  public boolean isFullyDestroyed() {
    return destroyed && animationStep == 0;
  }

  @Override
  public void update() {
    updateImage();
  }

  @Override
  public void updateImage() {
    if (isFullyDestroyed()) return;
    if (isDestroyed()) {
      this.setImage(
        Sprite.movingSprite(
          Sprite.brick_exploded,
          Sprite.brick_exploded1,
          Sprite.brick_exploded2,
          animationStep,
          Sprite.ANIMATION_CYCLE
        ).getFxImage()
      );
      animationStep--;
    }
  }


}
