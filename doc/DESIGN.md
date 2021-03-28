# DESIGN Document for SLogo (Team 05)

## Name(s)

* Marc Chmielewski (msc68)
* David Li (dl303)
* Harrison Huang (hlh38)
* Cole Spector (cgs26)


## Role(s)

* Marc Chmielewski:
    * Model/Controller
        * Led development of Lexer/Parser and worked extnesively on the associated middleware to link it all together.
        * Also developed most of the back-end tests.
* Harrison Huang:
    * Model/Controller/Commands
        * Interfacing between model and view using property binding
* David Li:
    * View
        * Constructed the entire view, receiving updates from the back-end and updating back-end properties with user interaction
* Cole Spector:
    * Commands

## Design Goals
* MVC
* Make adding of new commands easy
    * Using a command-tree
    * Abstract Syntax Trees(ASTs) are friend, not food :)
* Extensibility
    * Add n-turtles with fairly low overhead
        * Different turtle images (i.e. arrow, turtle), different turtle coordinate systems (i.e. xy), etc.
    * Add additional colors, layouts, localizations, etc.
    * Ability for turtle to draw in shapes, not just lines
        * can use stamps along with a path-line
* Encapsulation
    * Turtles should be self-contained units
    * Clean API interfaces!!!
* Different screen edges (i.e. finite size, wrapped)

## High-Level Design

#### Back-End (Model)

* The back-end of SLogo lives within the Model package and revolves around fundamental unit of the `Turtle`.
  * `Turtle`s control their own "specific state", that is, their position, visibility, pen status, and id, but defer to the `GlobalProperties` on issues of global state.
      * Specific state that is pertinent to position is encapsulated within a subclass of the `Coordinates` abstract class.
        * In this particular implementation, this is always `GridCoordinates`, which handles a tradition Cartesian grid with headings measured in degrees, but this could be easily extended to alternative coordinate systems.
        * Other specific state, is left as primitives within the `Turtle` as there was no great grouping for purposes of encapsulation.
    * Examples of global state include the palette, pen color, background color, pen size, variables, and user commands.
      * As this information is required to be accessible between the many possible `Turtles` that could exist on screen after a `TELL` command, it is necessarily upstream of all of the `TurtleControllers`. 
    * SLogo variables are encapsulated within `Variable` objects that contain their name and value and are collectively members of the `Variables` encapsulated Map.

#### Middleware (Controller)

* The SLogo middleware primarily lives in the Controller package, and is responsible for modifying the state of the Turtle through the interpretation of various types of `Commands`.
* The highest level unit is the `Controller` object, which contains all of the objects required to completely assemble and control a SLogo parser.
* The management of any particular `Turtle` is handled by the `TurtleController` that is bound to the `Turtle`.
  * The `TurtleController`s are then in turn held by the `TurtleGeneral`, which is primarily tasked with marshalling the various `TurtleController`s and maintaining their global states.
    * Ex. Which `TurtleController`s are active, the Palette, the GlobalProperties, etc.
* The interpretation of a command String is handled by the `Parser`, which utilizes a `Lexer` to tokenize the command into identifiable chunks, which are then parsed to determine which `Command`s to instantiate and ultimately to determine how to build out the abstract syntax tree (AST).
  * Various private methods within the `Parser` define the behavior required to populate the AST under the various conditions that a piece of SLogo code may create.
    * Ex.) The schema for populating an AST with `fd sum sum 50 50 50` requires calling different methods in different combination than `repeat 5 [ fd 50 ]` as lists functionally exist as "sub-ASTs"
  * Once the AST is completely configured, the `Parser` delivers the AST to the "active" `TurtleController`s within the `TurtleGeneral` and commands them to execute.
  * The Parser also implements the `SelectorTarget` interface to allow it to communicate with the View in an efficient manner.
* Each `Command` has a specific implementation of `executeCommand(turtle, globalProperties)` method, which defines the specific execution behavior of the `Command` in question.
* The actual execution of `Command`s onto `Turtle`s is...commanded...by the `Parser`, which acts upon the turtleArmy of the `TurtleGeneral` to call the `execute` methods down the AST.

#### Front-End (View)
* The SLogo front-end lives in the View package and is responsible for the display of the application state to the user.
  * Examples of this display include the representation of the all of the Turtles and the lines drawn by them, the variables, the user-defined commands, and various global properties.
* The front-end gets this information through a number of different Property bindings throughout the application, which pull data from the application state and allow it to be rendered on screen.
* The fundamental unit of the View is the `Workspace`
  * The `Workspace` contains an instance of the `Controller` which is the highest-level object in the actual SLogo parser, and `MainView` which contains the actual front-end components that are responsible for the display of output and requisitioning of user input.
  * This design allows multiple instances of `Workspace` to be created, which in turn permits the implementation of a tab-driven sandbox environment.
* The `MainView` is composed of the front-end components required to build an instance of the SLogo parser interface. These components can be broken up into four main groups, and are packaged accordingly:
  * Canvas
    * Contains components pertinent to the canvas on which `Turtle`s live and the modifications thereof.
  * Info
    * Contains components that report information about Turtle status, the Palette, and other global data back to the user.
  * MenuBar
    * Contains components that the user can interact with to change global properties and data, such as the background color and the pen color, as well as buttons to save and load commands and variables to/from JSON.
  * Controller
    * Contains components that allow the user to control the `Turtle` graphically. For example, buttons that allow the turtle to be moved forwards/backwards a certain number of pixels, rotated a certain number of degrees, etc.


## Assumptions or Simplifications

Assumptions or Simplifications:

* The user won't want to make more than like 15 Turtles.
    * Otherwise the JVM will overflow the heap on the low-power, RAM-constrained machines found in your typical elementary school
* The user will be content with a 10-color palette, though this could be readily expanded to "n-colors" with the addition of a scrollbar in the pane.
* The GUI/help menu were not localized, though the SLogo language itself was.
* In general, the user will be fairly reasonable in their demands of the program
    * Ex. The user won't keep 10+ tabs open with multiple turtles each, won't have it try to calculate Pi by Monte Carlo Simulation, and won't try to concurrently keep this, 30 Chrome tabs, a pair of VMs, Vivado Hardware Synthesis, and a Tensorflow model training at the same time... [^1]
* In the interest of preserving the ability to have multi-line declarations we opted to have any amount of comments render the input void.
    * We weighed just scrapping commment lines from the input, but this would break under several circumstances
* We assume that `TELL` will be called separately from other commands, as our parse-cycle needs to before the Turtles are valid. This is for thread-safety, as we did not implement any sort of concurrency control in this code.
    * Ex.) `TELL [ 1 2 ]` followed by `fd 50` will create two turtles and move them each 50 pixels forwards.
    * Ex.) `TELL [ 1 2 ] fd 50` will create two turtles, but only move the FIRST turtle 50 pixels forwards, as the creation of the second Turtle has not yet necessarily resolved at this point.


## Changes from the Plan
See `API_CHANGES` document.

## How to Add New Features

#### Adding a New Command

Adding a new "calculator-code" style arithmetic command to this architecture is relatively trivial, as Reflection is utilized to add new `Command`s to the set. At minimum, all `Command`s extend the basic `Command` object, define a value for `NUM_PARAMS`, the number of parameters it takes (if any), and override the implementation of `executeCommand(turtle, globalProperties)` to actually implement their behavior. Note that the value of `NUM_PARAMS` needs to be passed to `setNumParams()` within the constructor for the new command as the member variable that enforces this is a member of `Command`.

The value of the parameters can be accessed by calling `getChildren().get(PARAM_NUMBER).execute(turtle, globalProperties)`. This is a uniform rule for all parameter types, including constant values, and allows for the stack-based architecture of SLogo to function properly. If this were not implemented this way it would be much harder to support the ability to take functions as parameters of other functions. (ie. situations like `fd fd 50`)

The specific implementation of the override of `executeCommand` will vary depending upon the command, but will generally take the form of `get parameters ==> do math/function ==> return result`. The result ***MUST*** be a double; this was a deliberate design decision made in accordance with the specification and failing to adhere to it will break the `Parser`.

The nomenclature for implementing a new command class is `NAME_OF_OPERATIONCommand`. This is **critically** important, as the Reflection is reliant upon pattern matching to find the right class to instantiate. 

A commented example of the implementation of these steps is the given below for `SumCommand`:

```java=
package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class SumCommand extends Command {

  // SUM takes two parameters, so that number is set here
  private static final int NUM_PARAMS = 2; 
  
  // Pass the value of NUM_PARAMS to setNumParams()
  public SumCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * This is where SumCommand overrides the executeCommand
   * method. The parameters are retrived by calling 
   * getChildren().get(INDEX_OF_PARAM).execute(turtle, globalProperties)
   * and stored in the appropriate type of variable.
   * The operation is then performed and returned.
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double a = getChildren().get(0).execute(turtle, globalProperties);
    double b = getChildren().get(1).execute(turtle, globalProperties);

    // To implement a different command, different math can be done here
    return a + b;
    

  }
}
```

For commands that modify global state like, for example, `SetBackgroundCommand` the steps are largely the same. The only difference is that the actual command operates on the member of `globalProperties` that it wishes to mutate. An example of this is given below:

```java=
package slogo.controller.commands;

import javafx.scene.paint.Color;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetBackground is a type of Command that sets the background color to the corresponding color
 * index.
 *
 * @author Harrison Huang
 * @author Marc Chmielewski
 */

public class SetBackgroundCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for SetBackgroundCommand.
   */
  public SetBackgroundCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the background to be the color at the given index of the palette.
   *
   * @param turtle           The current active turtle
   * @param globalProperties The GlobalProperties on which to change the background.
   * @return The index of the color that was set
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    int index = (int) getChildren().get(0).execute(turtle, globalProperties);
    Color color = globalProperties.paletteProperty().get(index);
    globalProperties.setBackgroundColorProperty(color);
    return index;
  }
}

```

#### Adding a New Component to the Front-End

Adding a new component to the Front-End is generally fairly straightforward. In general, our components utilize the `Consumer` interface to take in inputs from the user, and various `Property` objects and variable bindings to update state based off of changes to the Model. All components will ultimately be added to the `MainView` either directly or as a component of some greater "widget". It is within the `MainView` that the bindings and `Consumers` are configured.

If the new component is reliant upon the `globalProperties` it will need to be bound within the `bindGlobalProperties()` method of the `MainView`

#### Adding Undo/Redo

While I did not have time to implement them within the "Complete" window, implementing undo and redo would not have required major architectural changes. All that would need to be done would be to create a "versioning" object of some sort that would store the state of the `Turtle`s within the turtleArmy before the execution of a given AST of `Commands` and push that onto a stack after each execute. Then, if the user pressed the undo button, that state could be popped off the stack and the `Turtle`s could be reverted. Implementing this would require binding the internal `Turtle` state variables as well as the `globalProperties` to some listener, but this could likely be accomplished within the `TurtleGeneral` as part of the overall `Turtle` marshalling process. Redo would functionally be the same thing, but in reverse.
