package view;

import java.awt.*;

import javax.swing.*;

import model.Color;

/**
 * 200 by 200 image which displays the preview.
 */
public class Preview extends JPanel implements ImageInterface{
  private Color[][] image;
  private int pixelSize;
  /**
   * creates and ImagePanel.
   */
  public Preview() {
    super();
    this.pixelSize = 1;
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (image == null) {
      return;
    }
    super.paintComponent(g);
    for (int i = 0; i < this.image.length; i++) {
      for (int j = 0; j < this.image[0].length; j++) {
        g.setColor(new java.awt.Color(this.image[i][j].red,
                this.image[i][j].green, this.image[i][j].blue));
        g.fillRect(25 + j * pixelSize, 25 + i * pixelSize, pixelSize, pixelSize);
      }
    }
  }

  @Override
  public void setImage(Color[][] image) {
    if (image.length > 0 && image[0].length > 0) {
      this.image = image;
      pixelSize = 200 / image.length;
      if (pixelSize == 0) {
        pixelSize = 1;
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(250, 250);
  }
}
