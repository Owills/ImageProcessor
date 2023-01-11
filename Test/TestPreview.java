import org.junit.Test;

import javax.swing.*;

import controller.ControllerFeatures;
import controller.GuiController;
import model.CommandImageProcessingModel;
import model.CommandModel;
import view.SwingGuiView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

/**
 * tests for preview in gui.
 */
public class TestPreview {

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
  }

  @Test
  public void testLoad() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");

    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertEquals(message.getText(), "Command 'load' executed Successfully as: example");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testBlur() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"blur", "example", "blurTest"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("blurTest"));
    assertEquals(message.getText(), "Preview of: blur Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testSepia() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"sepia", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: sepia Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testRed() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"red", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: red Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testBlue() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"blue", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: blue Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testGreen() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"green", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: green Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testValue() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"value", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: value Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testLuma() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"luma", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: luma Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testIntensity() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"intensity", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: intensity Displayed Below");
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
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"sharpen", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: sharpen Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testFlipHorizontal() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"flipHorizontal", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: flipHorizontal Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testFlipVertical() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"flipVertical", "example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: flipVertical Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testBrighten() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"brighten", "10" ,"example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: brighten Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }


  @Test
  public void testDarken() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"darken", "10" ,"example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: darken Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testGreyscale() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"greyscale", "10" ,"example", "test"};
    this.controller.command(args);
    assertNotNull(model.getImage("example"));
    assertThrows(IllegalArgumentException.class,()-> model.getImage("test"));
    assertEquals(message.getText(), "Preview of: greyscale Displayed Below");
    assertEquals(combobox.getItemCount(), 1);
    assertEquals(combobox.getItemAt(0), "example");
  }

  @Test
  public void testDownscale() {
    this.setDefaults();
    args = new String[]{"load", "newExample.ppm", "example"};
    assertThrows(IllegalArgumentException.class, () -> model.getImage("example"));

    assertEquals(message.getText(), "");
    assertEquals(combobox.getItemCount(), 0);
    assertEquals(preview.getItemCount(), 2);
    assertEquals(preview.getSelectedItem(), "Preview");
    this.controller.command(args);
    args = new String[]{"downscale", "10" ,"10" ,"example", "test"};
    assertThrows(IllegalArgumentException.class, () -> this.controller.command(args));
  }
}
