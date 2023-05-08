package uet.oop.bomberman.entities.basis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.entities.bombers.Explosion;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.Map;
import uet.oop.bomberman.processors.SoundPlayer;

public abstract class Entity {
  protected int x;
  protected int y;
  protected Image image;
  protected SoundPlayer sound;

  //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
  public Entity(int xUnit, int yUnit, Image image) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.image = image;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getGridX() {
    return (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
  }

  public int getGridY() {
    return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(image, x, y);
  }

  public abstract void update();

  public abstract void updateImage();

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  /// TO DO: xử lý nổ
  public boolean isCollidingExplosion() {
    for (Bomb bomb : Map.bombs) {
      if (bomb.isExploded() && isColliding(bomb)) {
        return true;
      }
    }
    for (Explosion explosion : Map.explosions) {
      if (isColliding(explosion)) {
        return true;
      }
    }
    return false;
  }

  /// TO DO: xử lý va chạm
  public boolean isColliding(Entity e) {
    return Library.isIntersecting(x, y, e.x, e.y);
  }

  public void toggle() {
    if (sound != null) {
      sound.toggle();
    }
  }
}