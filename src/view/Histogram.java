package view;

import java.awt.*;

import javax.swing.JPanel;

import model.Color;

/**
 * class responsible for drawing the red, blue, green and intensity histograms of an image.
 */
public class Histogram extends JPanel implements IHistogram {

  private Color[][] image;

  private int[] reds;
  private int[] greens;
  private int[] blues;
  private int[] intensity;

  /**
   * creates a histogram.
   */
  public Histogram() {
    super();
    reds = new int[256];
    greens = new int[256];
    blues = new int[256];
    intensity = new int[256];
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (image == null) {
      return;
    }
    super.paintComponent(g);
    this.calculateHistograms();

    //draw red histogram
    double max = 0.0;
    for (int i = 0; i < reds.length; i++) {
      if (reds[i] > max) {
        max = reds[i];
      }
    }

    double scale = 99 / max;
    g.setColor(new java.awt.Color(255, 0, 0));
    for (int i = 1; i < reds.length; i++) {
      g.drawLine(50 + i - 1, 100 - (int) (reds[i - 1] * scale),
              50 + i, 100 - (int) (reds[i] * scale));
    }

    //draw green histogram
    max = 0.0;
    for (int i = 0; i < reds.length; i++) {
      if (greens[i] > max) {
        max = greens[i];
      }
    }

    scale = 99 / max;
    g.setColor(new java.awt.Color(0, 255, 0));
    for (int i = 1; i < greens.length; i++) {
      g.drawLine(50 + i - 1, 199 - (int) (greens[i - 1] * scale),
              50 + i, 199 - (int) (greens[i] * scale));
    }

    //draw  blue histogram
    max = 0.0;
    for (int i = 0; i < blues.length; i++) {
      if (blues[i] > max) {
        max = blues[i];
      }
    }

    scale = 99 / max;
    g.setColor(new java.awt.Color(0, 0, 255));
    for (int i = 1; i < blues.length; i++) {
      g.drawLine(50 + i - 1, 299 - (int) (blues[i - 1] * scale),
              50 + i, 299 - (int) (blues[i] * scale));
    }

    //draw  intensity histogram
    max = 0.0;
    for (int i = 0; i < intensity.length; i++) {
      if (intensity[i] > max) {
        max = intensity[i];
      }
    }

    scale = 99 / max;
    g.setColor(new java.awt.Color(111, 111, 111));
    for (int i = 1; i < intensity.length; i++) {
      g.drawLine(50 + i - 1, 399 - (int) (intensity[i - 1] * scale),
              50 + i, 399 - (int) (intensity[i] * scale));
    }
  }

  /**
   * calculates the red, blue, green and intensity frequencies.
   */
  private void calculateHistograms() {
    if (image == null) {
      return;
    }
    reds = new int[256];
    greens = new int[256];
    blues = new int[256];
    intensity = new int[256];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        reds[image[i][j].red] += 1;
        greens[image[i][j].green] += 1;
        blues[image[i][j].blue] += 1;
        int in = (int) (image[i][j].red / 3.0 +
                image[i][j].green / 3.0 +
                image[i][j].blue / 3.0);
        intensity[in] += 1;
      }
    }
  }

  @Override
  public void setImage(Color[][] image) {
    if (image.length > 0 && image[0].length > 0) {
      this.image = image;
    }
  }

  @Override
  public int[][] getHistograms() {
    if (image == null) {
      return null;
    }
    this.calculateHistograms();
    return new int[][]{reds, blues, greens, intensity};
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(400, 420);
  }
}
