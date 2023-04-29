package uet.oop.bomberman.processors;


import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.entities.bombers.Explosion;
import uet.oop.bomberman.entities.statics.Brick;

import java.util.ConcurrentModificationException;

import static uet.oop.bomberman.BombermanGame.canvas;
import static uet.oop.bomberman.BombermanGame.gc;
import static uet.oop.bomberman.processors.Map.*;

public class Display {
  /**
   * Render all entities.
   */
  public static void render() {
    // render
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    statics.forEach(g -> g.render(gc));
    bomber.render(gc);
    bombs.forEach(g -> g.render(gc));
    bricks.forEach(g -> g.render(gc));
    items.forEach(g -> g.render(gc));
    enemies.forEach(g -> g.render(gc));
    explosions.forEach(g -> g.render(gc));
    bomber.render(gc);
  }

  public static void update() {
    // update
    try {
      bomber.update();
      bombs.forEach(Entity::update);
      explosions.forEach(Entity::update);
      bricks.forEach(Entity::update);
      items.forEach(Entity::update);
      enemies.forEach(Entity::update);
      /// remove
      bombs.removeIf(Bomb::isFullyExploded);
      bricks.removeIf(Brick::isFullyDestroyed);
      items.removeIf(Item::isEaten);
      explosions.removeIf(Explosion::isFullyExploded);
      enemies.removeIf(Enemy::isFullyDead);
    } catch (ConcurrentModificationException ignored) {
    }
  }
}
