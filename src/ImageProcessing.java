import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import controller.CommandController;
import controller.ControllerFeatures;
import controller.GuiController;
import controller.ImageProcessingController;
import model.CommandImageProcessingModel;
import model.CommandModel;
import view.BasicTextView;
import view.SwingGuiView;
import view.TextView;

/**
 * class that takes in command from the command line and passes to the controller.
 */
public final class ImageProcessing {
  /**
   * driver.
   *
   * @param args command line arguments
   * @throws FileNotFoundException if the file does not exist
   */
  public static void main(String[] args) throws FileNotFoundException {

    CommandImageProcessingModel model = new CommandModel();
    TextView textView = new BasicTextView(model);
    SwingGuiView guiView;
    ControllerFeatures controllerGUI;
    ImageProcessingController controllerNormal;

    if (args.length == 0) {

      SwingGuiView.setDefaultLookAndFeelDecorated(false);
      guiView = new SwingGuiView(model);
      controllerGUI = new GuiController(guiView, model);
      guiView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      guiView.setVisible(true);
    } else if (args[0].equals("-file") && args.length == 2 && args[1].contains(".txt")) {
      BufferedReader reader = new BufferedReader(new FileReader(args[1]));
      textView = new BasicTextView(model);
      controllerNormal = new CommandController(model, textView, reader);
      controllerNormal.processImages();
    } else if (args[0].equals("-text") && args.length == 1) {
      textView = new BasicTextView(model);
      controllerNormal = new CommandController(model, textView, new InputStreamReader(System.in));
      controllerNormal.processImages();
    } else {
      try {
        textView.renderMessage("Invalid Command " + args[0] + "\nPlease enter a new command \n");

      } catch (IOException e) {
        throw new IllegalStateException("Cannot show message on screen");
      }

    }
  }
}
