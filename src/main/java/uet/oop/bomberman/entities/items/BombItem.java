package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
  public BombItem(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.powerup_bombs.getFxImage());
  }

  @Override
  public void update() {}

  @Override
  public void updateImage() {}
}
