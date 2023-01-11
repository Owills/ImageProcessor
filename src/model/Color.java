package model;

/**
 * rgd representation of a color.
 */
public class Color {

  //when we edit the color is images we will do so by creating new color
  public int red;
  // INVARIANT red is a number between 0 and 255
  public int green;
  // INVARIANT blue is a number between 0 and 255
  public int blue;
  // INVARIANT green is a number between 0 and 255

  public int alpha;
  // INVARIANT green is a number between 0 and 255

  /**
   * given rgb components of a color, creates a color.
   *
   * @param r red component of a color
   * @param g green component of a color
   * @param b blue component of a color
   */
  public Color(int r, int g, int b) {
    this.red = this.capColor(r);
    this.green = this.capColor(g);
    this.blue = this.capColor(b);
    this.alpha = 0;
  }

  /**
   * given rgb components of a color, creates a color.
   *
   * @param r     red component of a color
   * @param g     green component of a color
   * @param b     blue component of a color
   * @param alpha transparency component of a color
   */
  public Color(int r, int g, int b, int alpha) {
    this.red = this.capColor(r);
    this.green = this.capColor(g);
    this.blue = this.capColor(b);
    this.alpha = this.capColor(alpha);
  }

  /**
   * caps given color part (red, green, or blue part of a color) to ensure it is between 0 and 255.
   *
   * @param color integer representation of a color
   * @return the color between (0,225)
   */
  private int capColor(int color) {
    if (color > 225) {
      color = 255;
    } else if (color < 0) {
      color = 0;
    }
    return color;
  }
}
