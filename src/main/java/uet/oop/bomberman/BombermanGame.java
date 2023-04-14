package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Map;
import uet.oop.bomberman.processors.Display;

public class BombermanGame extends Application {
  public static GraphicsContext gc;
  public static Canvas canvas;
  public static Scene scene;
  public static Group root;
  public static Stage stage;
  public static AnimationTimer timer;

  public static void main(String[] args) {
    Map.readMap();
    Application.launch(BombermanGame.class);
  }

  public static void defeatedScene() {}

  public static void victoryScene() {}

  @Override
  public void start(Stage stage) {
    // Tao Canvas
    BombermanGame.stage = stage;
    canvas = new Canvas(Sprite.SCALED_SIZE * Map.numCol, Sprite.SCALED_SIZE * Map.numRow);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    root = new Group();
    root.getChildren().add(canvas);

    // Tao scene
    scene = new Scene(root);
    stage.setTitle("Bomberman Game");

    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
          case UP -> {
            Map.bomber.setLocked(false);
            Map.bomber.setDirection(DynamicEntity.Direction.UP);
          }
          case DOWN -> {
            Map.bomber.setLocked(false);
            Map.bomber.setDirection(DynamicEntity.Direction.DOWN);
          }
          case LEFT -> {
            Map.bomber.setLocked(false);
            Map.bomber.setDirection(DynamicEntity.Direction.LEFT);
          }
          case RIGHT -> {
            Map.bomber.setLocked(false);
            Map.bomber.setDirection(DynamicEntity.Direction.RIGHT);
          }
        }
      }
    });

    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
          case UP, DOWN, LEFT, RIGHT -> {
            Map.bomber.setLocked(true);
          }
        }
      }
    });

    // Them scene vao stage
    stage.setScene(scene);
    stage.show();

    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        Display.render();
        Display.update();
      }
    };

    timer.start();
  }
}