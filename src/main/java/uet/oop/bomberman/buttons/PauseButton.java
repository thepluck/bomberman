package uet.oop.bomberman.buttons;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.SoundPlayer;

public class PauseButton extends UIButton {
  public PauseButton(int width, int height) {
    super(925, 1696, width, height, 50, 50);
    setOnMouseClicked(e -> {
      Enemy.toggleAll();
      ImageView view = new ImageView(new Image(Library.class.getResource("/textures/buttons.png").toExternalForm()));
      if (BombermanGame.paused) {
        view.setViewport(new Rectangle2D(569, 1702, width, height));
      } else {
        view.setViewport(new Rectangle2D(925, 1696, width, height));
      }
      view.setFitHeight(50);
      view.setFitWidth(50);
      setGraphic(view);
    });
  }
}
