package controller;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;


import model.Commands.AdjustBrightness;
import model.Commands.BlueComponent;
import model.Commands.Blur;
import model.Commands.Command;
import model.CommandImageProcessingModel;
import model.Commands.DownScale;
import model.Commands.FlipHorizontal;
import model.Commands.FlipVertical;
import model.Commands.GreenComponent;
import model.Commands.Greyscale;
import model.Commands.IntensityComponent;
import model.Commands.RedComponent;
import model.Commands.ValueComponent;
import model.ImageProcessingModel;
import model.Commands.SepiaTone;
import model.Commands.Sharpen;
import view.TextView;

/**
 * supports additional commands all new commands read from input should take in string name,
 * * string newName as there first two arguments.
 */
public class CommandController extends AbstractController {

  protected final CommandImageProcessingModel model;
  private final HashMap<String, Function<Scanner, Command>> commands;


  /**
   * given a ImageProcessingModel, TextView, and Readable creates a BasicController.
   *
   * @param model the image processing model
   * @param view  the view to send error messages
   * @param input the script of commands
   * @throws IllegalArgumentException if the given arguments are null
   */
  public CommandController(CommandImageProcessingModel model, TextView view, Readable input)
          throws IllegalArgumentException {
    super(view, input);
    if (model == null) {
      throw new IllegalArgumentException("given model must not be null");
    }
    this.model = model;

    commands = new HashMap<>();
    this.addKnownCommands();
  }

  /**
   * adds commands to our list of commands.
   */
  protected void addKnownCommands() {
    commands.put("blur", s -> new Blur());
    commands.put("red-component", s -> new RedComponent());
    commands.put("green-component", s -> new GreenComponent());
    commands.put("blue-component", s -> new BlueComponent());
    commands.put("luma-component", s -> new Greyscale());
    commands.put("intensity-component", s -> new IntensityComponent());
    commands.put("value-component", s -> new ValueComponent());
    commands.put("darken", s -> new AdjustBrightness(-Integer.parseInt(s.next())));
    commands.put("brighten", s -> new AdjustBrightness(Integer.parseInt(s.next())));
    commands.put("sharpen", s -> new Sharpen());
    commands.put("sepia", s -> new SepiaTone());
    commands.put("greyscale", s -> new Greyscale());
    commands.put("downscale", s -> new DownScale(Integer.parseInt(s.next()),
            Integer.parseInt(s.next())));
    commands.put("horizontal-flip", s -> new FlipHorizontal());
    commands.put("vertical-flip", s -> new FlipVertical());
  }


  protected void processCommand(String userInstruction, Scanner in) throws IllegalStateException {
    boolean partial = false;
    if (userInstruction.equalsIgnoreCase("partial")) {
      partial = true;
      userInstruction = in.next();
    }
    Command c;
    Function<Scanner, Command> cmd =
            commands.getOrDefault(userInstruction, null);

    if (cmd == null) {
      super.processCommand(userInstruction, in);
    } else {
      try {
        if (partial) {
          String mask = this.getNextInput(in);
          c = cmd.apply(in);
          c.setMask(model.getImage(mask));
        } else {
          c = cmd.apply(in);
        }
        String name = this.getNextInput(in);
        String newName = this.getNextInput(in);
        model.accept(name, newName, c);
      } catch (Exception e) {
        throw new IllegalStateException(e.getMessage());
      }
    }
  }

  @Override
  public ImageProcessingModel getModel() {
    return this.model;
  }
}
