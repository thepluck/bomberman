package uet.oop.bomberman.processors;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
public class SoundPlayer extends JFrame {
  public SoundPlayer(String path, int loopTimes, float lowerVolume) {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    try {
      URL url = this.getClass().getResource(path);
      AudioInputStream audio = AudioSystem.getAudioInputStream(url);
      Clip clip = AudioSystem.getClip();
      clip.open(audio);
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-lowerVolume);
      clip.start();
      clip.loop(loopTimes);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException err) {
      err.printStackTrace();
    }
  }
}
