package uet.oop.bomberman.buttons;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.Map;

public class LevelButton extends Button {
  public LevelButton(int level, int x, int y, int width, int height) {
    ImageView view = new ImageView(new Image(Library.class.getResource("/textures/levels.png").toExternalForm()));
    view.setViewport(new Rectangle2D(x, y, width, height));
    view.setFitHeight(75);
    view.setFitWidth(75);
    setGraphic(view);
    setOnAction(e -> {
      Map.level = level;
      BombermanGame.startGame();
    });
  }

}
