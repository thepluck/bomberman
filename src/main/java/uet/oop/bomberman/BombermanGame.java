package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.Display;
import uet.oop.bomberman.processors.Library;
import uet.oop.bomberman.processors.Map;

import java.util.concurrent.atomic.AtomicBoolean;

public class BombermanGame extends Application {
  public static final int DEFAULT_BFS_COUNTDOWN = 0;

  public static GraphicsContext gc;
  public static Canvas canvas;
  public static Scene scene;
  public static Pane root;
  public static Stage stage;
  public static AnimationTimer timer;
  public static int bfsCountDown = DEFAULT_BFS_COUNTDOWN;

  public static void main(String[] args) {
    Application.launch(BombermanGame.class);
  }

  public static void endingScene(String imagePath) {
    timer.stop();
    try {
      ImageView view = new ImageView(new Image(imagePath));
      view.setFitHeight(canvas.getHeight());
      view.setFitWidth(canvas.getWidth());
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      root = new Pane(view);
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
    endingScene("/images/defeat.png");
  }

  public static void victoryScene() {
    endingScene("/images/victory.png");
  }

  public static void startGame() {
    stage.close();
    // Tao canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * Map.width, Sprite.SCALED_SIZE * Map.height);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    root = new Pane();
    root.getChildren().add(canvas);
    // Tao scene
    scene = new Scene(root);

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
        case UP, DOWN, LEFT, RIGHT -> Map.bomber.setLocked(true);
      }
    });

    // Them scene vao stage
    stage.setScene(scene);
    stage.show();

    timer = new AnimationTimer() {
      private static long lastTimestamp = System.nanoTime();
      private static final long REFRESH_TIME = 10000000;
      @Override
      public void handle(long now) {
        if (now - lastTimestamp > REFRESH_TIME) {
          if (bfsCountDown > 0) {
            bfsCountDown--;
          } else {
            bfsCountDown = DEFAULT_BFS_COUNTDOWN;
            Library.breathFirstSearch(Map.bomber.getGridX(), Map.bomber.getGridY());
          }
          Display.render();
          Display.update();
          if (Map.bomber.isFullyDead()) {
            defeatedScene();
          }
          lastTimestamp = System.nanoTime();
        }

      }
    };

    timer.start();
  }

  @Override
  public void start(Stage stage) {
    // Canh mo dau
    ImageView view = new ImageView(getClass().getResource("/images/background.png").toExternalForm());
    System.err.println(getClass().getResource("/images/background.png").toExternalForm());
    view.setFitHeight(600);
    view.setFitWidth(800);

    ImageView level1View = new ImageView(getClass().getResource("/textures/levels.png").toExternalForm());
    level1View.setViewport(new Rectangle2D(146, 89, 433, 157));
    level1View.setFitHeight(70);
    level1View.setFitWidth(200);

    ImageView level2View = new ImageView(getClass().getResource("/textures/levels.png").toExternalForm());
    level2View.setViewport(new Rectangle2D(622, 89, 433, 157));
    level2View.setFitHeight(70);
    level2View.setFitWidth(200);

    root = new Pane(view);
    canvas = new Canvas(800, 600);

    // Tao button
    Button level1 = new Button();
    Button level2 = new Button();

    level1.setOnAction(e -> {
      Map.readMap(1);
      level1.setVisible(false);
      level2.setVisible(false);
      startGame();
    });

    level2.setOnAction(e -> {
      Map.readMap(2);
      level1.setVisible(false);
      level2.setVisible(false);
      startGame();
    });

    level1.setGraphic(level1View);
    level2.setGraphic(level2View);

    int xCenter = 350;
    int yCenter = 300;
    level1.setLayoutX(xCenter - 300);
    level1.setLayoutY(yCenter);
    level2.setLayoutX(xCenter - 300);
    level2.setLayoutY(yCenter + 100);

    root.getChildren().add(canvas);
    root.getChildren().addAll(level1, level2);
    scene = new Scene(root);
    BombermanGame.stage = stage;
    stage.setTitle("Bomberman Game");
    stage.setScene(scene);
    stage.show();
  }
}