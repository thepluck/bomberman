package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.processors.SoundPlayer;

public abstract class Item extends Entity {
  private boolean eaten;

  public Item(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image);
  }

  public boolean isEaten() {
    return eaten;
  }

  public void setEaten(boolean eaten) {
    this.eaten = eaten;
    if (!(this instanceof Portal)) {
      SoundPlayer eatenSound = new SoundPlayer("/sounds/eaten.wav", 0, 0);
    }
  }
}
