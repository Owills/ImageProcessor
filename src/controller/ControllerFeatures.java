package controller;

/**
 * interface for controller to be used with guiView.
 */
public interface ControllerFeatures {

  /**
   * executes a command by delegating tasks to model and view.
   * @param args command and arguments
   */
  void command(String[] args);
}
