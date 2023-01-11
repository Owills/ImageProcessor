package controller;

import java.io.File;
import java.util.HashMap;
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
import model.Commands.SepiaTone;
import model.Commands.Sharpen;
import model.Commands.ValueComponent;
import view.GuiView;

/**
 * represent the controller for gui image editor.
 */
public class GuiController implements ControllerFeatures  {

  private final GuiView view;
  private final CommandImageProcessingModel model;
  private final HashMap<String, Function<String[], Command>> commands;

  /**
   * give a GuiView and CommandImageProcessingModel creates a GuiController.
   * @param view the view to be used
   * @param model the model to be used
   * @throws IllegalArgumentException if the given inputs are used
   */
  public GuiController(GuiView view, CommandImageProcessingModel model) throws
          IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("given MarbleSolitaireModel must not be null");
    }
    if (view == null) {
      throw new IllegalArgumentException("given MarbleSolitaireGuiView must not be null");
    }

    this.model = model;
    this.view = view;
    this.view.accept(this);
    this.view.refresh();

    commands = new HashMap<>();
    this.addKnownCommands();
  }

  /**
   * adds commands to our list of commands.
   */
  private void addKnownCommands() {
    commands.put("red", s -> new RedComponent());
    commands.put("green", s -> new GreenComponent());
    commands.put("blue", s -> new BlueComponent());
    commands.put("luma", s -> new Greyscale());
    commands.put("sepia", s -> new SepiaTone());
    commands.put("blur", s -> new Blur());
    commands.put("intensity", s -> new IntensityComponent());
    commands.put("value", s -> new ValueComponent());
    commands.put("darken", s -> new AdjustBrightness(-Integer.parseInt(s[1])));
    commands.put("brighten", s -> new AdjustBrightness(Integer.parseInt(s[1])));
    commands.put("sharpen", s -> new Sharpen());
    commands.put("greyscale", s -> new Greyscale());
    commands.put("downscale", s -> new DownScale(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
    commands.put("flipHorizontal", s -> new FlipHorizontal());
    commands.put("flipVertical", s -> new FlipVertical());
  }

  @Override
  public void command(String[] args) throws IllegalArgumentException {
    Command c;
    Function<String[], Command> cmd =
            commands.getOrDefault(args[0], null);

    if (cmd == null) {
      try {
        if (args[0].equals("load")) {
          model.load(args[1], args[2]);
        } else if (args[0].equals("save")) {
          model.save(args[1], args[2]);
        } else {
          throw new IllegalArgumentException("Unknown Command: ");
        }
        this.view.updateGui(args);
      } catch (Exception e) {
        throw new IllegalArgumentException("Cannot execute given command: " + e.getMessage());
      }
    } else {
      try {
        c = cmd.apply(args);
        String name = args[args.length-2];
        String newName = args[args.length-1];
        if (this.view.getPartial()) {
          c.setMask(this.view.getMask());
          this.view.setPreview(c.execute(this.model.getImage(name)));
          this.view.renderMessage("Preview of: " +args[0] + " Displayed Below");
          this.view.refresh();
        } else {
          model.accept(name, newName, c);
          this.view.updateGui(args);
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Cannot execute given command: " + e.getMessage());
      }
    }
  }
}
