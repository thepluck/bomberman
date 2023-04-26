package uet.oop.bomberman.entities.bombers;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.entities.statics.Brick;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Map;

public class Bomb extends Entity {
  public static final int TICKING_CYCLE = 90;
  public static final int EXPLODING_CYCLE = 30;

  private int length = 2;
  private int animationStep;
  private boolean exploded;

  public Bomb(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image);
    animationStep = 0;
  }

  public Bomb(int xUnit, int yUnit, int length) {
    super(xUnit, yUnit, Sprite.bomb.getFxImage());
    animationStep = 0;
    this.length = length;
  }

  @Override
  public void update() {
    if (!isExploded() && isCollidingExplosion()) {
      setExploded(true);
    }
    updateImage();
  }

  @Override
  public void updateImage() {
    if (!isExploded()) {
      setImage(
          Sprite.movingSprite(
              Sprite.bomb,
              Sprite.bomb_1,
              Sprite.bomb_2,
              animationStep,
              Sprite.ANIMATION_CYCLE
          ).getFxImage()
      );
      animationStep++;
      if (animationStep == TICKING_CYCLE) {
        setExploded(true);
      }
    } else {
      if (animationStep == EXPLODING_CYCLE) {
        return;
      }
      setImage(
          Sprite.movingSprite(
              Sprite.bomb_exploded,
              Sprite.bomb_exploded1,
              Sprite.bomb_exploded2,
              animationStep,
              Sprite.ANIMATION_CYCLE
          ).getFxImage()
      );
      animationStep++;
    }
  }

  public boolean isExploded() {
    return exploded;
  }

  public void setExploded(boolean exploded) {
    this.exploded = exploded;
    animationStep = 0;
    int gridX = getGridX();
    int gridY = getGridY();
    for (int shiftX = 1; shiftX < length; shiftX++) {
      Entity entity = Map.getEntity(gridX + shiftX, gridY);
      if (entity instanceof Wall) {
        break;
      }
      if (entity instanceof Brick) {
        ((Brick) entity).setDestroyed(true);
      }
      Map.explosions.add(
          new Explosion(
            gridX + shiftX, gridY,
              shiftX == length - 1 ?
                  Explosion.Direction.RIGHT :
                  Explosion.Direction.HORIZONTAL
          )
      );
    }
    for (int shiftX = 1; shiftX < length; shiftX++) {
      Entity entity = Map.getEntity(gridX - shiftX, gridY);
      if (entity instanceof Wall) {
        break;
      }
      if (entity instanceof Brick) {
        ((Brick) entity).setDestroyed(true);
      }
      Map.explosions.add(
          new Explosion(
              gridX - shiftX, gridY,
              shiftX == length - 1 ?
                  Explosion.Direction.LEFT :
                  Explosion.Direction.HORIZONTAL
          )
      );
    }
    for (int shiftY = 1; shiftY < length; shiftY++) {
      Entity entity = Map.getEntity(gridX, gridY + shiftY);
      if (entity instanceof Wall) {
        break;
      }
      if (entity instanceof Brick) {
        ((Brick) entity).setDestroyed(true);
      }
      Map.explosions.add(
          new Explosion(
              gridX, gridY + shiftY,
              shiftY == length - 1 ?
                  Explosion.Direction.DOWN :
                  Explosion.Direction.VERTICAL
          )
      );
    }
    for (int shiftY = 1; shiftY < length; shiftY++) {
      Entity entity = Map.getEntity(gridX, gridY - shiftY);
      if (entity instanceof Wall) {
        break;
      }
      if (entity instanceof Brick) {
        ((Brick) entity).setDestroyed(true);
      }
      Map.explosions.add(
          new Explosion(
              gridX, gridY - shiftY,
              shiftY == length - 1 ?
                  Explosion.Direction.UP :
                  Explosion.Direction.VERTICAL
          )
      );
    }
  }



  public int getAnimationStep() {
    return animationStep;
  }

  public void setAnimationStep(int animationStep) {
    this.animationStep = animationStep;
  }

  public boolean isFullyExploded() {
    return exploded && animationStep == EXPLODING_CYCLE;
  }
}
