package model;

import org.junit.Test;

import model.Commands.Blur;
import model.Commands.Command;
import model.Commands.Greyscale;
import model.Commands.SepiaTone;
import model.Commands.Sharpen;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests for the command model.
 */
public class CommandModelTests extends AbstractModelTests {


  @Test
  public void testSuccessfulBlur() {
    CommandModel model = new CommandModel();
    Command blur = new Blur();
    model.load("example.ppm", "example1");
    model.accept("example1", "example2", blur);

    Color[][] image1 = model.getImage("example1");
    Color[][] image2 = model.getImage("example2");
    assertFalse(super.voidCompareImages(image1, image2));


    Color[][] image3 = blur.execute(image1);
    assertTrue(super.voidCompareImages(image3, image2));

    double[][] kernel = new double[][]
        {{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}};

    image1 = model.getImage("example1");
    image2 = model.getImage("example2");
    Color[][] image4 = new Color[image1.length][image1[0].length];
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int filterNumRed = 0;
        int filterNumGreen = 0;
        int filterNumBlue = 0;

        for (int num = 0; num < kernel.length; num++) {
          for (int nim = 0; nim < kernel[0].length; nim++) {
            int r = i + (num - ((kernel.length - 1) / 2));
            int c = j + (nim - ((kernel.length - 1) / 2));
            if (r >= 0 && c >= 0 && r < image1.length && c < image1[0].length) {
              filterNumRed += image1[r][c].red * kernel[num][nim];
              filterNumGreen += image1[r][c].green * kernel[num][nim];
              filterNumBlue += image1[r][c].blue * kernel[num][nim];
            }
          }
        }

        image4[i][j] = new Color(filterNumRed, filterNumGreen, filterNumBlue);
      }
    }
    assertTrue(super.voidCompareImages(image4, image2));
  }

  @Test
  public void testSuccessfulSharpen() {
    CommandModel model = new CommandModel();
    Command sharpen = new Sharpen();
    model.load("example.ppm", "example1");
    model.accept("example1", "example2", sharpen);

    Color[][] image1 = model.getImage("example1");
    Color[][] image2 = model.getImage("example2");
    assertFalse(super.voidCompareImages(image1, image2));


    Color[][] image3 = sharpen.execute(image1);
    assertTrue(super.voidCompareImages(image3, image2));

    double[][] kernel = new double[][]
        {{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
                {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
                {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
                {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
                {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}};

    image1 = model.getImage("example1");
    image2 = model.getImage("example2");
    Color[][] image4 = new Color[image1.length][image1[0].length];
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int filterNumRed = 0;
        int filterNumGreen = 0;
        int filterNumBlue = 0;

        for (int num = 0; num < kernel.length; num++) {
          for (int nim = 0; nim < kernel[0].length; nim++) {
            int r = i + (num - ((kernel.length - 1) / 2));
            int c = j + (nim - ((kernel.length - 1) / 2));
            if (r >= 0 && c >= 0 && r < image1.length && c < image1[0].length) {
              filterNumRed += image1[r][c].red * kernel[num][nim];
              filterNumGreen += image1[r][c].green * kernel[num][nim];
              filterNumBlue += image1[r][c].blue * kernel[num][nim];
            }
          }
        }

        image4[i][j] = new Color(filterNumRed, filterNumGreen, filterNumBlue);
      }
    }
    assertTrue(super.voidCompareImages(image4, image2));

  }

  @Test
  public void testSuccessfulSepia() {
    CommandModel model = new CommandModel();
    Command sepia = new SepiaTone();
    model.load("example.ppm", "example1");
    model.accept("example1", "example2", sepia);

    Color[][] image1 = model.getImage("example1");
    Color[][] image2 = model.getImage("example2");
    assertFalse(super.voidCompareImages(image1, image2));


    Color[][] image3 = sepia.execute(image1);
    assertTrue(super.voidCompareImages(image3, image2));

    double[][] matrix = new double[][]
        {{0.393, 0.789, 0.189},
                {0.349, 0.686, 0.168},
                {0.272, 0.534, 0.131}};

    image1 = model.getImage("example1");
    image2 = model.getImage("example2");
    Color[][] image4 = new Color[image1.length][image1[0].length];
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int r = image1[i][j].red;
        int g = image1[i][j].green;
        int b = image1[i][j].blue;
        image4[i][j] = new Color((int) (r * matrix[0][0] + g * matrix[0][1] + b * matrix[0][2]),
                (int) (r * matrix[1][0] + g * matrix[1][1] + b * matrix[1][2]),
                (int) (r * matrix[2][0] + g * matrix[2][1] + b * matrix[2][2]));
      }
    }
    assertTrue(super.voidCompareImages(image4, image2));

  }

  @Test
  public void testSuccessfulGreyscale() {
    CommandModel model = new CommandModel();
    Command greyscale = new Greyscale();
    model.load("example.ppm", "example1");
    model.accept("example1", "example2", greyscale);

    Color[][] image1 = model.getImage("example1");
    Color[][] image2 = model.getImage("example2");
    assertFalse(super.voidCompareImages(image1, image2));


    Color[][] image3 = greyscale.execute(image1);
    assertTrue(super.voidCompareImages(image3, image2));

    double[][] matrix = new double[][]
        {{0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722}};

    image1 = model.getImage("example1");
    image2 = model.getImage("example2");
    Color[][] image4 = new Color[image1.length][image1[0].length];
    for (int i = 0; i < image1.length; i++) {
      for (int j = 0; j < image1[0].length; j++) {
        int r = image1[i][j].red;
        int g = image1[i][j].green;
        int b = image1[i][j].blue;
        image4[i][j] = new Color((int) (r * matrix[0][0] + g * matrix[0][1] + b * matrix[0][2]),
                (int) (r * matrix[1][0] + g * matrix[1][1] + b * matrix[1][2]),
                (int) (r * matrix[2][0] + g * matrix[2][1] + b * matrix[2][2]));
      }
    }
    assertTrue(super.voidCompareImages(image4, image2));

    model.greyscale(Brightnesses.LUMA, "example1", "example5");
    Color[][] image5 = model.getImage("example5");
    assertTrue(super.voidCompareImages(image5, image2));
  }

  @Override
  public ImageProcessingModel setModel() {
    return new CommandModel();
  }
}
