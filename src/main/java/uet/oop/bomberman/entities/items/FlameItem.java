package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
  public FlameItem(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.powerup_flames.getFxImage());
  }

  @Override
  public void update() {}

  @Override
  public void updateImage() {}
}
