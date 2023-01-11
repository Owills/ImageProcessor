import java.io.FileNotFoundException;

import javax.swing.JFrame;


import controller.ControllerFeatures;
import controller.GuiController;
import model.CommandImageProcessingModel;
import model.CommandModel;
import view.SwingGuiView;

/**
 * class that runs the gui interface.
 */
public class GuIDriver {

  /**
   * driver.
   *
   * @param args command line arguments
   * @throws FileNotFoundException if the file does not exist
   */
  public static void main(String[] args) throws FileNotFoundException {
    CommandImageProcessingModel model = new CommandModel();
    SwingGuiView.setDefaultLookAndFeelDecorated(false);
    SwingGuiView view = new SwingGuiView(model);
    ControllerFeatures controller = new GuiController(view, model);


    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);
  }
}
