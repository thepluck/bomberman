package uet.oop.bomberman.entities.basis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
  protected int x;
  protected int y;
  protected Image image;

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
  public boolean checkCollidingExplosion() {
    return false;
  }

  /// TO DO: xử lý va chạm
  public boolean checkCollision(Entity e) {
    return false;
  }
}