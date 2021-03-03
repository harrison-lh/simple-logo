# SLogo Design Plan
### Group Number: 5
### Names:
Cole Spector (cgs26)
Marc Chmielewski (msc68)
David Li (dl303)
Harrison Huang (hlh38)

## Introduction

**Problem Statement:** Simple Logo, or SLogo for short, is a dialect of the Lisp programming language that is design to teach programming to children using turtles. The goal of this project is to implement a basic SLogo interpreter, and provide a rudimentary IDE in which to execute SLogo commands in a REPL fashion.

* **Design Goals**
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

**Primary Architecture:** The Controller serves to control the functionality of the whole program. It first parses and interprets commands to be sent to Model. The results of these commands are retrieved from Model and then sent to View to be displayed. The Controller should have instances of Model and View, while neither Model nor View should have instances of Controller.

## Overview


* **Frontend External API:**
    * ![Frontend External CRC](https://i.imgur.com/IWeh5mP.png)

* **Frontend Internal API:**
    * ![Frontened Internal CRC](https://i.imgur.com/CrsydW0.png)
    * ![JavaFXPen.java CRC](https://i.imgur.com/JJnb1bn.png)
    * ![JavaFXPen.java CRC](https://i.imgur.com/JJnb1bn.png)


* **Backend External API:**

    * ![Controller.java CRC](https://i.imgur.com/yGeMbge.png)
    * ![TurtleArmy.java CRC](https://i.imgur.com/y4pYeWp.png)
    * ![CommandParser.java CRC](https://i.imgur.com/WCCVbp4.png)
    * ![Variables.java CRC](https://i.imgur.com/F3E54GO.png)

* **Backend Internal API:**

    * ![Coordinates.java CRC](https://i.imgur.com/rE4D0VK.png)
    * ![Pen.java CRC](https://i.imgur.com/4GV5ejX.png)
    * ![Command.java CRC](https://i.imgur.com/u5Byalf.png)
    * ![CoordinateTranslator.java CRC](https://i.imgur.com/LADPJPU.png)
    * ![JavaFXCoordinateTranslator.java CRC](https://i.imgur.com/bqUSCfM.png)
    * ![TurtleInfantry.java CRC](https://i.imgur.com/JFWBvHc.png)

    * ![Turtle.java CRC](https://i.imgur.com/DrqGOPC.png)






## User Interface
* **User interface components**
    * **Menu bar:** The menu bar will consist of buttons that allow the user to change the appearance (background color, grid settings, turtle image, pen color) and the language. Clicking these buttons will probably open new windows that allow the user to select a color/image. It will also have an info button that opens a new window listing all the available commands.
    * **Turtle Canvas**: The turtle canvas will take up most of the space and display the turtle on a canvas.
    * **Variables and Commands**: Next to the canvas, the variables being used and user-defined commands will be listed for the user to see
    * **Command history**: At the bottom of the window, there will be a part that displays the commands the user has inputed, like a console.
    * **Input box**: To the left of the command history, there will be an input box. This box is made somewhat taller so that the user can input multi-line commands.
    * **Error window**: If the user inputs a faulty command, an error window will open displaying the details of the error.


## Design Details
* **Model**:
    * Turtle.java
        * Has a Pen
        * Foward
        * Turn
    * interface Coordinates.java
        * X, Y, heading
    * interface Pen.java
        * Has a Color<E>
        * Has a Shape<E>
        * Has a Stamp() Method
    * Command.java
        * An object that represents a command
            * Type of command: move/rotate
            * "Magnitude" of command
* **Controller**:
    * CommandParser.java
        * Reads commands in from property file
    * TurtleInfantry.java
        * Has a Turtle
        * Has a Deque<Command> commandsToRun
        * Has a Deque<Command> commandHistory
    * TurtleArmy.java: implements Collection
        * Has a List<TurtleInfantry>
        * method .order(TurtleInfantry, Command)
    * interface Variables.java
        * Has a Map<String, E>
    * IntegerVariables.java
        * Has a Map<String, Integer>
    * TurtleVariables.java
        * Has a Map<String, TurtleInfantry>
    * interface CoordinateTranslator.java
    * JavaFXCoordiateTranslator.java
        * Translates JavaFX coordinates to having               center (0,0)
    * Controller.java
        * Has a View
        * Has a TurtleArmy
        * Has a CommandParser
        * Has a Set<Variables>

* **View**:
    * MainView.java
        * Arranges and holds all the view elements
    * interface SplashScreen.java
    * MenuBar.java
        * interface Selector.java
        * BackgroundSelector.java
        * GridSelector.java
        * TurtleSelector.java
        * PenSelector.java
        * LanguageSelector.java
        * CommandListWindow.java
    * Canvas.java
        * TurtleView.java
    * InputBox.java
    * CommandHistoryBox.java
    * VariablesBox.java
    * UDCommandsBox.java
    * ErrorWindow.java
    * JavaFXPen.java implements Pen.java


## Test Plan

**General Strategy:**
* **EXCEPTIONS:** Throwing meaningful exceptions makes it much easier to diagnose modes of failure in software, and as such, the API will have extensive exception handling, that will allow erroroneous commands, states, etc. to be caught and reported from the Controller to the View.

* **Small, SRP-Compliant Classes:** Having small classes helps keep the "unit" in unit-testing, by ensuring that there are only a few, specific things that could cause a class to fail. This makes debugging much easier, and enables us to better practice TDD strategies.

**Scenarios:**
* Model Data Management
    * *Scenario 1:* The TurtleController recieves a queue of new Commands for the turtle to run: `fd 50, bk 25`
        * Expected Outcome: The Turtle will update its position to 50 units in front of its current heading after processing the first command, (calling forward(50)) and then will retreat back 25 units after processing the second command (calling forward(-25)). This result can be verified by calling the Turtle's getX and getY methods to validate the coordinates.
    * *Scenario 2:* The TurtleController recieves a queue of new Commands for the turtle to run: `rt 90, rt -25`
        * Expected Outcome: The Turtle will update its position to rotate 90 degrees after procesing the first command (setHeading(getHeading() + 90)) and then will rotate back another 25 degrees (setHeading(getHeading() - 25)).
    * *Scenario 3:* The TurtleController recieves an empty queue of Commands for the turtle to run.
        * Expected Outcome: The Turtle will do nothing. If this was unexpected, it will throw an Exception, but regardless the model should not be updated.

* Basic Command Parsing
    * *Scenario 1:* The parser recieves the input `fd 50` from the user.
        * Expected Outcome: The parser will traverse the AST to find the entry for `fd`, and then create a new Command object with command `fd` and value `50`. The Command will then be queued into the Turtle's commandsToRun.
    * *Scenario 2:* The parser recieves the input `rt 90 rt -45` from the user.
        * Expected Outcome: The parser will traverse the AST to find the entry for `rt`, and then create a new Command object with command `rt` and value `90`. The Command will then be queued into the Turtle's commandsToRun. Then, the parser will create a new Command object with command `rt` and value `-45`. The Command will then be queued into the Turtle's commandsToRun.
    * *Scenario 3:* The parser recieves the input `tr 90, rt -25` from the user.
        * Expected Outcome: The parser will attempt to process the first command, but will throw and error upon recognizing that the token is invalid. This exception will be called InvalidCommand. The turtle model will not be updated.

* Variable Storage
    * *Scenario 1:* The Parser recieves the input `MAKE A 50`
        * Expected Outcome: The parser traverses the AST and calls the appropriate function for the command `MAKE` with the inputs of `A` and `50`. If the variable `A` does not already exist, this will create a new Variable `A`, add it to the `Set<Variable>` variables within the Controller, and set its value to `50`.
    * *Scenario 2:* The Parser recieves the input `SET A 50`
        * Expected Outcome: The parser traverses the AST and calls the appropriate function for the command `SET` with the inputs of `A` and `50`. If the variable `A` already exists, this will set its value to `50`. If not, this will throw a `VariableNotFound` Exception.
    * *Scenario 3:* The Parser recieves the input `SET MAKE 50`
        * Expected Outcome: The parser traverses the AST and calls the appropriate function for the command `SET` with the inputs of `MAKE` and `50`. However, since `MAKE` is a reserved keyword in SLogo, this will throw a `ReservedKeywordException` and refuse to create the variable.

* Function Storage
    * *Scenario 1:* The Parser recieves the input `TO TEST [ A ] [ fd A ]`
        * Expected Outcome: The parser traverses the AST and calls the appropriate function for the command `TO` with the parameter `A`, and then traverses the AST for each command, buliding out a commandList of `fd A`. This is then saved to a new node on the AST named `TEST`.
    * *Scenario 2:* The Parser recieves the input `TO TEST [ A ] [ fd A ]`, but `TEST` has already been defined.
        * Expected Outcome: The parser traverses the AST and calls the appropriate function for the command `TO` with the parameter `A`, and then traverses the AST for each command, buliding out a commandList of `fd A`. This is then saved to a the node on the AST named `TEST`, overwriting what is presently saved there.
    * *Scenario 3:* The Parser recieves the input `TO MAKE [ A ] [ fd A ]`, but `MAKE` is a reserved keyword.
        * Expected Outcome: The parser traverses the AST and calls the appropriate function for the command `TO` with the parameter `A`, and then traverses the AST for each command, buliding out a commandList of `fd A`. However, when this is attempted to be saved to the AST, it is recognized as a reserved keyword and the write is denied, throwing a `ReservedKeywordException`

## Design Considerations

* **Who Can Talk to What?**
    * Proposition 1: View owns Controller, owns Model
        * Pros:
            * Encapsulated. If we want another instance of the program, just spin up another View and it just sandboxes.
        * Cons:
            * Hard to extend
            * Lots of interplay between the View and downstream systems, which will lead to bloat/feature creep
    * Proposition 2: Controller owns both the Model and the View
        * Pros:
            * Modular design of View enables extension without bloat/feature creep
        * Cons:
            * Requires listeners for the controller to get inputs from view

* **What is the Pen and Who Owns it?**
    * Proposition 1: The Turtle owns a Pen object
        * Pros:
            * Keeps the information local to the Turtle and encapsulated
        * Cons:
            * Makes communicating up to the view through the Controller a *NIGHTMARE*
            * Just bad design
    * Proposition 2: The View owns a Pen object
        * Pros:
            * Keeps the information where it needs to ultimately wind up.
        * Cons:
            * Splits state between View and Model (BAD!)
            * Similar to proposition 1, communicating this back down to the Model through the Controller gets ugly.
    * Proposition 3: The Turtle implements a Pen interface, and the View owns a separate JavaFXPen that also implements a Pen interface
        * Pros:
            * Clean communication protocol
            * Turtle owns the state, while the View owns the behavior of actually drawing
            * Good design!
        * Cons:
            * A bit more code overhead out the gate, but this isn't even really a con since there's a ***lot*** of savings in the long-run.

**General Assumptions and Dependencies**
* There will only be one turtle
    * The code is designed for extensibility, but presently, this is designed with the assumption that only a single turtle exists on screen at any given point in time.


## Team Responsibilities

* Marc Chmielewski
    * Primary: Parser and associated controller elements
    * Secondary: Model/JUnit Testing

* David Li
    * All internal view elements

* Harrison Huang
    * Model and secondary view, interfacing model and view

* Cole Spector
    * Controller