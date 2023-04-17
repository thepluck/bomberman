package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Display;
import uet.oop.bomberman.processors.Map;

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

  public static void endingScene(String imagePath) {
    timer.stop();
    try {
      ImageView view = new ImageView(new Image(imagePath));
      view.setFitHeight(canvas.getHeight());
      view.setFitWidth(canvas.getWidth());
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      root = new Group(view);
      root.getChildren().add(canvas);
      scene = new Scene(root);
      scene.setOnKeyPressed(keyEvent -> System.exit(0));
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public static void defeatedScene() {
    endingScene("res/images/defeat.png");
  }

  public static void victoryScene() {
    endingScene("res/images/victory.png");
  }

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

    scene.setOnKeyPressed(keyEvent -> {
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
    });


    scene.setOnKeyReleased(keyEvent -> {
      switch (keyEvent.getCode()) {
        case UP, DOWN, LEFT, RIGHT -> {
          Map.bomber.setLocked(true);
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