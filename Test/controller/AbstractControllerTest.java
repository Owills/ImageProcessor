package controller;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import model.Color;
import model.ImageProcessingModel;
import view.BasicTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * tests that all controller should pass.
 */
public abstract class AbstractControllerTest {

  /**
   * test throws exception when calling 'load' with an invalid ppm file argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testNonExistentFile() throws IOException {
    Reader input = new FileReader("LoadNonExistentFile.txt");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'load' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingLoadArgument() throws IOException {
    Reader input = new FileReader("LoadMissingArgument.txt");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'save' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingSaveArgument() throws IOException {
    Reader input = new FileReader("SaveMissingArgument.txt");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'save' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidSaveArgument() throws IOException {
    Reader input = new FileReader("SaveMissingArgument.txt");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'brighten' without number arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidBrightenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "save newExample.ppm newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }


  /**
   * test throws exception when calling 'brighten' invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidBrightenArgumentV3() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "brighten 0 newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'brighten' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingBrightenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "brighten 13 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'darken' without number arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidDarkenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "darken type newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'darken' with invalid name arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidDarkenArgumentV3() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "darken 3 newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }


  /**
   * test throws exception when calling 'darken' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingDarkenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "darken 13 newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'vertical-flip' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingFlipVerticalArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "vertical-flip newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'vertical-flip' without invalid name arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidFlipVerticalArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "vertical-flip newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'horizontal-flip' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingFlipHorizontalArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "horizontal-flip newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'brightness-component' with invalid name arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidFlipHorizontalArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "horizontal-flip newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'value-component' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingValueArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "value-component newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'intensity-component' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingIntensityArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "intensity-component newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'luma-component' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingLumaArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "luma-component newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'red-component' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingRedArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "red-component newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'green-component' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingGreenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "green-component newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'blue-component' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testMissingBluergument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "blue-component newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'value-component' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidValueArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "value-component newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'intensity-component' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidIntensityArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "intensity-component newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'luma-component' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidLumaArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "luma-component newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'red-component' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidRedArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "red-component newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'green-component' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidGreenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "green-component newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'blue-component' with invalid name argument.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidBlueArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "blue-component newExample2 newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }


  /**
   * test calling invalid command.
   *
   * @throws IOException if the file is not found
   */
  @Test
  public void testInvalidCommand() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "what newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    assertThrows(IllegalStateException.class, controller::processImages);
  }


  /**
   * tests successful red component command.
   *
   * @throws IOException if the file is not found.
   */
  @Test
  public void testOneSuccessfulCommand() throws IOException {
    Reader input = new FileReader("OneSucessfulRedComponent.txt");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    //image generate from script
    model.load("newExampleTestRed.ppm", "newExampleRedTest1");
    //image generate from GIMP
    model.load("newExample-Red.ppm", "newExampleRedGIMP");
    Color[][] image1 = model.getImage("newExampleRedTest1");
    Color[][] image2 = model.getImage("newExampleRedGIMP");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load command.
   */
  @Test
  public void testLoadMethod() {
    Reader input = new StringReader("load newExample.ppm newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    assertNotNull(model.getImage("newExample"));
    assertEquals("Successfully loaded newExample.ppm as newExample.\n", output.toString());
  }

  /**
   * test case insensitivity in load.
   */
  @Test
  public void testCaseInsesitivty() {
    Reader input = new StringReader("LOAD newExample.ppm newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    assertNotNull(model.getImage("newExample"));
    assertEquals("Successfully loaded newExample.ppm as newExample.\n", output.toString());
  }

  /**
   * tests multiple successful commands in a row.
   *
   * @throws IOException if the file is not found.
   */
  @Test
  public void testMultipleSuccessfulCommand() throws IOException {
    Reader input = new FileReader("MultipleSuccessfulCommands.txt");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    //image generate from script
    model.load("newExample-vertical-horizontal.ppm",
            "newExampleTestBlueVerticalHorizontal");
    //image generate from GIMP
    model.load("newExample-vertical-horizontalGIMP.ppm",
            "newExampleTestBlueVerticalHorizontalGIMP");
    Color[][] image1 = model.getImage("newExampleTestBlueVerticalHorizontal");
    Color[][] image2 = model.getImage("newExampleTestBlueVerticalHorizontalGIMP");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load then save command.
   */
  @Test
  public void testLoadThenSave() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "save testLoadThenSave.ppm newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    assertNotNull(model.getImage("newExample"));
    assertEquals("Successfully loaded newExample.ppm as newExample.\n" +
            "Successfully saved newExample as testLoadThenSave.ppm.\n", output.toString());

  }

  /**
   * test Successful load then brighten command.
   */
  @Test
  public void testLoadThenBrighten() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "brighten 13 newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");

    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int red = image1[i][j].red + 13;
        int green = image1[i][j].green + 13;
        int blue = image1[i][j].blue + 13;
        image1[i][j] = new Color(red, green, blue);
      }
    }
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load then flip-horizontal command.
   */
  @Test
  public void testLoadThenFlipHor() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "load newExample-fliphor.ppm flipHor " +
            "horizontal-flip newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample2");
    Color[][] image2 = model.getImage("flipHor");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load then flip-Vertical command.
   */
  @Test
  public void testLoadThenFlipVert() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "load newExample-flipVer.ppm flipVer " +
            "vertical-flip newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample2");
    Color[][] image2 = model.getImage("flipVer");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load then green component command.
   */
  @Test
  public void testLoadThenGreen() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "load newExample-green.ppm green " +
            "green-component newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample2");
    Color[][] image2 = model.getImage("green");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load then blue component command.
   */
  @Test
  public void testLoadThenBlue() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "load newExample-blue.ppm blue " +
            "blue-component newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample2");
    Color[][] image2 = model.getImage("blue");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * test Successful load then darken command.
   */
  @Test
  public void testLoadThenDarken() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "darken 13 newExample newExample2");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");

    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int red = image1[i][j].red - 13;
        int green = image1[i][j].green - 13;
        int blue = image1[i][j].blue - 13;
        image1[i][j] = new Color(red, green, blue);
      }
    }
    assertTrue(this.voidCompareImages(image1, image2));
  }


  /**
   * checks that each row and column entry of two images have the same r g b values.
   *
   * @param image1 the first image
   * @param image2 the second image
   * @return return true if the images are the same
   */
  private boolean voidCompareImages(Color[][] image1, Color[][] image2) {
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

  public abstract ImageProcessingModel setModel();

  public abstract ImageProcessingController setController(ImageProcessingModel model,
                                                          TextView view, Readable input);
}
