package uet.oop.bomberman.entities.statics;

import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Map;

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

  public void setDestroyed(boolean destroyed) {
    if (this.isDestroyed()) {
      return;
    }
    this.destroyed = destroyed;
    if (destroyed) {
      setAnimationStep(Sprite.ANIMATION_CYCLE);
    }
  }

  public int getAnimationStep() {
    return animationStep;
  }

  public void setAnimationStep(int animationStep) {
    this.animationStep = animationStep;
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
      if (animationStep == 0) {
        Map.setEntity(getGridX(), getGridY(), new Grass(getGridX(), getGridY()));
      }
    }
  }


}
