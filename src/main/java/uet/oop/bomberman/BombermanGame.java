package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.bomberman.buttons.LevelButton;
import uet.oop.bomberman.buttons.PauseButton;
import uet.oop.bomberman.buttons.SoundButton;
import uet.oop.bomberman.buttons.UIButton;
import uet.oop.bomberman.entities.basis.DynamicEntity;
import uet.oop.bomberman.entities.bombers.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.processors.*;

import javax.sound.sampled.Clip;

public class BombermanGame extends Application {
  public static final int DEFAULT_BFS_COUNTDOWN = 30;
  private static final long REFRESH_TIME = 10000000;
  public static GraphicsContext gc;
  public static Canvas canvas;
  public static Scene scene;
  public static Pane root;
  public static Stage stage;
  public static AnimationTimer timer;
  public static int bfsCountDown = 0;
  public static SoundPlayer backgroundMusicPlayer;
  public static boolean paused = false;

  public static void main(String[] args) {
    backgroundMusicPlayer = new SoundPlayer("/sounds/background.wav", Clip.LOOP_CONTINUOUSLY, 10);
    Application.launch(BombermanGame.class);
  }

  public static void endingScene(String imagePath) {

  }

  public static void defeatedScene() {
    timer.stop();
    try {
      UIButton resetButton = new UIButton(1300, 168, 600, 225, 220, 100);
      resetButton.setOnAction(e -> {
        timer.stop();
        stage.close();
        createMainScreen();
      });
      ImageView view = new ImageView(BombermanGame.class.getResource("/images/gameover.jpg").toExternalForm());
      canvas.setHeight(600);
      canvas.setWidth(1100);
      resetButton.setLayoutX(450);
      resetButton.setLayoutY(20);
      view.setFitHeight(canvas.getHeight());
      view.setFitWidth(canvas.getWidth());
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      root = new Pane(view);
      root.getChildren().add(canvas);
      root.getChildren().add(resetButton);
      scene = new Scene(root);
      scene.getStylesheets().add(BombermanGame.class.getResource("/stylesheets/LevelButton.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public static void victoryScene() {
    timer.stop();
    try {
      ImageView view = new ImageView(BombermanGame.class.getResource("/images/victory.jpg").toExternalForm());
      canvas.setHeight(600);
      canvas.setWidth(1100);
      view.setFitHeight(canvas.getHeight());
      view.setFitWidth(canvas.getWidth());
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      root = new Pane(view);
      root.getChildren().add(canvas);
      scene = new Scene(root);
      scene.getStylesheets().add(BombermanGame.class.getResource("/stylesheets/LevelButton.css").toExternalForm());
      scene.setOnKeyPressed(keyEvent -> System.exit(0));
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public static void startGame() {
    Map.readMap();
    SoundButton soundButton = new SoundButton(156, 156);
    soundButton.setLayoutX(1380);
    soundButton.setLayoutY(0);

    UIButton homeButton = new UIButton(537, 1476, 198, 178, 50, 50);
    homeButton.setOnMouseClicked(e -> {
      timer.stop();
      stage.close();
      createMainScreen();
    });
    homeButton.setLayoutX(1430);
    homeButton.setLayoutY(0);

    PauseButton pauseButton = new PauseButton(156, 156);
    pauseButton.setLayoutX(1330);
    pauseButton.setLayoutY(0);

    stage.close();
    // Tao canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * Map.width, Sprite.SCALED_SIZE * Map.height);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    root = new Pane();
    root.getChildren().add(canvas);
    root.getChildren().add(soundButton);
    root.getChildren().add(homeButton);
    root.getChildren().add(pauseButton);
    // Tao scene
    scene = new Scene(root);

    scene.setOnKeyPressed(keyEvent -> {
      if (paused) {
        return;
      }
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

    scene.getStylesheets().add(BombermanGame.class.getResource("/stylesheets/LevelButton.css").toExternalForm());

    // Them scene vao stage
    stage.setScene(scene);
    stage.show();

    canvas.requestFocus();

    timer = new AnimationTimer() {
      private static long lastTimestamp = System.nanoTime();
      @Override
      public void handle(long now) {
        canvas.requestFocus();
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

  public static void createLevelSelection() {
    LevelButton[] levelButtons = new LevelButton[9];
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++) {
        LevelButton button;
        button = new LevelButton(
                i * 3 + j + 1, 474 + 474 * i,
                541 + 540 * j, 304, 304);
        button.setLayoutX(100 + 100 * i);
        button.setLayoutY(250 + 100 * j);
        levelButtons[i * 3 + j] = button;
      }
    root.getChildren().addAll(levelButtons);
  }

  public static void createMainScreen() {
    ImageView view = new ImageView(BombermanGame.class.getResource("/images/background.jpg").toExternalForm());

    canvas = new Canvas(1100, 600);
    view.setFitHeight(canvas.getHeight());
    view.setFitWidth(canvas.getWidth());

    UIButton newGameButton = new UIButton(100, 168, 600, 225, 220, 100);
    newGameButton.setOnAction(e -> {
      Map.level = 1;
      startGame();
    });
    newGameButton.setLayoutX(100);
    newGameButton.setLayoutY(250);

    UIButton levelSelectionButton = new UIButton(712, 412, 600, 225, 220, 100);
    levelSelectionButton.setOnAction(e -> {
      newGameButton.setVisible(false);
      levelSelectionButton.setVisible(false);
      createLevelSelection();
    });
    levelSelectionButton.setLayoutX(100);
    levelSelectionButton.setLayoutY(350);

    root = new Pane(view, newGameButton, levelSelectionButton);
    scene = new Scene(root);
    scene.getStylesheets().add(BombermanGame.class.getResource("/stylesheets/LevelButton.css").toExternalForm());
    stage.setScene(scene);
    stage.show();
  }


  @Override
  public void start(Stage stage) {
    BombermanGame.stage = stage;
    createMainScreen();
  }
}