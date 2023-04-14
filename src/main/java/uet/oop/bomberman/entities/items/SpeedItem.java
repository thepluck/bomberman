package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
  public SpeedItem(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.powerup_speed.getFxImage());
  }

  @Override
  public void update() {}

  @Override
  public void updateImage() {}
}
