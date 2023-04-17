package uet.oop.bomberman.processors;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.entities.basis.Entity;
import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.entities.bombers.Bomber;
import uet.oop.bomberman.entities.bombers.Explosion;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.entities.statics.Brick;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
  public static int level = 1;
  public static int numRow = 105;
  public static int numCol = 105;

  public static Bomber bomber;
  public static List<DynamicEntity> entities = new ArrayList<>();
  public static List<Entity> statics = new ArrayList<>(); // Grass & Wall
  public static List<Brick> bricks = new ArrayList<>();
  public static List<Bomb> bombs = new ArrayList<>();
  public static List<Explosion> explosions = new ArrayList<>();
  public static List<Item> items = new ArrayList<>();
  public static List<Enemy> enemies = new ArrayList<>();
  public static Entity[][] entityMap = new Entity[numCol][numRow];

  public static Entity getEntity(int x, int y) {
    return x < numCol && y < numRow ? entityMap[x][y] : null;
  }

  public static void setEntity(int x, int y, Entity entity) {
    entityMap[x][y] = entity;
  }

  public static void reset() {
    bomber = null;
    entities.clear();
    statics.clear();
    bombs.clear();
    bricks.clear();
    explosions.clear();
    items.clear();
    enemies.clear();
  }

  public static void readMap() {
    Scanner scanner;
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
            entity = new Wall(j, i);
            statics.add(entity);
          }
          case '*' -> {
            entity = new Brick(j, i);
            statics.add(new Grass(j, i));
            bricks.add((Brick) entity);
          }
          case 'p' -> {
            bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
            entity = new Grass(j, i);
            statics.add(entity);
            entities.add(bomber);
          }
          case 'f' -> {
            entity = new Brick(j, i);
            statics.add(new Grass(j, i));
            bricks.add((Brick) entity);
            items.add(new FlameItem(j, i));
          }
          case 'b' -> {
            entity = new Brick(j, i);
            statics.add(new Grass(j, i));
            bricks.add((Brick) entity);
            items.add(new BombItem(j, i));
          }
          case 's' -> {
            entity = new Brick(j, i);
            statics.add(new Grass(j, i));
            bricks.add((Brick) entity);
            items.add(new SpeedItem(j, i));
          }
          case 'x' -> {
            entity = new Brick(j, i);
            statics.add(new Grass(j, i));
            bricks.add((Brick) entity);
            items.add(new Portal(j, i));
          }
          case '1' -> {
            entity = new Grass(j, i);
            statics.add(entity);
            enemies.add(new Balloom(j, i, null));
          }
          case '2' -> {
            entity = new Grass(j, i);
            statics.add(entity);
            enemies.add(new Oneal(j, i, null));
          }
          case '3' -> {
            entity = new Grass(j, i);
            statics.add(entity);
            enemies.add(new Kondoria(j, i, null));
          }
          case '4' -> {
            entity = new Grass(j, i);
            statics.add(entity);
            enemies.add(new Doll(j, i, null));
          }
          case '5' -> {
            entity = new Grass(j, i);
            statics.add(entity);
            enemies.add(new Minvo(j, i, null));
          }
          default -> {
            entity = new Grass(j, i);
            statics.add(entity);
          }
        }
        setEntity(j, i, entity);
      }
    }
    scanner.close();
  }
}
