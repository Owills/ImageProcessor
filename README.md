
README
Image Processing Project

Extra Credit Updates:
Part 1: Downscale:
No major design changes were need, simply created a command
"downscale" using the command design pattern,
added it accordingly to the controller and added a button for it in gui.


Part 2: Partial Image Manipulation:
The first step was convert all commands that had not been
implemented using the command design patterns to their own 
class.

Design changes to what a command actually does. The command
interface was give an additional public method, setMask
which would take in an image to set as the mask for that
command (also checking that the mask is a valid black and white image).
This method is intended to be called by the controller,
each execute method for each command was updated to 
only apply image manipulate if the mask is black and to otherwise store the image
from the starting method (the intention being that regular execute will use a mask
image of equal size to the image the is all black, as such updating every pixel).

As such the abstract class AbstractCommand was added, which each command that
wants partial manipulation functionality should extend. This abstract class
keeps track of if the controller has sent a mask image and if there is not one being the
time the command needs to be executed will delegate a default all black mask to be used.

Part 3:
The major design changes that were required for this:
A new options box was added in the gui to keep track of if the processor is 
in preview mode or not.

A new class (Preview) and interface (ImagePreviewBox)
were added the draw the 200 by 200 preview.

The ImagePanel class which draws the image has to keep track of
which of its pixels are in the preview mode.
Once this was completed, if the gui attempts to do a command in preview mode, it will
create a mask image, by checking which pixels are within the preview box, as such
the command will only be executed on those pixels. Additionally, it will draw a red
box around the area that would be previewed. This classes constructor will aditionally
take in the guiView so that every time the preview box is updated, the view will
be updated accordingly.

The guiview interface was added with new methods to determine and set the preview.
If the controller attempts a command in preview setting, it will not update the model,
but instead execute the command partially with the mask (determined using ImagePanel class),
and the passes the image back to the gui and the preview





Assignment 6:

This README is for the image processing application developed for the Object Oriented Design class
at Northeastern University. This is a program that takes an image and manipulates that image by
applying different operations to its pixels. The image processing application includes a model and
controller.

The model consists of 1 interface which is the ImageProcessingModel interface and 4 classes which
are BasicImageProcessingModel class, Colors class, Color class, and Brightnesses class.The
ImageProcessingModel interface is responsible for the following functionalities: load and save
image, get image, flip an image horizontally and vertically, adjust the brightness of an image,
create a greyscale image. Three RGB colors red, green and blue are represented using the Colors
class. The brightness levels, volume intensity and luma, are represented using the Brightnesses
class. Each pixel of an image is represented using the Color class. Moreover, The
BasicImageProcessingModel class includes a representation of an image with its name and the 2d array
of its pixels.

The controller consists of 1 interface which is the ImageProcessingController interface and 1 class
which is BasicController class.The ImageProcessingController interface and BasicController class are
responsible for executing the processImage program. Users can use the command line to run the
program by providing the a script directly to the controller

Users are expected to enter valid commands for this application
to work. The commands are:
Load [sourceFile] [image],
save [destinationFile] [image],
brighten [Amount] [orginalImage] [resultImage],
darken [Amount] [orginalImage] [resultImage],
vertical-flip [orginalImage] [resultImage],
horizontal-flip [orginalImage] [resultImage],
value-component [orginalImage] [resultImage],
intensity-component [orginalImage] [resultImage],  
luma-component [orginalImage] [resultImage],
red-component [orginalImage] [resultImage],
green-component [orginalImage] [resultImage],
blue-component [orginalImage] [resultImage].

Users are expected to enter the file with the commands above into
the command line and replace the items in brackets for the
desired files or images.Moreover, users must enter ‘q’ or “quit”
to stop the program.Otherwise,the program will throw an exception.

Citations:

The image used in this example was found online:
Simple PPM Image. (n.d.). www.cs.swarthmore.ed
. https://www.cs.swarthmore.edu/~soni/cs35/f13/Labs/images/01/example5000.png

Assignment 5 / part 2 updates:

Design Changes:
We chose to move forward by using the command design pattern. This required us to create a new class
CommandModel, CommandController and new interface Command, CommandImageProcessingModel which would
be able to deal with handling command.

Each command would take a 2d array of colors which represent one image and manipulate it based on
the command. There are currently two types of commands, ColorTransformation which handles sepia and
grayscale transformations and ImageFilter which handles bluring and sharpening images.

The CommandModel simply has the functionality to accept a command, while extended the original
BasicImageProcessingModel, as such maintaining the original functionality.

The Controller was changed heavily. First we abstracted the code from the basic controller to an
abstract controller. Additionally we split the proceessImageMethod into two methods, including a new
method called processCommand which would handle commands. The new command controller has a map of
possible commands which can be called (but only commands through the command design pattern, the
command functionality from part 1 remains the same). When this CommandController process a command,
it checks if the command is in the map, otherwise calls the super method checking and executing the
original set of commands from part 1.

Additionally to compensate load and saving new file types. The BasicImageProcessingModel was edited.
New private methods for load and save were added which can load and save jpeg, png and bmp files.
The original load and save methods try to call these new methods respectively, successfully loading
or saving the file, or failing and trying to load / save the the file as a ppm.

Updated List of Commands (using CommandController).
Load [sourceFile] [image],
save [destinationFile] [image],
brighten [Amount] [orginalImage] [resultImage],
darken [Amount] [orginalImage] [resultImage],
vertical-flip [orginalImage] [resultImage],
horizontal-flip [orginalImage] [resultImage],
value-component [orginalImage] [resultImage],
intensity-component [orginalImage] [resultImage],  
luma-component [orginalImage] [resultImage],
red-component [orginalImage] [resultImage],
green-component [orginalImage] [resultImage],
blue-component [orginalImage] [resultImage].
blur [orginalImage] [resultImage].
sharpen [orginalImage] [resultImage].
greyscale [orginalImage] [resultImage].
sepia [orginalImage] [resultImage].

Assignment 5 / part 3 (gui) updates:

The gui view can be seen by running the main method of GuiDriver

Controller Updates:
A new controller interface was add called
ControllerFeatures, which represents the interface for
controller to be used the GuiViews

A new controller class GuiController was adds which can take in a
GuiView and CommandModel, this GuiController work
similarly to previous controller except it takes in  
a array of string as arguments, then try to pass the
arguments to the model, and then updates the view as necessary

View Updates:

A new interface was added the represents a GuiView. This
interface contains the signatures of methods necassary for
java graphic in awt and swing, along with a method
which to accept the controller and a method to update the gui which the controller should call.

A new class SwingGuiView was added which represent the
window for this gui. It has an options box to allow the user
to select which image they want to edit, two scrollable
jframe which represent the image and its corresponding histograms.
Below these are a list of buttons for every possible command.
The view will update the user on error / successes of commands at
the top of the screen.

New interfaces and classes where add to help draw the
current image (ImageInterface and ImagePanel) being edited
and its corresponding histogram (IHistrogram and Histogram).
Each of these class can accept and store an image as Color[][]
which it will use to draw what is needed in each jframe.
