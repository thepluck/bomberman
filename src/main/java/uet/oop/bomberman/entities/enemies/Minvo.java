package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {
  public Minvo(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 1, 4);
    this.leftSprites = new Sprite[]{Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3};
    this.rightSprites = new Sprite[]{Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3};
    this.deadSprite = Sprite.minvo_dead;
    this.sprites = leftSprites;
    this.setImg(this.sprites[0].getFxImage());
    this.setSpeed(minSpeed);
  }
}
