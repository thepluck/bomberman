package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
  public Kondoria(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 1, 1);
    this.leftSprites = new Sprite[]{Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3};
    this.rightSprites = new Sprite[]{Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3};
    this.deadSprite = Sprite.kondoria_dead;
    this.sprites = leftSprites;
    this.setImg(this.sprites[0].getFxImage());
    this.setSpeed(minSpeed);
  }
}
