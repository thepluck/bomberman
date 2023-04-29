package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
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
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.*;

import javax.sound.sampled.Clip;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BombermanGame extends Application {
  public static final int DEFAULT_BFS_COUNTDOWN = 30;
  private static final long REFRESH_TIME = 10000000;

  public static GraphicsContext gc;
  public static Canvas canvas;
  public static Scene scene;
  public static Pane root;
  public static Stage stage;
  public static AnimationTimer timer;
  public static int bfsCountDown = DEFAULT_BFS_COUNTDOWN;

  public static void main(String[] args) {
    SoundPlayer backgroundMusicPlayer = new SoundPlayer("/sounds/background.wav", Clip.LOOP_CONTINUOUSLY, 10);
    Application.launch(BombermanGame.class);
  }

  public static void endingScene(String imagePath) {
    timer.stop();
    Task<Void> sleeper = new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return null;
      }
    };
    try {
      ImageView view = new ImageView(BombermanGame.class.getResource(imagePath).toExternalForm());
      canvas.setHeight(600);
      canvas.setWidth(800);
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
    endingScene("/images/gameover.jpg");
  }

  public static void victoryScene() {
    endingScene("/images/victory.jpg");
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
        case SPACE -> {
          if (Map.bombs.size() == Map.bomber.getBombLimit()) {
            return;
          }
          int gridX = Map.bomber.getGridX();
          int gridY = Map.bomber.getGridY();
          Map.bombs.add(new Bomb(
              gridX, gridY,
              Map.bomber.getBombLength()
          ));
        }
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
    ImageView view = new ImageView(getClass().getResource("/images/background.png").toExternalForm());

    canvas = new Canvas(800, 600);
    view.setFitHeight(canvas.getHeight());
    view.setFitWidth(canvas.getWidth());

    LevelButton[] levelButtons = new LevelButton[3];
    levelButtons[0] = new LevelButton(1, 146, 89);
    levelButtons[1] = new LevelButton(2, 622, 89);
    levelButtons[2] = new LevelButton(3, 146, 306);

    int xCenter = 350;
    int yCenter = 300;
    levelButtons[0].setLayoutX(xCenter - 300);
    levelButtons[0].setLayoutY(yCenter - 100);
    levelButtons[1].setLayoutX(xCenter - 300);
    levelButtons[1].setLayoutY(yCenter);
    levelButtons[2].setLayoutX(xCenter - 300);
    levelButtons[2].setLayoutY(yCenter + 100);

    root = new Pane(view);
    root.getChildren().add(canvas);
    root.getChildren().addAll(levelButtons);
    scene = new Scene(root);
    BombermanGame.stage = stage;
    stage.setTitle("Bomberman Game");
    stage.setScene(scene);
    stage.show();
  }
}