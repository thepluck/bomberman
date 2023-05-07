package uet.oop.bomberman.buttons;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.Map;
public class UIButton extends Button {
  public UIButton(int x, int y, int width, int height, int realWidth, int realHeight) {
    ImageView view = new ImageView(new Image(Library.class.getResource("/textures/buttons.png").toExternalForm()));
    view.setViewport(new Rectangle2D(x, y, width, height));
    view.setFitHeight(realHeight);
    view.setFitWidth(realWidth);
    setGraphic(view);
  }
}