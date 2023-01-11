package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * tests for Color class.
 */
public class ColorTest {

  /**
   * tests that constructor enforces the colors between 0 and 255.
   */
  @Test
  public void testConstructor() {
    Color c1 = new Color(100, 100, 100);
    assertEquals(100, c1.red);
    assertEquals(100, c1.green);
    assertEquals(100, c1.blue);

    Color c2 = new Color(300, 300, 300);
    assertEquals(255, c2.red);
    assertEquals(255, c2.green);
    assertEquals(255, c2.blue);

    Color c3 = new Color(-1, -1, -1);
    assertEquals(0, c3.red);
    assertEquals(0, c3.green);
    assertEquals(0, c3.blue);

    Color c4 = new Color(300, -1, 100);
    assertEquals(255, c4.red);
    assertEquals(0, c4.green);
    assertEquals(100, c4.blue);
  }
}
