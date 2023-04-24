package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Library;

public class Kondoria extends Enemy {
  private boolean currentStrategy = false;
  public Kondoria(int xUnit, int yUnit, Image image) {
    super(xUnit, yUnit, image, 1, 2);
    this.leftSprites = new Sprite[]{Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3};
    this.rightSprites = new Sprite[]{Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3};
    this.deadSprite = Sprite.kondoria_dead;
    this.sprites = leftSprites;
    setImage(this.sprites[0].getFxImage());
    setSpeed(minSpeed);

  }

  @Override
  public void updateDirection() {
    if (directionChangeCountDown > 0) {
      directionChangeCountDown--;
    } else {
      directionChangeCountDown = DEFAULT_DIRECTION_CHANGE_COUNT_DOWN;
      if (random.nextInt(3) == 0) {
        currentStrategy = !currentStrategy;
      }
      direction = getBestDirection();
    }
  }

  @Override
  public Direction getBestDirection() {
    if (currentStrategy) {
      return getRandomDirection();
    }
    Direction bestDirection = Direction.STAND;
    int bestDistance = Integer.MAX_VALUE;
    for (Direction direction : Direction.values()) {
      if (direction == Direction.STAND) continue;
      int newX = getNewX(direction);
      int newY = getNewY(direction);
      if (Library.getDistanceToBomber(newX, newY) < bestDistance) {
        bestDistance = Library.getDistanceToBomber(newX, newY);
        bestDirection = direction;
      }
    }
    return bestDirection;
  }
}
