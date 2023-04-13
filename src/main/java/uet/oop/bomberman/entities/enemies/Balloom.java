package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
  public Balloom(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 1, 1);
    this.leftSprites = new Sprite[]{Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
    this.rightSprites = new Sprite[]{Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3};
    this.deadSprite = Sprite.balloom_dead;
    this.sprites = leftSprites;
    this.setImg(this.sprites[0].getFxImage());
    this.setSpeed(minSpeed);
  }
}
