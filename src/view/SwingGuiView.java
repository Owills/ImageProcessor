package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ControllerFeatures;
import model.Color;
import model.CommandImageProcessingModel;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**
 * class that represents the main window of the gui.
 */
public class SwingGuiView extends JFrame implements ActionListener, GuiView {
  private ControllerFeatures c;
  private final CommandImageProcessingModel modelState;
  private String[] args;
  private final JLabel messageLabel;
  private final JLabel comboboxDisplay;
  private final JLabel fileOpenDisplay;
  private final String[] images = {"none", "histogram"};
  private final JComboBox<String> combobox = new JComboBox<String>();
  private final JComboBox<String> preview = new JComboBox<String>();
  ;
  private final ImagePanel colorImage;

  private final Preview previewImage;

  private final Histogram histogram;
  private final JLabel inputDisplay;

  /**
   * gives a CommandImageProcessingModel creates a SwingGuiView.
   *
   * @param model the model
   * @throws IllegalArgumentException if the model is null
   */
  public SwingGuiView(CommandImageProcessingModel model) throws IllegalArgumentException {
    super("Image Processing");
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.modelState = model;
    this.messageLabel = new JLabel();
    setSize(700, 700);

    JPanel mainPanel = new JPanel();
    mainPanel.add(messageLabel);
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //combo boxes
    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBorder(BorderFactory.createTitledBorder("Select Image: "));
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(comboboxPanel);
    if (comboboxPanel.getHeight() == 0) {
      comboboxDisplay = new JLabel("Load an image first");
    } else {
      comboboxDisplay = new JLabel("What image do you want to show?");
    }
    comboboxPanel.add(comboboxDisplay);
    //the event listener when an option is selected
    combobox.setActionCommand("Image options");
    combobox.addActionListener(this);
    comboboxPanel.add(combobox);

    // preview choice
    JPanel previewPanel = new JPanel();
    previewPanel.setBorder(BorderFactory.createTitledBorder("Select Preview or Full Image " +
            "manipulation: "));
    previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(previewPanel);

    //the event listener when an option is selected
    preview.setActionCommand("preview");
    preview.addActionListener(this);
    previewPanel.add(preview);
    preview.addItem("Preview");
    preview.addItem("Full Image");


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image and its Histogram: " +
            "Click on image to change preview location"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);
    JLabel[] imageLabel = new JLabel[images.length];
    imageLabel[0] = new JLabel();
    colorImage = new ImagePanel(this);
    JScrollPane shownImage = new JScrollPane(colorImage);

    shownImage.createHorizontalScrollBar();
    shownImage.createVerticalScrollBar();
    shownImage.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
    shownImage.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

    imageLabel[0].setIcon(new ImageIcon(images[0]));
    shownImage.getViewport().setPreferredSize(new Dimension(100, 400));
    imagePanel.add(shownImage);
    imageLabel[1] = new JLabel();


    histogram = new Histogram();
    JScrollPane histogramBox = new JScrollPane(histogram);
    imageLabel[1].setIcon(new ImageIcon(images[1]));
    histogramBox.setPreferredSize(new Dimension(100, 400));
    imagePanel.add(histogramBox);

    previewImage = new Preview();
    previewImage.setBorder(BorderFactory.createTitledBorder("Showing the previewing of image:"));
    mainPanel.add(previewImage);

    //file open
    JPanel applyPreview = new JPanel();
    applyPreview.setLayout(new FlowLayout());
    mainPanel.add(applyPreview);
    JButton applyPreviewButton = new JButton("Apply Preview");
    applyPreviewButton.setActionCommand("apply preview");
    applyPreviewButton.addActionListener(this);
    applyPreview.add(applyPreviewButton);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Load a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileSavePanel);
    JButton fileSaveButton = new JButton("Save current file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    fileSavePanel.add(fileSaveButton);
    JLabel fileSaveDisplay = new JLabel("Success message will appear above");
    fileSavePanel.add(fileSaveDisplay);

    //file save
    JPanel downscalePanel = new JPanel();
    downscalePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(downscalePanel);
    JButton downscaleButton = new JButton("Downscale");
    downscaleButton.setActionCommand("downscale");
    downscaleButton.addActionListener(this);
    downscalePanel.add(downscaleButton);
    JLabel downscaleDisplay = new JLabel("Success message will appear above");
    downscalePanel.add(downscaleDisplay);

    //flip flipHorizontal
    JPanel filefhPanel = new JPanel();
    filefhPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filefhPanel);
    JButton fileFHButton = new JButton("Flip Image Horizontally");
    fileFHButton.setActionCommand("flipHorizontal");
    fileFHButton.addActionListener(this);
    filefhPanel.add(fileFHButton);
    JLabel fileFHDisplay = new JLabel("Image Name will appear above");
    filefhPanel.add(fileFHDisplay);

    //flip flipVerticaly
    JPanel filefvPanel = new JPanel();
    filefvPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filefvPanel);
    JButton filefvButton = new JButton("Flip Image Vertically");
    filefvButton.setActionCommand("flipVertical");
    filefvButton.addActionListener(this);
    filefvPanel.add(filefvButton);
    JLabel fileFVDisplay = new JLabel("Image Name will appear above");
    filefvPanel.add(fileFVDisplay);

    //darken 'amount' 'name' 'new name'
    JPanel filedPanel = new JPanel();
    filedPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filedPanel);
    JButton filedButton = new JButton("Darken Image");
    filedButton.setActionCommand("darken");
    filedButton.addActionListener(this);
    filedPanel.add(filedButton);
    JLabel fileDarkenDisplay = new JLabel("Image Name will appear above");
    filedPanel.add(fileDarkenDisplay);

    //brighten 'amount' 'name' 'new name'
    JPanel fileBPanel = new JPanel();
    fileBPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileBPanel);
    JButton fileBButton = new JButton("Brighten Image");
    fileBButton.setActionCommand("brighten");
    fileBButton.addActionListener(this);
    fileBPanel.add(fileBButton);
    JLabel fileBrightenDisplay = new JLabel("Image Name will appear above");
    fileBPanel.add(fileBrightenDisplay);

    // value-component 'name' 'new name'
    JPanel filevPanel = new JPanel();
    filevPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filevPanel);
    JButton filevButton = new JButton("Get Value Component of Image");
    filevButton.setActionCommand("value");
    filevButton.addActionListener(this);
    filevPanel.add(filevButton);
    JLabel fileValueDisplay = new JLabel("Image Name will appear above");
    filevPanel.add(fileValueDisplay);

    // intensity-component 'name' 'new name'
    JPanel fileiPanel = new JPanel();
    fileiPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileiPanel);
    JButton fileiButton = new JButton("Get Intensity Component of Image");
    fileiButton.setActionCommand("intensity");
    fileiButton.addActionListener(this);
    fileiPanel.add(fileiButton);
    JLabel fileIntensityDisplay = new JLabel("Image Name will appear above");
    fileiPanel.add(fileIntensityDisplay);

    // luma-component 'name' 'new name'
    JPanel filelPanel = new JPanel();
    filelPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filelPanel);
    JButton filelButton = new JButton("Get Luma Component of Image");
    filelButton.setActionCommand("luma");
    filelButton.addActionListener(this);
    filelPanel.add(filelButton);
    JLabel fileLumaDisplay = new JLabel("Image Name will appear above");
    filelPanel.add(fileLumaDisplay);

    // red-component 'name' 'new name'
    JPanel filerPanel = new JPanel();
    filerPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filerPanel);
    JButton filerButton = new JButton("Get Red Component of Image");
    filerButton.setActionCommand("red");
    filerButton.addActionListener(this);
    filerPanel.add(filerButton);
    JLabel fileRedDisplay = new JLabel("Image Name will appear above");
    filerPanel.add(fileRedDisplay);

    // green-component 'name' 'new name'
    JPanel filegPanel = new JPanel();
    filegPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filegPanel);
    JButton filegButton = new JButton("Get Green Component of Image");
    filegButton.setActionCommand("green");
    filegButton.addActionListener(this);
    filegPanel.add(filegButton);
    JLabel fileGreenDisplay = new JLabel("Image Name will appear above");
    filegPanel.add(fileGreenDisplay);

    // blue-component 'name' 'new name'
    JPanel fileBluePanel = new JPanel();
    fileBluePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileBluePanel);
    JButton fileBlueButton = new JButton("Get Blue Component of Image");
    fileBlueButton.setActionCommand("blue");
    fileBlueButton.addActionListener(this);
    fileBluePanel.add(fileBlueButton);
    JLabel fileBlueDisplay = new JLabel("Image Name will appear above");
    fileBluePanel.add(fileBlueDisplay);

    // blur
    JPanel fileBlurPanel = new JPanel();
    fileBlurPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileBlurPanel);
    JButton fileBlurButton = new JButton("Blur Image");
    fileBlurButton.setActionCommand("blur");
    fileBlurButton.addActionListener(this);
    fileBlurPanel.add(fileBlurButton);
    JLabel fileBlurDisplay = new JLabel("Image Name will appear above");
    fileBlurPanel.add(fileBlurDisplay);

    // sharpen
    JPanel fileSharpenPanel = new JPanel();
    fileSharpenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileSharpenPanel);
    JButton fileSharpenButton = new JButton("Sharpen Image");
    fileSharpenButton.setActionCommand("sharpen");
    fileSharpenButton.addActionListener(this);
    fileSharpenPanel.add(fileSharpenButton);
    JLabel fileSharpenDisplay = new JLabel("Image Name will appear above");
    fileSharpenPanel.add(fileSharpenDisplay);

    // sepia
    JPanel fileSepiaPanel = new JPanel();
    fileSepiaPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileSepiaPanel);
    JButton fileSepiaButton = new JButton("Transform to Sepia Image");
    fileSepiaButton.setActionCommand("sepia");
    fileSepiaButton.addActionListener(this);
    fileSepiaPanel.add(fileSepiaButton);
    JLabel fileSepiaDisplay = new JLabel("Image Name will appear above");
    fileSepiaPanel.add(fileSepiaDisplay);

    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    inputDisplay = new JLabel("Default");
    inputDialogPanel.add(inputDisplay);
    this.refresh();
  }

  @Override
  public void actionPerformed(ActionEvent arg) {
    // TODO Auto-generated method stub
    String command = arg.getActionCommand();
    switch (command) {
      case "Image options":
        if (arg.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) arg.getSource();
          comboboxDisplay.setText("You selected: " + (String) box.getSelectedItem());
          images[0] = (String) box.getSelectedItem();
          this.colorImage.setImage(this.modelState.getImage(images[0]));
          this.histogram.setImage(this.modelState.getImage(images[0]));
          this.setPreview(this.modelState.getImage(images[0]));
          this.refresh();
        }
        break;
      case "apply preview":
        if (getPartial() && args != null){
          try {
            preview.setSelectedItem("Full Image");
            this.c.command(args);
            this.renderMessage(args[0] + " applied as: " + args[args.length - 2]);
          } catch (Exception e) {
            this.renderMessage(e.getMessage());
          }
        } else {
          this.renderMessage("Preview an image first");
        }

        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingGuiView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
          inputDisplay.setText(JOptionPane.showInputDialog(
                  "Please enter a name to refer to the Image as: "));
          String[] args = {"load", fchooser.getSelectedFile().getAbsolutePath(), inputDisplay.getText()};
          try {
            this.c.command(args);
          } catch (Exception e) {
            this.renderMessage(e.getMessage());
          }
        }
      }
      break;
      case "Save file": {
        try {
          inputDisplay.setText(JOptionPane.showInputDialog("Please file name to save as: "));
          String[] args = {"save", inputDisplay.getText(), images[0]};
          this.c.command(args);
          this.renderMessage("Image Saved Successfully as: " + args[args.length - 2]);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      case "flipVertical": {
        inputDisplay.setText(JOptionPane.showInputDialog(
                "Please enter a name to refer to the new Image as: "));
        inputDisplay.getText();
        args = new String[]{"flipVertical", images[0], inputDisplay.getText()};
        try {
          this.c.command(args);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      case "flipHorizontal": {
        inputDisplay.setText(JOptionPane.showInputDialog(
                "Please enter a name to refer to the new Image as: "));
        args = new String[]{"flipHorizontal", images[0], inputDisplay.getText()};
        try {
          this.c.command(args);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      case "darken": {
        inputDisplay.setText(JOptionPane.showInputDialog(
                "How much to you want to Darken the image by?"));
        String amount = inputDisplay.getText();
        inputDisplay.setText(JOptionPane.showInputDialog(
                "Please enter a name to refer to the new Image as: "));
        args = new String[]{"darken", amount, images[0], inputDisplay.getText()};
        try {
          this.c.command(args);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      case "brighten": {
        inputDisplay.setText(JOptionPane.showInputDialog(
                "How much to you want to Brighten the image by?"));
        String amount = inputDisplay.getText();
        inputDisplay.setText(JOptionPane.showInputDialog(
                "Please enter a name to refer to the new Image as: "));
        args = new String[]{"brighten", amount, images[0], inputDisplay.getText()};
        try {
          this.c.command(args);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      case "downscale": {
        inputDisplay.setText(JOptionPane.showInputDialog(
                "What is the height of the new image? "));
        String xRatio = inputDisplay.getText();
        inputDisplay.setText(JOptionPane.showInputDialog(
                "What is the width of the new image? "));
        String yRatio = inputDisplay.getText();
        inputDisplay.setText(JOptionPane.showInputDialog(
                "Please enter a name to refer to the new Image as: "));
        String[] args = {command, xRatio, yRatio, images[0], inputDisplay.getText()};
        try {
          this.c.command(args);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      case "value":
      case "sepia":
      case "intensity":
      case "luma":
      case "red":
      case "green":
      case "blue":
      case "blur":
      case "sharpen": {
        inputDisplay.setText(JOptionPane.showInputDialog(
                "Please enter a name to refer to the new Image as: "));
        args = new String[]{command, images[0], inputDisplay.getText()};
        try {
          this.c.command(args);
        } catch (Exception e) {
          this.renderMessage(e.getMessage());
        }
      }
      break;
      default:
    }
  }

  /**
   * updates the gui by adding the new image to list of options, and setting
   * * the new image to the current image, and informing the user of completed command.
   *
   * @param args command and its arguments
   */
  public void updateGui(String[] args) {
    images[0] = args[args.length - 1];
    boolean noNeedToAdd = false;
    for (int i = 0; i < this.combobox.getItemCount(); i++) {
      if (this.combobox.getItemAt(i).equals(images[0])) {
        noNeedToAdd = true;
      }
    }
    if (!noNeedToAdd) {
      this.combobox.addItem(images[0]);
    }
    this.combobox.setSelectedItem(images[0]);
    this.refresh();
    this.renderMessage("Command '" + args[0] + "' executed Successfully as: " + images[0]);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  @Override
  public void accept(ControllerFeatures c) {
    this.c = c;
  }

  public boolean getPartial() {
    if (preview.getSelectedIndex() == 0) {
      return true;
    }
    return false;
  }

  public Color[][] getMask() {
    Color[][] currentImage = this.modelState.getImage(images[0]);
    Color[][] mask = new Color[currentImage.length][currentImage[0].length];
    for (int i = 0; i < mask.length; i++) {
      for (int j = 0; j < mask[0].length; j++) {
        if (this.colorImage.isWithinPreview(i, j)) {
          mask[i][j] = new Color(0, 0, 0);
        } else {
          mask[i][j] = new Color(255, 255, 255);
        }
      }
    }
    return mask;
  }

  public void updatePreview() {
    if (args != null && !args[0].equalsIgnoreCase("open file")
            && !args[0].equalsIgnoreCase("save file")) {
      c.command(args);
    }
  }

  @Override
  public void setPreview(Color[][] preview) {
    int row = 0;
    int col = 0;
    for (int i = 0; i < preview.length; i++) {
      for (int j = 0; j < preview[0].length; j++) {
        if (this.colorImage.isWithinPreview(i, j)) {
          row = i;
          col = j;
          i = preview.length;
          j = preview[0].length;
        }
      }
    }

    int finalRow = 0;
    int finalCol = 0;
    for (int i = preview.length - 1; i >= 0; i--) {
      for (int j = preview[0].length - 1; j >= 0 ; j--) {
        if (this.colorImage.isWithinPreview(i, j)) {
          finalRow = i;
          finalCol = j;
          i = -1;
          j = -1;
        }
      }
    }

    Color[][] temp = new Color[finalRow - row + 1][finalCol - col + 1];
    for (int i = 0 ; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        temp[i][j] = preview[row + i][col + j];
      }
    }
    this.previewImage.setImage(temp);
  }
}