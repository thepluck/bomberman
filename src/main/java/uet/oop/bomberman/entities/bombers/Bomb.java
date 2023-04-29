package uet.oop.bomberman.entities.bombers;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.entities.statics.Brick;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Map;
import uet.oop.bomberman.processors.SoundPlayer;

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

  public boolean tryExplode(int x, int y) {
    Entity entity = Map.getEntity(x, y);
    if (entity instanceof Wall) {
      return false;
    }
    if (entity instanceof Brick) {
      ((Brick) entity).setDestroyed(true);
      return false;
    }
    return true;
  }

  public void setExploded(boolean exploded) {
    this.exploded = exploded;
    SoundPlayer explosionSound = new SoundPlayer("/sounds/explosion.wav", 0, 20);
    animationStep = 0;
    int gridX = getGridX();
    int gridY = getGridY();
    for (int shiftX = 1; shiftX < length; shiftX++) {
      if (!tryExplode(gridX + shiftX, gridY)) {
        break;
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
      if (!tryExplode(gridX - shiftX, gridY)) {
        break;
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
      if (!tryExplode(gridX, gridY + shiftY)) {
        break;
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
      if (!tryExplode(gridX, gridY - shiftY)) {
        break;
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
