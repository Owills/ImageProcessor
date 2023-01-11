package controller;

import model.Color;
import model.CommandImageProcessingModel;
import model.CommandModel;
import model.ImageProcessingModel;
import view.BasicTextView;
import view.TextView;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests that a CommandController should pass.
 */
public class CommandControllerTest extends AbstractControllerTest {

  @Test
  public void testDifferentFileTypeLoadSameImage() {
    Reader input = new StringReader("load newExample.ppm ppm " +
            "load res/newExample.png png " +
            "load res/newExample.bmp bmp ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] image1 = model.getImage("ppm");
    Color[][] image2 = model.getImage("png");
    Color[][] image4 = model.getImage("bmp");
    assertTrue(this.voidCompareImages(image1, image2));
    assertTrue(this.voidCompareImages(image1, image4));
    assertTrue(this.voidCompareImages(image2, image4));
  }

  @Test
  public void testDifferentFileTypeLoadSameImage2() {
    Reader input = new StringReader("load res/newExample-blur.ppm ppm " +
            "load res/newExample-blur.png png " +
            "load res/newExample-blur.bmp bmp ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] image1 = model.getImage("ppm");
    Color[][] image2 = model.getImage("png");
    Color[][] image4 = model.getImage("bmp");
    assertTrue(this.voidCompareImages(image1, image2));
    assertTrue(this.voidCompareImages(image1, image4));
    assertTrue(this.voidCompareImages(image2, image4));
  }

  @Test
  public void testDifferentFileTypeLoadSameImage3() {
    Reader input = new StringReader("load res/newExample-sharpen.ppm ppm " +
            "load res/newExample-sharpen.png png " +
            "load res/newExample-sharpen.bmp bmp ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] image1 = model.getImage("ppm");
    Color[][] image2 = model.getImage("png");
    Color[][] image4 = model.getImage("bmp");
    assertTrue(this.voidCompareImages(image1, image2));
    assertTrue(this.voidCompareImages(image1, image4));
    assertTrue(this.voidCompareImages(image2, image4));
  }

  @Test
  public void testDifferentFileTypeLoadSameImage4() {
    Reader input = new StringReader("load res/newExample-greyscale.ppm ppm " +
            "load res/newExample-greyscale.png png " +
            "load res/newExample-greyscale.bmp bmp ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();

    Color[][] image1 = model.getImage("ppm");
    Color[][] image2 = model.getImage("png");
    Color[][] image4 = model.getImage("bmp");
    assertTrue(this.voidCompareImages(image1, image2));
    assertTrue(this.voidCompareImages(image1, image4));
    assertTrue(this.voidCompareImages(image2, image4));
  }


  @Test
  public void testSaveToDifferentType() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.ppm newExample2 " +
            "save newExample-sharpen.jpeg newExample3 " +
            "save newExample-sepia.png newExample4 " +
            "save newExample-greyscale.bmp newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    assertEquals("Successfully loaded newExample.ppm as newExample.\n" +
            "Successfully saved newExample2 as newExample-blur.ppm.\n" +
            "Successfully saved newExample3 as newExample-sharpen.jpeg.\n" +
            "Successfully saved newExample4 as newExample-sepia.png.\n" +
            "Successfully saved newExample5 as newExample-greyscale.bmp.\n", output.toString());
  }

  @Test
  public void testSaveToDifferentType2() {
    Reader input = new StringReader("load res/newExample.png newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.ppm newExample2 " +
            "save newExample-sharpen.jpeg newExample3 " +
            "save newExample-sepia.png newExample4 " +
            "save newExample-greyscale.bmp newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    assertEquals("Successfully loaded res/newExample.png as newExample.\n" +
            "Successfully saved newExample2 as newExample-blur.ppm.\n" +
            "Successfully saved newExample3 as newExample-sharpen.jpeg.\n" +
            "Successfully saved newExample4 as newExample-sepia.png.\n" +
            "Successfully saved newExample5 as newExample-greyscale.bmp.\n", output.toString());
  }

  @Test
  public void testSaveToDifferentType3() {
    Reader input = new StringReader("load res/newExample.jpeg newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.ppm newExample2 " +
            "save newExample-sharpen.jpeg newExample3 " +
            "save newExample-sepia.png newExample4 " +
            "save newExample-greyscale.bmp newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    assertEquals("Successfully loaded res/newExample.jpeg as newExample.\n" +
            "Successfully saved newExample2 as newExample-blur.ppm.\n" +
            "Successfully saved newExample3 as newExample-sharpen.jpeg.\n" +
            "Successfully saved newExample4 as newExample-sepia.png.\n" +
            "Successfully saved newExample5 as newExample-greyscale.bmp.\n", output.toString());
  }

  @Test
  public void testSaveToDifferentType4() {
    Reader input = new StringReader("load res/newExample.bmp newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.ppm newExample2 " +
            "save newExample-sharpen.jpeg newExample3 " +
            "save newExample-sepia.png newExample4 " +
            "save newExample-greyscale.bmp newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    assertEquals("Successfully loaded res/newExample.bmp as newExample.\n" +
            "Successfully saved newExample2 as newExample-blur.ppm.\n" +
            "Successfully saved newExample3 as newExample-sharpen.jpeg.\n" +
            "Successfully saved newExample4 as newExample-sepia.png.\n" +
            "Successfully saved newExample5 as newExample-greyscale.bmp.\n", output.toString());
  }


  @Test
  public void testBmp() {
    Reader input = new StringReader("load res/newExample.bmp newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.bmp newExample2 " +
            "save newExample-sharpen.bmp newExample3 " +
            "save newExample-sepia.bmp newExample4 " +
            "save newExample-greyscale.bmp newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");
    assertFalse(this.voidCompareImages(image1, image2));
  }

  @Test
  public void testJpeg() {
    Reader input = new StringReader("load res/newExample.jpeg newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.jpeg newExample2 " +
            "save newExample-sharpen.jpeg newExample3 " +
            "save newExample-sepia.jpeg newExample4 " +
            "save newExample-greyscale.jpeg newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");
    assertFalse(this.voidCompareImages(image1, image2));
  }

  @Test
  public void testPng() {
    Reader input = new StringReader("load res/newExample.png newExample " +
            "blur newExample newExample2 " +
            "sharpen newExample newExample3 " +
            "sepia newExample newExample4 " +
            "greyscale newExample newExample5 " +
            "save newExample-blur.png newExample2 " +
            "save newExample-sharpen.png newExample3 " +
            "save newExample-sepia.png newExample4 " +
            "save newExample-greyscale.png newExample5 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");
    assertFalse(this.voidCompareImages(image1, image2));
  }


  @Test
  public void testBlur() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "blur newExample newExample2 " +
            "save newExample-blur.ppm newExample2 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");
    assertFalse(this.voidCompareImages(image1, image2));
  }

  /**
   * test throws exception when calling 'blur' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testNotEnoughBlurArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "blur newExample ");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'blur' with invalid arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidBlurArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "blur newExample1 newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  @Test
  public void testSharpen() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "sharpen newExample newExample2 " +
            "save newExample-sharpen.ppm newExample2 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");
    assertFalse(this.voidCompareImages(image1, image2));
  }

  /**
   * test throws exception when calling 'sharpen' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testNotEnoughSharpenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "sharpen newExample ");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'sharpen' with invalid arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidSharpenArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "sharpen newExample1 newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }


  @Test
  public void testSepia() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "sepia newExample newExample2 " +
            "luma-component newExample newExample3 " +
            "save lumaComparison.ppm newExample3 " +
            "save newExample-sepia.ppm newExample2 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image1 = model.getImage("newExample");
    Color[][] image2 = model.getImage("newExample2");
    Color[][] image3 = model.getImage("newExample3");
    assertFalse(this.voidCompareImages(image1, image2));
    assertFalse(this.voidCompareImages(image2, image3));
  }

  /**
   * test throws exception when calling 'sepia' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testNotEnoughSepiaArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "sepia newExample ");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'sepia' with invalid arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidSepiaArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "sepia newExample1 newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }


  /**
   * confirm that greyscale through color transformation is the same as luma-component.
   */
  @Test
  public void testGreyscale() {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "greyscale newExample newExample2 " +
            "luma-component newExample newExample3 " +
            "save newExample-greyscale.ppm newExample2 ");
    CommandImageProcessingModel model = new CommandModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    CommandController controller = new CommandController(model, view, input);
    controller.processImages();
    Color[][] image2 = model.getImage("newExample2");
    Color[][] image3 = model.getImage("newExample3");
    assertTrue(this.voidCompareImages(image2, image3));
  }

  /**
   * test throws exception when calling 'greyscale' without enough arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testNotEnoughGreyscaleArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "greyscale newExample ");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
  }

  /**
   * test throws exception when calling 'greyscale' with invalid arguments.
   *
   * @throws IOException if the file is not found
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidGreyscaleArgument() throws IOException {
    Reader input = new StringReader("load newExample.ppm newExample " +
            "greyscale newExample1 newExample");
    ImageProcessingModel model = this.setModel();
    StringBuilder output = new StringBuilder();
    TextView view = new BasicTextView(model, output);
    ImageProcessingController controller = this.setController(model, view, input);
    controller.processImages();
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

  @Override
  public ImageProcessingModel setModel() {
    return new CommandModel();
  }

  @Override
  public ImageProcessingController setController(ImageProcessingModel model, TextView view,
                                                 Readable input) {
    return new CommandController((CommandImageProcessingModel) model, view, input);
  }
}
