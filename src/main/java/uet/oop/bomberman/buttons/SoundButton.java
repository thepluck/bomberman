package uet.oop.bomberman.buttons;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.entities.bombers.Bomber;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.SoundPlayer;

public class SoundButton extends UIButton {
  public SoundButton(int width, int height) {
    super(1275, 1481, width, height, 50, 50);
    setOnMouseClicked(e -> {
      SoundPlayer.toggleAll();
      ImageView view = new ImageView(new Image(Library.class.getResource("/textures/buttons.png").toExternalForm()));
      if (SoundPlayer.muted) {
        view.setViewport(new Rectangle2D(1625, 1481, width, height));
      } else {
        view.setViewport(new Rectangle2D(1275, 1481, width, height));
      }
      view.setFitHeight(50);
      view.setFitWidth(50);
      setGraphic(view);
    });
  }
}
