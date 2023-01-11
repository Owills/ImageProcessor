package controller;

import org.junit.Test;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import model.CommandImageProcessingModel;
import model.CommandModel;
import view.SwingGuiView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

/**
 * tests for gui controller.
 */
public class GuiControllerTest {

  CommandImageProcessingModel model;
  SwingGuiView view;
  ControllerFeatures controller;
  String[] args;

  JLabel message;
  JComboBox<String> combobox;
  JComboBox<String> preview;

  private void setDefaults() {
    this.model = new CommandModel();
    this.view = new SwingGuiView(model);
    this.controller = new GuiController(view, model);

    JPanel main = (JPanel) view.getContentPane();
    assertEquals(main.getComponentCount(), 1);
    JScrollPane window = (JScrollPane) main.getComponent(0);

    JViewport viewport = (JViewport) window.getComponent(0);


    JPanel mainPanel = (JPanel) viewport.getComponent(0);

    message = (JLabel) mainPanel.getComponent(0);
    JPanel comboboxPanel = (JPanel) mainPanel.getComponent(1);

    combobox = (JComboBox<String>) comboboxPanel.getComponent(1);
    JPanel previewPanel = (JPanel) mainPanel.getComponent(2);
    preview = (JComboBox<String>) previewPanel.getComponent(0);
    preview.setSelectedItem("Full Image");

  }

  @Test
  public void testLoad() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);

    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertEquals(message.getText(), "Command 'load' executed Successfully as: example");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testSave() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);

    this.controller.command(args);
    args = new String[]{"save", "newExample.ppm", "example"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertEquals(message.getText(), "Command 'save' executed Successfully as: example");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testSharpen() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"sharpen", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'sharpen' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testSepia() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"sepia", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'sepia' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testBlur() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"blur", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'blur' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testIntensityBlue() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"blue", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'blue' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testGreen() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"green", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'green' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testRed() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"red", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'red' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testLuma() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"luma", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'luma' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testIntensity() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"intensity", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'intensity' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testValue() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"value", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'value' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testBrighten() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"brighten", "10", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'brighten' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testDarken() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"darken", "10", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'darken' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testFlipHorizontal() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"flipHorizontal", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'flipHorizontal' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }

  @Test
  public void testFlipVertically() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    this.controller.command(args);

    args = new String[]{"flipVertical", "example", "example2"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertNotNull(model.getImage("example2"));
    assertNotEquals(model.getImage("example"), model.getImage("example2"));
    assertEquals(message.getText(), "Command 'flipVertical' " +
            "executed Successfully as: example2");
    assertEquals(combobox.getItemCount(), 2);
    assertEquals(combobox.getItemAt(0), "example");
    assertEquals(combobox.getItemAt(1), "example2");
  }


  @Test
  public void testSharpenNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"sharpen", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testSharpenInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"sharpen", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testBrightenNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"brighten", "3", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testBrightenInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"brighten", "random", "example", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"brighten", "3", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testDarkenNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"darken", "3", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testDarkenInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"darken", "random", "example", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"darken", "3", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testSepiaNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"sepia", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testSepiaInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"sepia", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testBlurNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"blur", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testBlurInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"blur", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testBlueNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"blue", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testBlueInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"blue", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testGreenNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"green", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testGreenInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"green", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testRedNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"red", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testRedInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"red", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testLumaNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"luma", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testLumaInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"luma", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }


  @Test
  public void testIntensityNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"intensity", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testIntensityInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"intensity", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testValueNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"value", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testValueInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"value", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testFlipVerticalNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"flipVertical", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testFlipVerticalInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"flipVertical", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testFlipHorizontalNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"flipHorizontal", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testFlipHorizontalInvalidArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"flipHorizontal", "random", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testSaveNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"save", "newExample.ppm"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testSaveIncorrectArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"save", "newExample.ppm", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testSaveInvalidFileType() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    this.controller.command(args);
    args = new String[]{"save", "newExample.huh", "random"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testLoadNotEnoughArguments() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testLoadNoFile() {
    this.setDefaults();
    args = new String[]{"load", "random.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testCommandsNoLoad() {
    this.setDefaults();
    args = new String[]{"darken", "1", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"brighten", "1", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"flipHorizontal", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"flipVertical", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"value", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"intensity", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"luma", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"red", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"green", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"blue", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"blur", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"sharpen", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
    args = new String[]{"sepia", "example", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }

  @Test
  public void testNonExistentCommand() {
    this.setDefaults();
    args = new String[]{"huh", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }
}
