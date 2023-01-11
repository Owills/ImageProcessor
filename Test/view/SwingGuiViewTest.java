package view;

import org.junit.Test;

import controller.ControllerFeatures;
import controller.GuiController;
import model.CommandImageProcessingModel;
import model.CommandModel;

import static org.junit.Assert.assertEquals;

/**
 * tests for gui view.
 */
public class SwingGuiViewTest {

  @Test
  public void testGui() {
    Appendable output = new StringBuilder();
    GuiView viewMock = new SwingGUIViewMock(output);
    CommandImageProcessingModel model = new CommandModel();
    GuiView view = new SwingGuiView(model);
    ControllerFeatures controller = new GuiController(view, model);

    view.renderMessage("Test GUI View");
    assertEquals("", output.toString());
    viewMock.renderMessage("Test the render message method");
    assertEquals("Render Message: Test the render message method\n", output.toString());

    viewMock.refresh();
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n",
            output.toString());

    viewMock.accept(controller);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n"
            + "The command is accepted\n", output.toString());

    String[] input = {"load", "newExample.ppm", "newExample"};
    viewMock.updateGui(input);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n"
            + "The command is accepted\n" + "Load file is selected\n", output.toString());


    String[] input2 = {"red-component", "newExample", "newExampleBlur"};
    viewMock.updateGui(input2);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n"
            + "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n",
            output.toString());

    String[] input3 = {"save", "newExample.ppm", "newExample.png"};
    viewMock.updateGui(input3);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n"
            + "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n"
            + "Save file is selected\n", output.toString());

    String[] input4 = {"blur", "newExample.ppm", "newExample.png"};
    viewMock.updateGui(input4);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n"
            + "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n" +
            "Save file is selected\n" + "blur file is selected\n", output.toString());

    String[] input5 = {"green-component", "newExample", "newExampleRed"};
    viewMock.updateGui(input5);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n"
                    + "The command is accepted\n" + "Load file is selected\n" +
                    "red file is selected\n"
                    + "Save file is selected\n" + "blur file is selected\n" +
                    "green file is selected\n",
            output.toString());


    String[] input6 = {"brighten", "newExample", "newExampleRed"};
    viewMock.updateGui(input6);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n" +
            "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n" +
            "Save file is selected\n" + "blur file is selected\n" + "green file is selected\n" +
            "brighten file is selected\n", output.toString());


    String[] input7 = {"darken", "newExample", "newExampleRed"};
    viewMock.updateGui(input7);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n" +
            "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n" +
            "Save file is selected\n" + "blur file is selected\n" + "green file is selected\n" +
            "brighten file is selected\n" + "darken file is selected\n", output.toString());


    String[] input8 = {"vertical-flip", "newExample", "newExampleRed"};
    viewMock.updateGui(input8);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n" +
            "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n" +
            "Save file is selected\n" + "blur file is selected\n" + "green file is selected\n" +
            "brighten file is selected\n" + "darken file is selected\n" +
            "Flip-Vertical file is selected\n", output.toString());

    String[] input9 = {"horizontal-flip", "newExample", "newExampleRed"};
    viewMock.updateGui(input9);
    assertEquals("Render Message: Test the render message method\n" +
            "Refresh\n" + "The command is accepted\n" + "Load file is selected\n" +
            "red file is selected\n" + "Save file is selected\n" + "blur file is selected\n" +
            "green file is selected\n" + "brighten file is selected\n" + "darken file is selected\n"
            + "Flip-Vertical file is selected\n" + "Flip-Horizontal file is selected\n",
            output.toString());

    String[] input10 = {"sharpen", "newExample", "newExampleRed"};
    viewMock.updateGui(input10);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n" +
            "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n" +
            "Save file is selected\n" + "blur file is selected\n" + "green file is selected\n" +
            "brighten file is selected\n" + "darken file is selected\n" +
            "Flip-Vertical file is selected\n" + "Flip-Horizontal file is selected\n" +
            "sharpen file is selected\n", output.toString());

    String[] input11 = {"sepia", "newExample", "newExampleRed"};
    viewMock.updateGui(input11);
    assertEquals("Render Message: Test the render message method\n" + "Refresh\n" +
            "The command is accepted\n" + "Load file is selected\n" + "red file is selected\n" +
            "Save file is selected\n" + "blur file is selected\n" + "green file is selected\n" +
            "brighten file is selected\n" + "darken file is selected\n" +
            "Flip-Vertical file is selected\n" + "Flip-Horizontal file is selected\n" +
            "sharpen file is selected\n" + "sepia file is selected\n", output.toString());
  }
}