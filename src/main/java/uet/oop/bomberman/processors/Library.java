package uet.oop.bomberman.processors;

import uet.oop.bomberman.graphics.Sprite;

public class Library {
  public static final int MAX_INTERSECTION = 6;

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
}