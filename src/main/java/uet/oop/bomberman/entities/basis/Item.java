package uet.oop.bomberman.entities.basis;

import javafx.scene.image.Image;

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
  }
}
