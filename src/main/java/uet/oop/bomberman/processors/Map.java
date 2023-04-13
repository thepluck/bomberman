package uet.oop.bomberman.processors;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.entities.bombers.Bomber;
import uet.oop.bomberman.entities.bombers.Explosion;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.entities.static_entities.Brick;
import uet.oop.bomberman.entities.static_entities.Grass;
import uet.oop.bomberman.entities.static_entities.Item;
import uet.oop.bomberman.entities.static_entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;

public class Map {
  public static int level = 1;
  public static int numRow = 105;
  public static int numCol = 105;

  public static Bomber bomber;
  public static List<DynamicEntity> entities = new ArrayList<>();
  public static List<Entity> stillObjects = new ArrayList<>(); // Grass & Wall
  public static List<Brick> bricks = new ArrayList<>();
  public static List<Bomb> bombs = new ArrayList<>();
  public static List<Explosion> explosions = new ArrayList<>();
  public static List<Item> items = new ArrayList<>();
  public static List<Enemy> enemies = new ArrayList<>();
  public static Entity[][] map = new Entity[numCol][numRow];

  public static Entity getEntity(int x, int y) {
    return map[x][y];
  }

  public static void setEntity(int x, int y, Entity entity) {
    map[x][y] = entity;
  }

  public static void reset() {
    bomber = null;
    entities.clear();
    stillObjects.clear();
    bombs.clear();
    bricks.clear();
    explosions.clear();
    items.clear();
    enemies.clear();
  }

  public static void readMap() {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("res/levels/Level" + level + ".txt"));
    } catch (FileNotFoundException e) {
      BombermanGame.victoryScene();
      return;
    }

    reset();

    numRow = scanner.nextInt();
    numCol = scanner.nextInt();

    scanner.nextLine();

    for (int i = 0; i < numRow; i++) {
      String line = scanner.nextLine();
      for (int j = 0; j < numCol; j++) {
        Entity entity;
        switch (line.charAt(j)) {
          case '#' -> {
            entity = new Wall(j, i, Sprite.wall.getFxImage());
            stillObjects.add(entity);
          }
          case '*' -> {
            entity = new Brick(j, i, Sprite.grass.getFxImage());
            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
            bricks.add((Brick) entity);
          }
          case 'p' -> {
            bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
            entities.add(bomber);
          }
          case 'f', 'b', 's', 'x' -> {
            entity = new Brick(j, i, Sprite.brick.getFxImage());
            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
            bricks.add((Brick) entity);
            items.add(
                new Item(j, i,
                    switch (line.charAt(j)) {
                      case 'f' -> Item.Type.FLAME;
                      case 'b' -> Item.Type.BOMB;
                      case 's' -> Item.Type.SPEED;
                      case 'x' -> Item.Type.PORTAL;
                      default -> null;
                    }
                )
            );
          }
          case '1' -> {
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
            enemies.add(new Balloom(j, i, null));
          }
          case '2' -> {
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
            enemies.add(new Oneal(j, i, null));
          }
          case '3' -> {
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
            enemies.add(new Kondoria(j, i, null));
          }
          case '4' -> {
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
            enemies.add(new Doll(j, i, null));
          }
          case '5' -> {
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
            enemies.add(new Minvo(j, i, null));
          }
          default -> {
            entity = new Grass(j, i, Sprite.grass.getFxImage());
            stillObjects.add(entity);
          }
        }
        setEntity(j, i, entity);
      }
    }
    scanner.close();
  }
}
