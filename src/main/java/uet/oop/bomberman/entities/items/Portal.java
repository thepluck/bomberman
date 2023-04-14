package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Item {
  public Portal(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.portal.getFxImage());
  }

  @Override
  public void update() {
  }

  @Override
  public void updateImage() {
  }
}
