package controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import model.Brightnesses;
import model.Colors;
import model.ImageProcessingModel;
import view.TextView;

/**
 * represents a controller for image processing with the following functionality
 * load 'filename' 'name'
 * save 'filename' 'name'
 * darken 'amount' 'name' 'new name'
 * brighten 'amount' 'name' 'new name'
 * horizontal-flip 'name' 'new name'
 * vertical-flip 'name' 'new name'
 * value-component 'name' 'new name'
 * intensity-component 'name' 'new name'
 * luma-component 'name' 'new name'
 * red-component 'name' 'new name'
 * green-component 'name' 'new name'
 * blue-component 'name' 'new name'
 * quit.
 */
public abstract class AbstractController implements ImageProcessingController {

  private final TextView view;
  private final Readable input;

  /**
   * given a TextView and Readable creates a controller.
   *
   * @param view  the view to send error messages
   * @param input the script of commands
   * @throws IllegalArgumentException if the given arguments are null
   */
  public AbstractController(TextView view, Readable input)
          throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("given view must not be null");
    }
    if (input == null) {
      throw new IllegalArgumentException("given input as Readable must not be null");
    }
    this.view = view;
    this.input = input;
  }

  @Override
  public void processImages() throws IllegalStateException {
    Scanner sc = new Scanner(input);
    boolean quit = false;
    while (!quit && sc.hasNext()) { //continue until the user quits
      String userInstruction = sc.next().toLowerCase(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      } else {
        processCommand(userInstruction, sc);
      }
    }
  }

  public abstract ImageProcessingModel getModel();


  protected void processCommand(String userInstruction, Scanner in) {
    ImageProcessingModel model = this.getModel();
    try {
      switch (userInstruction.toLowerCase()) {
        case "load":
          String filename = this.getNextInput(in);
          String name = this.getNextInput(in);
          model.load(new File(filename), name);
          this.renderMessageToView("Successfully loaded " + filename + " as " + name + ".\n");
          break;
        case "save":

          filename = this.getNextInput(in);
          name = this.getNextInput(in);
          model.save(filename, name);

          this.renderMessageToView("Successfully saved " + name + " as " + filename + ".\n");
          break;
        case "brighten":
          if (!in.hasNextInt()) {
            this.renderMessageToView("Invalid Arguments\n");
            throw new IllegalStateException("Invalid Arguments");
          } else {
            int change = in.nextInt();
            if (change < 0) {
              this.renderMessageToView("Invalid Arguments\n");
              throw new IllegalStateException("Invalid Arguments");
            }
            name = this.getNextInput(in);
            String newName = this.getNextInput(in);
            model.adjustBrightness(change, name, newName);
          }
          break;
        case "darken":
          if (!in.hasNextInt()) {
            this.renderMessageToView("Invalid Arguments\n");
            throw new IllegalStateException("Invalid Arguments");
          } else {
            int change = in.nextInt();
            if (change < 0) {
              this.renderMessageToView("Invalid Arguments\n");
              throw new IllegalStateException("Invalid Arguments");
            }
            name = this.getNextInput(in);
            String newName = this.getNextInput(in);
            model.adjustBrightness(-change, name, newName);
          }
          break;
        case "horizontal-flip":
          name = this.getNextInput(in);
          String newName = this.getNextInput(in);
          model.flipHorizontal(name, newName);
          break;
        case "vertical-flip":
          name = this.getNextInput(in);
          newName = this.getNextInput(in);
          model.flipVertical(name, newName);
          break;
        case "value-component":
          model.greyscale(Brightnesses.VALUE, this.getNextInput(in), this.getNextInput(in));
          break;
        case "intensity-component":
          model.greyscale(Brightnesses.INTENSITY, this.getNextInput(in), this.getNextInput(in));
          break;
        case "luma-component":
          model.greyscale(Brightnesses.LUMA, this.getNextInput(in), this.getNextInput(in));
          break;
        case "red-component":
          model.greyscale(Colors.RED, this.getNextInput(in), this.getNextInput(in));
          break;
        case "green-component":
          model.greyscale(Colors.GREEN, this.getNextInput(in), this.getNextInput(in));
          break;
        case "blue-component":
          model.greyscale(Colors.BLUE, this.getNextInput(in), this.getNextInput(in));
          break;
        case "quit":
          return;
        default:
          throw new IllegalStateException("unknown command\n");
      }
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage());
    }

  }

  /**
   * renders a message to view.
   *
   * @param s the message to be rendered
   * @throws IOException if the message cannot be render
   */
  protected void renderMessageToView(String s) throws IOException {
    this.view.renderMessage(s);
  }


  /**
   * checks that next input in the given exists and returns it as a string.
   *
   * @param in the scanner.
   * @return the next input as a string
   * @throws IllegalStateException if the next input in the scanner does not exist
   */
  protected String getNextInput(Scanner in) throws
          IllegalStateException {
    String s = "";
    if (in.hasNext()) {
      s = in.next();
    } else {
      try {
        this.renderMessageToView("Not enough Arguments");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      throw new IllegalStateException("Not enough Arguments");
    }
    return s;
  }
}
