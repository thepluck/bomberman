package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
  public Doll(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 2, 3);
    this.leftSprites = new Sprite[]{Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3};
    this.rightSprites = new Sprite[]{Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3};
    this.deadSprite = Sprite.doll_dead;
    this.sprites = leftSprites;
    this.setImg(this.sprites[0].getFxImage());
    this.setSpeed(minSpeed);
  }
}
