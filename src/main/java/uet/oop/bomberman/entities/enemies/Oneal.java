package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
  public Oneal(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 1, 3);
    this.leftSprites = new Sprite[]{Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3};
    this.rightSprites = new Sprite[]{Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3};
    this.deadSprite = Sprite.oneal_dead;
    this.sprites = leftSprites;
    this.setImg(this.sprites[0].getFxImage());
    this.setSpeed(minSpeed);
  }
}
