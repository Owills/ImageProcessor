
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * tests for partial image manipulation.
 */
public class TestPartial {

  @Test
  public void testPartialWrongSizeMask () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask200.png mask " +
            "partial blur mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialInvalidArgument () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "partial blur mask huh newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialInvalidMask () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "partial blur newExample newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialUnknown () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "partial huh mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialSave () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "partial save mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialLoad () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "partial load mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialDownScale () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "partial downscale mask 1 1 newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }

  @Test
  public void testPartialBrighten () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "brighten 10 newExample newExample2 " +
            "partial brighten mask 10 newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialDarken () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "darken 10 newExample newExample2 " +
            "partial darken mask 10 newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialFlipHorizontal () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "horizontal-flip newExample newExample2 " +
            "partial horizontal-flip mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialFlipVertical () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "vertical-flip newExample newExample2 " +
            "partial vertical-flip mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialGreyScale () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "greyscale newExample newExample2 " +
            "partial greyscale mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialSepia () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "sepia newExample newExample2 " +
            "partial sepia mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialIntensity () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "intensity-component newExample newExample2 " +
            "partial intensity-component mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialLuma () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "luma-component newExample newExample2 " +
            "partial luma-component mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialValue () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "value-component newExample newExample2 " +
            "partial value-component mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialGreen () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "green-component newExample newExample2 " +
            "partial green-component mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialBlue () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "blue-component newExample newExample2 " +
            "partial blue-component mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialRed () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "red-component newExample newExample2 " +
            "partial red-component mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialSharpen () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "sharpen newExample newExample2 " +
            "partial sharpen mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] command = model.getImage("newExample2");
    Color[][] partialCommand = model.getImage("newExample3");

    for (int i = 0; i < partialCommand.length; i ++ ) {
      for (int j = 0; j < partialCommand[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(command[i][j].red, partialCommand[i][j].red);
          assertEquals(command[i][j].green, partialCommand[i][j].green);
          assertEquals(command[i][j].blue, partialCommand[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialCommand[i][j].red);
          assertEquals(original[i][j].green, partialCommand[i][j].green);
          assertEquals(original[i][j].blue, partialCommand[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testPartialBlur () {
    Reader input = new StringReader("load example.ppm newExample " +
            "load mask.png mask " +
            "blur newExample newExample2 " +
            "partial blur mask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] blur = model.getImage("newExample2");
    Color[][] partialBlur = model.getImage("newExample3");

    for (int i = 0; i < partialBlur.length; i ++ ) {
      for (int j = 0; j < partialBlur[0].length; j ++ ) {
        if (j < 2 ) {
          assertEquals(blur[i][j].red, partialBlur[i][j].red);
          assertEquals(blur[i][j].green, partialBlur[i][j].green);
          assertEquals(blur[i][j].blue, partialBlur[i][j].blue);
        } else {
          assertEquals(original[i][j].red, partialBlur[i][j].red);
          assertEquals(original[i][j].green, partialBlur[i][j].green);
          assertEquals(original[i][j].blue, partialBlur[i][j].blue);
        }
      }
    }
  }

  @Test
  public void testFullWhiteMask() {
    Reader input = new StringReader("load example.ppm newExample " +
            "brighten 255 newExample fullWhiteMask " +
            "sharpen newExample newExample2 " +
            "partial sharpen fullWhiteMask newExample newExample3" );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] original = model.getImage("newExample");
    Color[][] sharpen = model.getImage("newExample2");
    Color[][] partialSharpen = model.getImage("newExample3");
    assertFalse(this.voidCompareImages(sharpen, partialSharpen));
    assertTrue(this.voidCompareImages(original, partialSharpen));
  }

  @Test
  public void testFullBlackMask() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "darken 255 newExample fullBlackMask " +
            "sharpen newExample newExample2 " +
            "partial sharpen fullBlackMask newExample newExample3 " );
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] sharpen = model.getImage("newExample2");
    Color[][] partialSharpen = model.getImage("newExample3");
    assertTrue(this.voidCompareImages(sharpen, partialSharpen));
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
