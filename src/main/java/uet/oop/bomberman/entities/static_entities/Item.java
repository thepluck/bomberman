package uet.oop.bomberman.entities.static_entities;

import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Item extends Entity {
  public enum Type {
    BOMB, SPEED, FLAME, PORTAL
  }

  private boolean eaten;
  private final Type type;

  public Item(int xUnit, int yUnit, Type type) {
    super(xUnit, yUnit,
        switch (type) {
          case BOMB -> Sprite.powerup_bombs.getFxImage();
          case SPEED -> Sprite.powerup_speed.getFxImage();
          case FLAME -> Sprite.powerup_flames.getFxImage();
          case PORTAL -> Sprite.portal.getFxImage();
        }
    );
    this.type = type;
  }

  public boolean isEaten() {
    return eaten;
  }

  public void setEaten(boolean eaten) {
    this.eaten = eaten;
  }
  @Override
  public void update() {
    if (this.isEaten()) return;
  }

  public void updateImage() {}
}
