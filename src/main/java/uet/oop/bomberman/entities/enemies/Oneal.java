package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Library;

public class Oneal extends Enemy {
  public Oneal(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 1, 4);
    this.leftSprites = new Sprite[]{Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3};
    this.rightSprites = new Sprite[]{Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3};
    this.deadSprite = Sprite.oneal_dead;
    this.sprites = leftSprites;
    setImage(this.sprites[0].getFxImage());
    setSpeed(minSpeed);
  }

  @Override
  public void updateDirection() {
    updateDirectionChangeCountDown();
    Direction bfsDirection = Library.getBestDirection(getGridX(), getGridY());
    int newX = getNewX(bfsDirection);
    int newY = getNewY(bfsDirection);
    if (newX == x && newY == y) {
      direction = getRandomDirection();
    } else {
      direction = bfsDirection;
    }
  }
}
