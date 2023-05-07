package uet.oop.bomberman.processors;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.basis.Enemy;
import uet.oop.bomberman.entities.basis.Item;
import uet.oop.bomberman.entities.bombers.Bomb;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer extends JFrame {
  public static boolean muted = false;
  private FloatControl gainControl;
  private float lowerVolume;
  public SoundPlayer(String path, int loopTimes, float lowerVolume) {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.lowerVolume = lowerVolume;
    try {
      URL url = this.getClass().getResource(path);
      AudioInputStream audio = AudioSystem.getAudioInputStream(url);
      Clip clip = AudioSystem.getClip();
      clip.open(audio);
      gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      if (muted) {
        gainControl.setValue(gainControl.getMinimum());
      } else {
        gainControl.setValue(-lowerVolume);
      }
      clip.start();
      clip.loop(loopTimes);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException err) {
      err.printStackTrace();
    }
  }

  public void toggle() {
    if (muted) {
      gainControl.setValue(gainControl.getMinimum());
    } else {
      gainControl.setValue(-lowerVolume);
    }
  }

  public static void toggleAll() {
    muted = !muted;
    BombermanGame.backgroundMusicPlayer.toggle();
    Map.bomber.toggle();
    for (Enemy enemy : Map.enemies) {
      enemy.toggle();
    }
    for (Item item : Map.items) {
      item.toggle();
    }
    for (Bomb bomb : Map.bombs) {
      bomb.toggle();
    }
  }
}
