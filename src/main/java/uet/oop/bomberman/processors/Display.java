package uet.oop.bomberman.processors;


import static uet.oop.bomberman.BombermanGame.canvas;
import static uet.oop.bomberman.BombermanGame.gc;
import static uet.oop.bomberman.processors.Map.*;

import uet.oop.bomberman.entities.basis.Entity;

import java.util.ConcurrentModificationException;

public class Display {
  public static void render() {
    // render
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    bomber.render(gc);
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
    bombs.forEach(g -> g.render(gc));
    bricks.forEach(g -> g.render(gc));
    items.forEach(g -> g.render(gc));
    enemies.forEach(g -> g.render(gc));
    explosions.forEach(g -> g.render(gc));
  }

  public static void update() {
    // update
    try {
      entities.forEach(Entity::update);
      bombs.forEach(Entity::update);
      explosions.forEach(Entity::update);
      bricks.forEach(Entity::update);
      items.forEach(Entity::update);
      enemies.forEach(Entity::update);
    } catch (ConcurrentModificationException ignored) {
    }
  }
}
