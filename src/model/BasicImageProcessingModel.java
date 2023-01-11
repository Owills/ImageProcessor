package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * represents an ImageProcessingModel with basic utility.
 */
public class BasicImageProcessingModel implements ImageProcessingModel {

  // represents images with their name, and then a 2d array of pixels
  protected final HashMap<String, Color[][]> images;

  // INVARIANT the height and width of Color[][] remains the same

  /**
   * creates a BasicImageProcessingModel this a map of images.
   */
  public BasicImageProcessingModel() {
    this.images = new HashMap<String, Color[][]>();
  }

  @Override
  public void load(File file, String name) throws IllegalArgumentException {
    try {
      load2(new FileInputStream(file), name);
    } catch (IOException e) {
      throw new IllegalArgumentException("file not found");
    } catch (IllegalArgumentException e) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(file));
      } catch (Exception e1) {
        throw new IllegalArgumentException("file not found");
      }
      this.loadHelper(sc, name);
    }
  }

  @Override
  public void load(String filename, String name) throws IllegalArgumentException {
    try {
      this.load2(filename, name);
    } catch (IllegalArgumentException e) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(filename));
      } catch (Exception ex) {
        throw new IllegalArgumentException("File " + filename + " not found!");
      }
      this.loadHelper(sc, name);
    }
  }

  /**
   * Converts scanner input into an image.
   *
   * @param sc   scanner to read input
   * @param name name of image to be saved as
   */
  private void loadHelper(Scanner sc, String name) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      } catch (StringIndexOutOfBoundsException ex) {
        throw new IllegalArgumentException("Invalid file");
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println(token);
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Color[][] image = new Color[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        image[i][j] = new Color(r, g, b);
      }
    }
    images.put(name, image);
  }

  /**
   * loads non jpeg, png or bmp files.
   *
   * @param file the file to be loaded
   * @param name the name to save the image as
   * @throws IOException if the file does not exist or cannot be read
   */
  private void load2(FileInputStream file, String name) throws IllegalArgumentException {
    BufferedImage in;
    try {
      in = ImageIO.read(file);
    } catch (Exception e) {
      throw new IllegalArgumentException("File not found");
    }
    this.load2Helper(in, name);
  }

  /**
   * loads non jpeg, png or bmp files.
   *
   * @param filename the file to be loaded
   * @param name     the name to refer to the image as
   * @throws IOException if the file is not found or supported
   */
  private void load2(String filename, String name) throws IllegalArgumentException {
    BufferedImage in;
    try {
      in = ImageIO.read(new FileInputStream(filename));
    } catch (Exception e) {
      throw new IllegalArgumentException("File not found");
    }
    this.load2Helper(in, name);
  }

  /**
   * Converts BufferedImage input into an image.
   *
   * @param in   the file as a BufferedImage
   * @param name the name of the image to be saved
   */
  private void load2Helper(BufferedImage in, String name) {
    if (in == null) {
      throw new IllegalArgumentException("file type not supported");
    }
    Color[][] image = new Color[in.getHeight()][in.getWidth()];
    for (int i = 0; i < in.getHeight(); i++) {
      for (int j = 0; j < in.getWidth(); j++) {
        java.awt.Color c = new java.awt.Color(in.getRGB(j, i));
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        image[i][j] = new Color(r, g, b);
      }
    }
    images.put(name, image);

  }

  @Override
  public void save(String filename, String name) throws IllegalArgumentException {
    try {
      save2(filename, name);
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid file type");
    } catch (IllegalArgumentException e) {

      File output = new File(filename);
      Color[][] image = this.getImage(name);

      try {
        PrintWriter out = new PrintWriter(new FileOutputStream(filename, true));

        //write header information
        out.println("P3\n" +
                image[0].length + " " + image.length + "\n" +
                "255");

        for (int i = 0; i < image.length; i++) {
          for (int j = 0; j < image[0].length; j++) {
            int r = image[i][j].red;
            int g = image[i][j].green;
            int b = image[i][j].blue;
            out.println(r);
            out.println(g);
            out.println(b);
          }
        }
        out.flush();
        out.close();
      } catch (Exception ex) {
        System.err.println(ex);
      }
    }
  }

  /**
   * saves non jpeg, bmp or png files.
   *
   * @param filename the file to save the image as
   * @param name     the name to the refers to an image
   * @throws IllegalArgumentException if the image does not exist
   * @throws IOException              if the file cannot be saved
   */
  private void save2(String filename, String name) throws IllegalArgumentException, IOException {

    Color[][] image = this.getImage(name);
    BufferedImage out = new BufferedImage(image[0].length, image.length, BufferedImage.TYPE_INT_RGB);
    if (filename.contains(".ppm")) {
      throw new IllegalArgumentException("not valid file type");
    }
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j].red;
        int g = image[i][j].green;
        int b = image[i][j].blue;

        int color = (r << 16) + (g << 8) + b;
        out.setRGB(j, i, color);
      }
    }
    String extension = filename.substring(filename.lastIndexOf(".") + 1);
    out.flush();
    ImageIO.write(out, extension, new File(filename));
  }

  @Override
  public void adjustBrightness(int change, String name, String newName)
          throws IllegalArgumentException {
    Color[][] image = this.getImage(name);
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        image[i][j] = new Color(image[i][j].red + change, image[i][j].green + change,
                image[i][j].blue + change);
      }
    }
    this.images.put(newName, image);
  }


  @Override
  public void flipVertical(String name, String newName) throws IllegalArgumentException {
    this.doesImageExist(name);
    Color[][] image = this.getImage(name);
    Color[][] flippedImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        flippedImage[image.length - i - 1][j] = image[i][j];
      }
    }
    this.images.put(newName, flippedImage);
  }


  @Override
  public void flipHorizontal(String name, String newName) throws IllegalArgumentException {
    this.doesImageExist(name);
    Color[][] image = this.getImage(name);
    Color[][] flippedImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        flippedImage[i][image[0].length - j - 1] = image[i][j];
      }
    }
    this.images.put(newName, flippedImage);
  }

  @Override
  public void greyscale(Colors type, String name, String newName)
          throws IllegalArgumentException {
    Color[][] image = this.getImage(name);
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (type.equals(Colors.RED)) {
          int red = image[i][j].red;
          image[i][j] = new Color(red, red, red);
        }
        if (type.equals(Colors.GREEN)) {
          int green = image[i][j].green;
          image[i][j] = new Color(green, green, green);
        }
        if (type.equals(Colors.BLUE)) {
          int blue = image[i][j].blue;
          image[i][j] = new Color(blue, blue, blue);
        }
      }
    }
    this.images.put(newName, image);
  }

  @Override
  public void greyscale(Brightnesses type, String name, String newName)
          throws IllegalArgumentException {
    this.doesImageExist(name);
    Color[][] image = this.getImage(name);
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (type.equals(Brightnesses.VALUE)) {
          int c = image[i][j].red;
          if (image[i][j].green > c) {
            c = image[i][j].green;
          }
          if (image[i][j].blue > c) {
            c = image[i][j].blue;
          }
          image[i][j] = new Color(c, c, c);
        }
        if (type.equals(Brightnesses.INTENSITY)) {
          int c = (int) (image[i][j].red / 3.0 +
                  image[i][j].green / 3.0 +
                  image[i][j].blue / 3.0);
          image[i][j] = new Color(c, c, c);
        }
        if (type.equals(Brightnesses.LUMA)) {
          int c = (int) (0.2126 * image[i][j].red +
                  0.7152 * image[i][j].green +
                  0.0722 * image[i][j].blue);
          image[i][j] = new Color(c, c, c);
        }
      }
    }
    this.images.put(newName, image);
  }


  @Override
  public Color[][] getImage(String name) throws IllegalArgumentException {
    doesImageExist(name);
    Color[][] original = this.images.get(name);
    Color[][] copy = new Color[original.length][original[0].length];
    for (int i = 0; i < original.length; i++) {
      for (int j = 0; j < original[0].length; j++) {
        copy[i][j] = new Color(original[i][j].red, original[i][j].green, original[i][j].blue);
      }
    }
    return copy;
  }

  /**
   * checks if a given image exists in the map of images.
   *
   * @param name the image that may or may not exist
   * @throws IllegalArgumentException if the image does not exist
   */
  protected void doesImageExist(String name) throws IllegalArgumentException {
    if (!this.images.containsKey(name)) {
      throw new IllegalArgumentException("Give Image '" + name + "' does not exist");
    }
  }
}
