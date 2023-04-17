package uet.oop.bomberman.processors;

import uet.oop.bomberman.graphics.Sprite;

public class Library {
  public static final int MAX_INTERSECTION = 6;


  /**
   * Check weather the intersection of two rectangles is greater than MAX_INTERSECTION.
   */
  public static boolean isIntersecting(int x1, int y1, int x2, int y2) {
    return Math.min(x1, x2) + Sprite.SCALED_SIZE - Math.max(x1, x2) > MAX_INTERSECTION
        && Math.min(y1, y2) + Sprite.SCALED_SIZE - Math.max(y1, y2) > MAX_INTERSECTION;
  }
}
