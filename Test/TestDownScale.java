import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.CommandController;
import model.Color;
import model.CommandImageProcessingModel;
import model.CommandModel;
import view.BasicTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * tests for downscale command.
 */
public class TestDownScale {

  @Test
  public void testDownScaleToDifferentRatio() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale 1 2 newExample downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] original = model.getImage("newExample");
    Color[][] downscale = model.getImage("downscale");
    assertEquals(downscale.length, 2);
    assertEquals(downscale[0].length, 1);
  }

  @Test
  public void testDownScaleTo1by1() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale 1 1 newExample downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] original = model.getImage("newExample");
    Color[][] downscale = model.getImage("downscale");
    assertEquals(downscale.length, 1);
    assertEquals(downscale[0].length, 1);
  }

  @Test
  public void testDownScaleToSameSize() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale 4 4 newExample downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] original = model.getImage("newExample");
    Color[][] downscale = model.getImage("downscale");
    assertTrue(this.voidCompareImages(original,downscale));
  }

  @Test
  public void testDownScaleInvalidArguments() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale -1 -1 newExample downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testDownScaleInvalidArgumentsV4() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale 0 0 newExample downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testDownScaleInvalidArgumentsv2() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale 5 5 newExample downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testDownScaleInvalidArgumentsv3() {
    Reader input = new StringReader("load example.ppm newExample " +
            "downscale 4 4 huh downscale ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  /**
   * checks that each row and column entry of two iamges have the same r g b values.
   *
   * @param image1 the first image
   * @param image2 the second image
   * @return return true if the images are the same
   */
  protected boolean voidCompareImages(Color[][] image1, Color[][] image2) {
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        if (image1[i][j].red != image2[i][j].red) {
          return false;
        }
        if (image1[i][j].green != image2[i][j].green) {
          return false;
        }
        if (image1[i][j].blue != image2[i][j].blue) {
          return false;
        }
      }
    }

    return true;
  }
}
