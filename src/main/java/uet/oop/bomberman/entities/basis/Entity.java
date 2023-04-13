package uet.oop.bomberman.entities.basis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity extends Vector {
  protected Image img;

  //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
  public Entity(int xUnit, int yUnit, Image img) {
    super(xUnit * Sprite.SCALED_SIZE, yUnit * Sprite.SCALED_SIZE);
    this.img = img;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(img, x, y);
  }

  public abstract void update();

  public abstract void updateImage();

  public Image getImg() {
    return img;
  }

  public void setImg(Image img) {
    this.img = img;
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