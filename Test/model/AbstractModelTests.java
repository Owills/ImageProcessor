package model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests that all ImageProcessingModels should pass.
 */
public abstract class AbstractModelTests {

  public abstract ImageProcessingModel setModel();

  /**
   * test exception for ppm file with no header.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadFileWithNoHeader() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("missingHeader.ppm", "Invalid File");
  }

  /**
   * test exception for loading ppm file that does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadFileNotFound() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("code.ppm", "invalidFile");
  }

  /**
   * test exception in save method when image is not found.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSaveFileNotFound() {
    ImageProcessingModel bipm = this.setModel();
    bipm.save("code.ppm", "invalidFile");
  }


  /**
   * test exception for brighten method when image does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBrightenFileNotFound() {
    ImageProcessingModel bipm = this.setModel();
    bipm.adjustBrightness(100, "invalidImage", "newInvalidImage");
  }

  /**
   * checks correct parameters.
   */
  @Test
  public void testCorrectPara() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image = bipm.getImage("newExample");
    assertEquals(4, image.length);
    assertEquals(4, image[0].length);
  }

  /**
   * test load image.
   */
  @Test
  public void testLoadImageDifferentFileSameContent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    bipm.load("example.ppm", "example");
    Color[][] expectedImage = {
            {new Color(0, 0, 0)},
            {new Color(100, 0, 0)},
            {new Color(0, 0, 0)},
            {new Color(255, 0, 255)},
            {new Color(0, 0, 0)},
            {new Color(0, 255, 175)},
            {new Color(0, 0, 0)},
            {new Color(0, 0, 0)},
            {new Color(0, 0, 0)},
            {new Color(0, 0, 0)},
            {new Color(0, 15, 175)},
            {new Color(0, 0, 0)},
            {new Color(255, 0, 255)},
            {new Color(0, 0, 0)},
            {new Color(0, 0, 0)},
            {new Color(255, 255, 255)}

    };
    //TOD0: Ensure the 2 files are not null
    Color[][] image1 = bipm.getImage("newExample");
    Color[][] image2 = bipm.getImage("example");
    assertTrue(this.voidCompareImages(image1, image2));


  }

  /**
   * tests loading the same image to two different names correctly loads the same image.
   */
  @Test
  public void testLoadImageSameFile() {
    ImageProcessingModel bipm = this.setModel();

    bipm.load("newExample.ppm", "newExample1");
    bipm.load("newExample.ppm", "newExample2");
    Color[][] image3 = bipm.getImage("newExample1");
    Color[][] image4 = bipm.getImage("newExample2");
    assertTrue(this.voidCompareImages(image3, image4));
  }

  @Test
  public void testLoadSameExtend() throws IOException {
    ImageProcessingModel bipm = this.setModel();
    Color[][] run1;

    bipm.load("res/newExample.png", "ex2");
    bipm.load("res/newExample.png", "ex3");
    Color[][] image3 = bipm.getImage("ex2");
    Color[][] image4 = bipm.getImage("ex3");
    assertTrue(this.voidCompareImages(image3, image4));
  }

  @Test
  public void testLoadSaveExtend() throws IOException {
    ImageProcessingModel bipm = this.setModel();
    Color[][] run1;

    bipm.load("res/newExample.png", "ex2");
    Color[][] image3 = bipm.getImage("ex2");
    bipm.save("testSave.png", "ex2");
    File testFile = new File("testSave.png");
    assertTrue(testFile.exists());
    assertTrue(testFile.isFile());
  }

  @Test
  public void testLoadSaveFlipExtend() throws IOException {
    ImageProcessingModel bipm = this.setModel();

    bipm.load("res/newExample.png", "ex2");
    Color[][] image3 = bipm.getImage("ex2");
    bipm.flipVertical("ex2", "ex3");
    Color[][] image3Flip = bipm.getImage("ex3");
    assertFalse(this.voidCompareImages(image3, image3Flip));
    bipm.save("testSave.png", "ex3");
    File testFile = new File("testSave.png");
    assertTrue(testFile.exists());
    assertTrue(testFile.isFile());
    bipm.load("newExample-flipverpng.png", "testex3");
    Color[][] image4 = bipm.getImage("testex3");
    assertTrue(this.voidCompareImages(image3Flip, image4));

  }

  @Test
  public void testLoadPNGFileSavePPMFile() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("res/newExample.png", "ex1");
    Color[][] image1 = bipm.getImage("ex1");
    bipm.save("newExampleSave.ppm", "ex1");
    File testFile = new File("newExampleSave.ppm");
    assertTrue(testFile.exists());
    assertTrue(testFile.isFile());
    bipm.load("newExampleSave.ppm", "savex1");
    bipm.save("newExampleSave.png", "savex1");
    bipm.load("newExampleSave.png", "save2");
    Color[][] image2 = bipm.getImage("save2");
    assertTrue(this.voidCompareImages(image1, image2));
  }


  /**
   * tests loading two different images.
   */
  @Test
  public void testLoadImageDifferentFileDifferentContent() {
    ImageProcessingModel bipm = this.setModel();

    bipm.load("newExample.ppm", "newExample1");
    bipm.load("newExample-Blue.ppm", "koalafile");
    Color[][] image1 = bipm.getImage("newExample1");
    Color[][] image2 = bipm.getImage("koalafile");
    assertFalse(this.voidCompareImages(image1, image2));
  }

  /**
   * test save method.
   */
  @Test
  public void testSaveImageExistInDir() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    bipm.save("saveExample.ppm", "newExample");
    File testFile = new File("saveExample.ppm");
    assertTrue(testFile.exists());
    assertTrue(testFile.isFile());

  }

  /**
   * tests save method when image does not exist in directory.
   */
  @Test
  public void testSaveImageDoesNotExistInDir() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    bipm.save("saveExample.ppm", "newExample");
    File testFile = new File("unKnown.ppm");
    assertFalse(testFile.exists());
  }

  /**
   * tests save method successfully saves the image.
   */
  @Test
  public void testSavedImageExistInDir() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    bipm.save("newerExample.ppm", "newExample");
    File testFile = new File("newerExample.ppm");
    assertTrue(testFile.exists());
  }

  /**
   * tests save method.
   */
  @Test
  public void testSaveImageSuccess() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    bipm.save("newerExample.ppm", "newExample");

    bipm.load("newerExample.ppm", "sameExample");
    Color[][] newExample = bipm.getImage("newExample");
    Color[][] sameExample = bipm.getImage("sameExample");
    assertTrue(this.voidCompareImages(newExample, sameExample));
  }

  /**
   * test load, saving and loading and image gives the original image.
   */
  @Test
  public void testSaveImageOverrideSuccess() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("testSave1.ppm", "newTestSave1");
    bipm.load("testSave2.ppm", "newTestSave2");
    Color[][] testSave1 = bipm.getImage("newTestSave1");
    Color[][] testSave2 = bipm.getImage("newTestSave2");
    assertFalse(this.voidCompareImages(testSave1, testSave2));
    bipm.save("testSave2.ppm", "newTestSave2");
    bipm.load("testSave2.ppm", "newTestSave3");
    Color[][] testSave3 = bipm.getImage("newTestSave2");
    Color[][] testSave4 = bipm.getImage("newTestSave3");
    assertTrue(this.voidCompareImages(testSave3, testSave4));
  }

  @Test
  public void testSaveOverride() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    bipm.save("newExample.ppm", "newExample");

    bipm.load("newExample.ppm", "sameExample");
    Color[][] newExample = bipm.getImage("newExample");
    Color[][] sameExample = bipm.getImage("sameExample");
    assertTrue(this.voidCompareImages(newExample, sameExample));
  }

  /**
   * test GetImage method.
   */
  @Test
  public void voidTestGetImage() {
    // test get image returns a copy of the image
    ImageProcessingModel bipm = this.setModel();
    bipm.load("koala.ppm", "Koala");
    Color[][] image = bipm.getImage("Koala");
    assertEquals(101, image[0][0].red);
    assertEquals(90, image[0][0].green);
    assertEquals(58, image[0][0].blue);

    image[0][0] = new Color(0, 0, 0);
    Color[][] image2 = bipm.getImage("Koala");
    assertEquals(101, image2[0][0].red);
    assertEquals(90, image2[0][0].green);
    assertEquals(58, image2[0][0].blue);
    //assertFalse(this.voidCompareImages(image,image2));

    image[0][0] = new Color(101, 90, 58);
    //assertTrue(this.voidCompareImages(image,image2));
  }

  /**
   * test adjustbrightness method.
   */
  @Test
  public void voidTestAdjustBrightnessManual() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("example.ppm", "Koala");
    Color[][] image1 = bipm.getImage("Koala");
    bipm.adjustBrightness(50, "Koala", "Koala+50Brightness");
    Color[][] image2 = bipm.getImage("Koala+50Brightness");
    assertFalse(this.voidCompareImages(image1, image2));

    // these should not be the same despite a total brightness change of zero (because of capping
    // at zero and 255)
    bipm.adjustBrightness(-50, "Koala+50Brightness", "Orig-Koala");
    Color[][] image3 = bipm.getImage("Orig-Koala");
    assertFalse(this.voidCompareImages(image1, image3));

    // manually adjust image 1 by +50 capping at 255
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int red = image1[i][j].red + 50;
        int green = image1[i][j].green + 50;
        int blue = image1[i][j].blue + 50;
        image1[i][j] = new Color(red, green, blue);
      }


    }
    assertTrue(this.voidCompareImages(image1, image2));

  }

  /**
   * tests for brighten.
   */
  @Test
  public void voidTestAdjustBrightnessCompareWithGeneratedFile() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.adjustBrightness(30, "newExample", "newExample+30Brightness");
    Color[][] image2 = bipm.getImage("newExample+30Brightness");
    assertFalse(this.voidCompareImages(image1, image2));

    bipm.load("newExampleBrighter30.ppm", "newExample+30BrightnessGimp");
    Color[][] image3 = bipm.getImage("newExample+30BrightnessGimp");
    assertTrue(this.voidCompareImages(image2, image3));
  }

  /**
   * tests that flipping horizontally twice produces the original image.
   */
  @Test
  public void testFlipHorizontalTwice() {
    // flipping horizontally twice should produce the original image
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.flipHorizontal("newExample", "newExample-horizontal");

    bipm.flipHorizontal("newExample-horizontal", "orig-example");
    Color[][] image2 = bipm.getImage("orig-example");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * tests that flip horizontal method.
   */
  @Test
  public void testFlipHorizontal() {
    // flipping horizontally twice should produce the original image
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.flipHorizontal("newExample", "newExample-horizontal");
    Color[][] flippedImage = bipm.getImage("newExample-horizontal");
    assertFalse(this.voidCompareImages(image1, flippedImage));


    bipm.load("newExample-fliphor.ppm", "horizontalflippedExample");
    Color[][] image2 = bipm.getImage("horizontalflippedExample");
    assertTrue(this.voidCompareImages(flippedImage, image2));
  }

  /**
   * tests that flipping vertically twice gets the original image.
   */
  @Test
  public void testFlipVerticalTwice() {
    // flipping vertical twice should produce the original image
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.flipVertical("newExample", "newExample-vertical");

    bipm.flipVertical("newExample-vertical", "orig-example");
    Color[][] image2 = bipm.getImage("orig-example");
    assertTrue(this.voidCompareImages(image1, image2));
  }

  /**
   * tests flip vertical method.
   */
  @Test
  public void testFlipVertical() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.flipVertical("newExample", "newExample-vertical");
    Color[][] flippedImage = bipm.getImage("newExample-vertical");
    assertFalse(this.voidCompareImages(image1, flippedImage));
    //bipm.save("FlippedHorImage.ppm","newExample-vertical");

    bipm.load("newExample-flipver.ppm", "verticalflippedExample");
    Color[][] image2 = bipm.getImage("verticalflippedExample");
    assertTrue(this.voidCompareImages(flippedImage, image2));
  }

  /**
   * test red component.
   */
  @Test
  public void testRedComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");

    bipm.greyscale(Colors.RED, "newExample", "newExample-Red");
    Color[][] imageRed = bipm.getImage("newExample-Red");

    assertFalse(this.voidCompareImages(image1, imageRed));

    bipm.load("newExample-Red.ppm", "newExampleRedGimp");
    Color[][] imageRed2 = bipm.getImage("newExampleRedGimp");
    assertTrue(this.voidCompareImages(imageRed, imageRed2));
  }

  /**
   * test blue component.
   */
  @Test
  public void testBlueComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");

    bipm.greyscale(Colors.BLUE, "newExample", "newExample-Blue");
    Color[][] imageBlue = bipm.getImage("newExample-Blue");

    assertFalse(this.voidCompareImages(image1, imageBlue));

    bipm.load("newExample-Blue.ppm", "newExampleBlueGimp");
    Color[][] imageBlue2 = bipm.getImage("newExampleBlueGimp");
    assertTrue(this.voidCompareImages(imageBlue, imageBlue2));
  }

  /**
   * test green component.
   */
  @Test
  public void testGreenComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");

    bipm.greyscale(Colors.GREEN, "newExample", "newExample-Green");
    Color[][] imageGreen = bipm.getImage("newExample-Green");

    assertFalse(this.voidCompareImages(image1, imageGreen));

    bipm.load("newExample-Green.ppm", "newExampleGreenGimp");
    Color[][] imageGreen2 = bipm.getImage("newExampleGreenGimp");
    assertTrue(this.voidCompareImages(imageGreen, imageGreen2));
  }

  /**
   * test value component.
   */
  @Test
  public void testValueComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");

    bipm.greyscale(Brightnesses.VALUE, "newExample", "newExample-Value");
    Color[][] imageGreen = bipm.getImage("newExample-Value");

    assertFalse(this.voidCompareImages(image1, imageGreen));

    bipm.load("newExample-value.ppm", "newExampleValueGimp");
    Color[][] imageGreen2 = bipm.getImage("newExampleValueGimp");
    assertTrue(this.voidCompareImages(imageGreen, imageGreen2));
  }


  /**
   * test luma component.
   */
  @Test
  public void testLumaComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");

    bipm.greyscale(Brightnesses.LUMA, "newExample", "newExample-Luma");
    Color[][] imageLuma = bipm.getImage("newExample-Luma");

    assertFalse(this.voidCompareImages(image1, imageLuma));

    bipm.load("newExample-luma1.ppm", "newExample-luma1");
    Color[][] imageluma2 = bipm.getImage("newExample-luma1");
    assertTrue(this.voidCompareImages(imageLuma, imageluma2));
  }

  /**
   * test intensity component.
   */
  @Test
  public void testIntensityComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");

    bipm.greyscale(Brightnesses.INTENSITY, "newExample", "newExample-Int");
    Color[][] image = bipm.getImage("newExample-Int");

    assertFalse(this.voidCompareImages(image1, image));

    bipm.load("newExample-int1.ppm", "newExample-int1");
    Color[][] imageint2 = bipm.getImage("newExample-int1");
    assertTrue(this.voidCompareImages(image, imageint2));
  }

  @Test
  public void testRedThenBlueComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.greyscale(Colors.RED, "newExample", "newExampleRed");
    bipm.greyscale(Colors.BLUE, "newExampleRed", "newExampleRedBlue");
    Color[][] imageRed = bipm.getImage("newExampleRed");
    Color[][] imageBlue = bipm.getImage("newExampleRedBlue");
    assertTrue(this.voidCompareImages(imageBlue, imageRed));
  }

  @Test
  public void testRedThenBlueThenGreenComponent() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.greyscale(Colors.RED, "newExample", "newExampleRed");
    bipm.greyscale(Colors.BLUE, "newExampleRed", "newExampleRedBlue");
    bipm.greyscale(Colors.GREEN, "newExampleRedBlue", "newExampleRedBlueGreen");

    Color[][] imageRed = bipm.getImage("newExampleRed");
    Color[][] imageGreen = bipm.getImage("newExampleRedBlueGreen");
    assertTrue(this.voidCompareImages(imageGreen, imageRed));
  }

  @Test
  public void testRedThenValue() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.greyscale(Colors.RED, "newExample", "newExampleRed");
    Color[][] imageRed = bipm.getImage("newExampleRed");
    bipm.greyscale(Brightnesses.VALUE, "newExampleRed", "newExampleValue");
    Color[][] imageValue = bipm.getImage("newExampleValue");
    assertTrue(this.voidCompareImages(imageRed, imageValue));
  }

  @Test
  public void testValueThenRed() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.greyscale(Brightnesses.VALUE, "newExample", "newExampleRed");
    Color[][] imageRed = bipm.getImage("newExampleRed");
    bipm.greyscale(Colors.RED, "newExampleRed", "newExampleValue");
    Color[][] imageValue = bipm.getImage("newExampleValue");
    assertTrue(this.voidCompareImages(imageRed, imageValue));
  }

  @Test
  public void testLumaThenBlue() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.greyscale(Brightnesses.LUMA, "newExample", "newExampleLuma");
    Color[][] imageRed = bipm.getImage("newExampleLuma");
    bipm.greyscale(Colors.BLUE, "newExampleLuma", "newExampleValue");
    Color[][] imageValue = bipm.getImage("newExampleValue");
    assertTrue(this.voidCompareImages(imageRed, imageValue));
  }

  @Test
  public void testGreenThenIntensity() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.greyscale(Brightnesses.INTENSITY, "newExample", "newExampleLuma");
    Color[][] imageRed = bipm.getImage("newExampleLuma");
    bipm.greyscale(Colors.GREEN, "newExampleLuma", "newExampleValue");
    Color[][] imageValue = bipm.getImage("newExampleValue");
    assertTrue(this.voidCompareImages(imageRed, imageValue));
  }

  /**
   * test darken.
   */
  @Test
  public void TestDarkenCompareWithGeneratedFile() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.adjustBrightness(-50, "newExample", "newExample-50Brightness");
    Color[][] image2 = bipm.getImage("newExample-50Brightness");
    assertFalse(this.voidCompareImages(image1, image2));

    bipm.load("newExampleBrighter-50.ppm", "newExample-50BrightnessGimp");
    Color[][] image3 = bipm.getImage("newExample-50BrightnessGimp");
    assertTrue(this.voidCompareImages(image2, image3));
  }

  @Test
  public void testDarkenThenBlueThenVerticalFlip() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "newExample");
    Color[][] image1 = bipm.getImage("newExample");
    bipm.adjustBrightness(-50, "newExample", "newExampleDarken");
    Color[][] image2 = bipm.getImage("newExampleDarken");
    assertFalse(this.voidCompareImages(image1, image2));
    bipm.greyscale(Colors.BLUE, "newExampleDarken", "newExampleDarkenBlue");
    Color[][] image3 = bipm.getImage("newExampleDarkenBlue");
    assertFalse(this.voidCompareImages(image1, image3));
    assertFalse(this.voidCompareImages(image3, image2));
    bipm.flipVertical("newExampleDarkenBlue", "newExampleDarkenBlueFlipVer");
    Color[][] image4 = bipm.getImage("newExampleDarkenBlueFlipVer");
    assertFalse(this.voidCompareImages(image3, image4));

    //load the generated image from GIMP
    bipm.load("newExampleDarkenBlueFlipVer.ppm", "GimpFile");
    Color[][] image5 = bipm.getImage("GimpFile");
    assertTrue(this.voidCompareImages(image4, image5));
  }


  /**
   * tests greyscale with color components.
   */
  @Test
  public void testGreyScaleColor() {
    ImageProcessingModel bipm = this.setModel();
    bipm.load("newExample.ppm", "Koala");
    Color[][] image1 = bipm.getImage("Koala");

    bipm.greyscale(Colors.BLUE, "Koala", "Koala-just-blue");
    Color[][] image2 = bipm.getImage("Koala-just-blue");
    assertFalse(this.voidCompareImages(image1, image2));
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int blue = image1[i][j].blue;
        image1[i][j] = new Color(blue, blue, blue);
      }
    }
    assertTrue(this.voidCompareImages(image1, image2));

    image1 = bipm.getImage("Koala");
    bipm.greyscale(Colors.RED, "Koala", "Koala-just-red");
    Color[][] image3 = bipm.getImage("Koala-just-red");
    assertFalse(this.voidCompareImages(image1, image3));
    assertFalse(this.voidCompareImages(image1, image2));
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int red = image1[i][j].red;
        image1[i][j] = new Color(red, red, red);
      }
    }
    assertTrue(this.voidCompareImages(image1, image3));

    image1 = bipm.getImage("Koala");
    bipm.greyscale(Colors.GREEN, "Koala", "Koala-just-green");
    Color[][] image4 = bipm.getImage("Koala-just-green");
    assertFalse(this.voidCompareImages(image1, image4));
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int green = image1[i][j].green;
        image1[i][j] = new Color(green, green, green);
      }
    }
    assertTrue(this.voidCompareImages(image1, image4));
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
