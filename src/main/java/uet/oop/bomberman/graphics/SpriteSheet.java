package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {

  public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);
  public final int SIZE;
  private final String path;
  public int[] pixels;
  public BufferedImage image;

  public SpriteSheet(String path, int size) {
    this.path = path;
    SIZE = size;
    pixels = new int[SIZE * SIZE];
    load();
  }

  private void load() {
    try {
      URL a = SpriteSheet.class.getResource(path);
      image = ImageIO.read(a);
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}