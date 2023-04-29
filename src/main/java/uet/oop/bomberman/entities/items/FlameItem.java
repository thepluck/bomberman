package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.processors.Map.bomber;

public class FlameItem extends Item {
  public FlameItem(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.powerup_flames.getFxImage());
  }

  @Override
  public void update() {
    if (this.isEaten()) return;
    if (bomber.getGridX() == getGridX() && bomber.getGridY() == getGridY()) {
      bomber.increaseBombLength();
      this.setEaten(true);
      this.setImage(null);
    }
  }

  @Override
  public void updateImage() {
  }
}
