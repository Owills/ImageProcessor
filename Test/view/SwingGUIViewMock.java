package view;


import java.io.IOException;

import controller.ControllerFeatures;
import model.Color;

/**
 * mock SwingGUIView for testing purposes.
 */
public class SwingGUIViewMock implements GuiView {
  private Appendable out;

  public SwingGUIViewMock(Appendable out) {
    this.out = out;
  }

  @Override
  public void refresh() {
    try {
      this.out.append("Refresh\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot confirm that the application is refreshing");
    }

  }

  @Override
  public void renderMessage(String message) {
    try {
      this.out.append("Render Message: " + message + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot confirm that the message is shown in view");
    }

  }

  @Override
  public void accept(ControllerFeatures c) {
    try {
      this.out.append("The command is accepted\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot confirm that command is accepted");
    }

  }

  @Override
  public void updateGui(String[] args) {
    switch (args[0]) {
      case "Image options": {
        try {
          this.out.append("Image Option is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that image option is accepted\n");
        }
      }
      break;
      case "load": {
        try {
          this.out.append("Load file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that load file is accepted\n");
        }

      }
      break;
      case "save": {
        try {
          this.out.append("Save file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that save file is accepted\n");
        }

      }
      break;
      case "vertical-flip": {
        try {
          this.out.append("Flip-Vertical file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that flip-vertical is accepted\n");
        }
      }
      break;
      case "horizontal-flip": {
        try {
          this.out.append("Flip-Horizontal file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that flip-horizontal is accepted\n");
        }
      }
      break;
      case "darken": {
        try {
          this.out.append("darken file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that darken is accepted\n");
        }

      }
      break;
      case "brighten": {
        try {
          this.out.append("brighten file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that brighten is accepted\n");
        }

      }
      break;
      case "value-component": {
        try {
          this.out.append("Value file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that value is accepted\n");
        }
      }
      break;
      case "intensity-component": {
        try {
          this.out.append("intensity file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that intensity is accepted\n");
        }


      }
      break;
      case "luma-component": {
        try {
          this.out.append("luma file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that luma is accepted\n");
        }
      }
      break;
      case "red-component": {
        try {
          this.out.append("red file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that red is accepted\n");
        }
      }
      break;
      case "green-component": {
        try {
          this.out.append("green file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that green is accepted\n");
        }
      }
      break;
      case "blue-component": {
        try {
          this.out.append("blue file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that blue is accepted\n");
        }

      }
      break;
      case "blur": {
        try {
          this.out.append("blur file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that blur is accepted\n");
        }
      }
      break;
      case "sharpen": {
        try {
          this.out.append("sharpen file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that sharpen is accepted\n");
        }
      }
      break;
      case "sepia": {
        try {
          this.out.append("sepia file is selected\n");
        } catch (IOException error) {
          throw new IllegalArgumentException("Cannot confirm that serpia is accepted\n");
        }

      }
      break;
      default:


    }
  }

  @Override
  public boolean getPartial() {
    return false;
  }

  @Override
  public Color[][] getMask() {
    return new Color[0][];
  }

  @Override
  public void setPreview(Color[][] preview) {

  }

  @Override
  public void updatePreview() {

  }
}

