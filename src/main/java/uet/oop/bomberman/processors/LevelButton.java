package uet.oop.bomberman.processors;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;

public class LevelButton extends Button {
  public LevelButton(int level, int x, int y) {
    ImageView view = new ImageView(new Image(Library.class.getResource("/textures/levels.png").toExternalForm()));
    view.setViewport(new Rectangle2D(x, y, 433, 157));
    view.setFitHeight(70);
    view.setFitWidth(200);
    setGraphic(view);
    setOnAction(e -> {
      Map.level = level;
      Map.readMap();
      BombermanGame.startGame();
    });
  }

}
