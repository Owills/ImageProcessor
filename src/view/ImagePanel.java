package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Color;

/**
 * class responsible for the drawing the image that is being edited.
 */
public class ImagePanel extends JPanel implements ImagePreviewBox {

  private Color[][] image;
  int pixelSize;
  private int width;
  private int height;
  private final GuiView view;
  private int previewCol;
  private int previewRow;

  /**
   * creates and ImagePanel.
   */
  public ImagePanel(GuiView view) {
    super();
    this.view = view;
    this.width = 10000;
    this.height = 40000;
    this.pixelSize = 1;

    MouseListener m = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        previewCol = (e.getX()) / pixelSize;
        previewRow = (e.getY()) / pixelSize;
        if (view.getPartial()) {
          view.setPreview(image);
          view.updatePreview();
          view.refresh();
        }
      }
    };
    this.addMouseListener(m);
  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(this.width, this.height);
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (image == null) {
      return;
    }
    super.paintComponent(g);
    int originX = 0;
    int originY = 0;
    for (int i = 0; i < this.image.length; i++) {
      for (int j = 0; j < this.image[0].length; j++) {
        g.setColor(new java.awt.Color(this.image[i][j].red,
                this.image[i][j].green, this.image[i][j].blue));
        g.fillRect(originX + j * pixelSize, originY + i * pixelSize, pixelSize, pixelSize);
      }
    }

    g.setColor(new java.awt.Color(255,0,0));

    this.height = this.image.length * pixelSize;
    this.width = this.image[0].length * pixelSize;

    if (this.view.getPartial()) {
      int x = this.setPreviewX();
      int y = this.setPreviewY();
      g.drawRect(x, y, 200, 200);
    }
  }

  private int setPreviewX() {
    int x = previewCol*pixelSize-100;
    if (x < 0 ) {
      x = 0;
    }
    if (x + 200 > this.width) {
      x = this.width - 200;
    }
    return x;
  }

  private int setPreviewY() {
    int y = previewRow*pixelSize-100;
    if (previewRow*pixelSize-100 < 0 ) {
      y = 0;
    }
    if (y + 200 > this.height) {
      y = this.width - 200;
    }
    return y;
  }


  @Override
  public void setImage(Color[][] image) {
    if (image.length > 0 && image[0].length > 0) {
      this.image = image;
      pixelSize = 200 / image.length;
      if (pixelSize == 0) {
        pixelSize = 1;
      }
      previewRow = 0;
      previewCol = 0;
    }
  }

  @Override
  public boolean isWithinPreview(int row, int col) {
    int x = this.setPreviewX();
    int y = this.setPreviewY();
    return row >= y && row <= y + 200 && col >= x && col <= x + 200;
  }
}
