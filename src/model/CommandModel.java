package model;

import model.Commands.Command;

/**
 * Image processing model that can take any command.
 */
public class CommandModel extends BasicImageProcessingModel implements CommandImageProcessingModel {

  /**
   * creates a CommandModel.
   */
  public CommandModel() {
    super();
  }

  @Override
  public void accept(String name, String newName, Command c) {
    super.images.put(newName, c.execute(this.getImage(name)));
  }
}
