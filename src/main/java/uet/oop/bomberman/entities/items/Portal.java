package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.processors.Map.bomber;
import uet.oop.bomberman.processors.Map;


public class Portal extends Item {
  public Portal(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.portal.getFxImage());
  }

  @Override
  public void update() {
    if (this.isEaten()) return;
    if (bomber.getGridX() == getGridX() && bomber.getGridY() == getGridY()) {
      if (Map.enemies.isEmpty()) Map.levelUp();
      else return;
      this.setEaten(true);
      this.setImage(null);
    }
  }

  @Override
  public void updateImage() {
  }
}
