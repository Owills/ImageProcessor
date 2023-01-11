package view;

import org.junit.Test;

import model.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * tests for histogram.
 */
public class HistogramTests {

  @Test
  public void voidTestHistogram() {
    Histogram h = new Histogram();
    assertNull(h.getHistograms());
    h.setImage(new Color[][]{{new Color(0, 0, 0),
            new Color(255, 255, 255)}});
    int[][] hs = h.getHistograms();
    int[] reds = hs[0];
    int[] greens = hs[1];
    int[] blues = hs[2];
    int[] intensity = hs[3];
    assertEquals(1, intensity[0]);
    assertEquals(1, intensity[255]);
    assertEquals(1, reds[0]);
    assertEquals(1, reds[255]);
    assertEquals(1, blues[0]);
    assertEquals(1, blues[255]);
    assertEquals(1, greens[0]);
    assertEquals(1, greens[255]);
    for (int i = 1; i < 255; i++) {
      assertEquals(0, reds[i]);
      assertEquals(0, greens[i]);
      assertEquals(0, blues[i]);
      assertEquals(0, intensity[i]);
    }
  }


}
