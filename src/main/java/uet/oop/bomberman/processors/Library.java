package uet.oop.bomberman.processors;

import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Library {
  public static final int MAX_INTERSECTION = 6;
  public static int[][] lastVisited = new int[105][105];
  public static DynamicEntity.Direction[][] bestDirection = new DynamicEntity.Direction[105][105];
  public static int timer;

  /**
   * Get the intersection of two segments.
   */
  public static int getIntersection(int l, int r) {
    return Math.min(l, r) + Sprite.SCALED_SIZE - Math.max(l, r) - MAX_INTERSECTION;
  }

  /**
   * Check weather the intersection of two rectangles is greater than MAX_INTERSECTION.
   */
  public static boolean isIntersecting(int x1, int y1, int x2, int y2) {
    return getIntersection(x1, x2) > 0 && getIntersection(y1, y2) > 0;
  }

  public static boolean isInside(int x, int y) {
    return x >= 0 && x < Map.width && y >= 0 && y < Map.height;
  }

  public static int compress(int x, int y) {
    return x * Map.height + y;
  }

  public static int extractX(int compressed) {
    return compressed / Map.height;
  }

  public static int extractY(int compressed) {
    return compressed % Map.height;
  }

  public static void breathFirstSearch(int x, int y) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(compress(x, y));
    timer++;
    lastVisited[x][y] = timer;
    bestDirection[x][y] = DynamicEntity.Direction.STAND;
    while (!queue.isEmpty()) {
      int curX = extractX(queue.peek());
      int curY = extractY(queue.peek());
      queue.remove();
      for (DynamicEntity.Direction direction : DynamicEntity.Direction.values()) {
        int nextX = curX, nextY = curY;
        switch (direction) {
          case UP -> nextY++;
          case DOWN -> nextY--;
          case LEFT -> nextX++;
          case RIGHT -> nextX--;
        }
        if (!isInside(nextX, nextY)) {
          continue;
        }
        if (lastVisited[nextX][nextY] == timer) {
          continue;
        }
        if (!(Map.getEntity(nextX, nextY) instanceof Grass)) {
          continue;
        }
        lastVisited[nextX][nextY] = timer;
        bestDirection[nextX][nextY] = direction;
        queue.add(compress(nextX, nextY));
      }
    }
  }

  public static DynamicEntity.Direction getBestDirection(int x, int y) {
    assert x >= 0 && x < Map.width && y >= 0 && y < Map.height;
    if (lastVisited[x][y] != timer) return DynamicEntity.Direction.STAND;
    return bestDirection[x][y];
  }

  public static int getDistanceToBomber(int x, int y) {
    return Math.abs(x - Map.bomber.getX()) + Math.abs(y - Map.bomber.getY());
  }
}