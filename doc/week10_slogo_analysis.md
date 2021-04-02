# SLogo Project Analysis

### Name: Harrison Huang

## Overall Design

### Frontend

* The frontend in the view package consists of a few different components, as seen in `MainView`.
    * Canvas holds the screen on which the turtles live and draw.
    * Info holds information displayed in the side bar, namely the turtle status, the palette, and
      variables and command.
    * MenuBar contains components that the user can toggle to change global properties and data
    * Controller contains components to move the turtle graphically.

* Each instance of the view can be said to be a workspace, so that different tabs can separate out
  different instances of SLogo.

* The frontend obtains information from the model using property bindings, which allow for the view
  to be notified when values change and then grab this information to update in return.

### Backend + Controller

* `Controller` is the highest level of the backend, controlling all other components.

* New commands are sent to the parser, which interpret text and convert them to commands to be run
  on turtles. These interpreted commands are in turn sent to `TurtleGeneral`, which owns all of the
  turtles and manages all turtle states.

* Commands are actually run by `TurtleController`, each of which is bound to one `Turtle`. `Turtle`
  consists of data fundamental to each turtle, with basic commands for movement and status.

### How to add new components

#### Adding new commands

* To create a new command, make a new class that extends `Command`, instantiate the correct number
  of parameters you want for the command, and implement the `executeCommand` method to do as you
  desire, making sure to return a double as a result of the execution.
* Then add the name of the new command to the properties files, adding a new line for each language
  you want to support for the command.
* This command then is automatically supported by the parser since it uses reflection to find the
  class corresponding to the new command.

#### Adding new component to the frontend

Using consumers to take inputs from the user and binding properties and variables, adding components
to the frontend is relatively straightforward. All components of the frontend are ultimately managed
by `MainView`, so one need only add a component directly there or by adding to an existing info or
control bar.

#### Additional unimplemented feature from the spec

In theory, implementing Undo/Redo from the spec would not be overly difficult, as it would only
require some way to store previous states of the turtles, canvas, and properties on a stack. Then,
undoing would pop off one state from the stack which would revert to one previous step, restoring
all of the previous values of that last state, and this state would be pushed onto the redo stack,
in case the user wants to redo this action.

### Dependencies

The dependencies in our project are quite straightforward, and I could only find one instance where
they could be improved. `GlobalProperties` holds a list of instances of `TurtleController`, while
`TurtleController` requires an instance of `GlobalProperties` in its constructor. Ideally, to avoid
this circular dependency, `TurtleGeneral` would handle the actual list of turtles while
`GlobalProperties` would serve as a middleman to `TurtleGeneral`, and so, `GlobalProperties` could
instead hold a different object instead of the `TurtleController` list to avoid the circular
dependency. Otherwise, the dependencies are logical and rather easy to follow. We removed our
previous use of `static` in order to be able to implement multiple palettes between different
workspaces, so there is no need to consider that.

### Two examples for each code design concept

#### Open Closed Principle

1. The Open Closed Principle was generally followed very well with regard to the execution of
   commands. Each new command can be found using reflection, and each command has sole
   responsibility of the execution itself, and neither the parser nor the controller interfere with
   this.

2. Some of the implemented commands, like `AskCommand` and `ClearScreenCommand`, directly execute
   commands on a list of commands they pull from `GlobalProperties`, while ideally this should not
   be handled within the scope of a single `executeCommand`, but likely by the
   `TurtleGeneral`.

#### Interface Segregation Principle

1. Using and implementing the abstract `executeCommand` method in every new command class, we
   succeed in the Interface Segregation Principle because each command implements this execute
   method, since we must always determine inputs and return a double after some action has or hasn't
   been performed.

2. The initial `Coordinates` interface didn't get used in a helpful way, as only `GridCoordinates`
   extended it. There's not a clear distinction of what `Coordinates` should do, and if it should in
   fact be narrower than what was actually implemented. Perhaps `Coordinates` should have strictly
   had to do with the position and heading itself, while an interface `CoordinatesProperty`
   could have been used in order to detect for changes to some `Coordinates` object.

#### Dependency Inversion Principle

1. Commands correctly rely on abstract Commands for their implementation of `execute` and
   subsequent `executeCommand`, which allow Commands to be flexible in their fulfillment of input
   parameters.

2. Similar to above, the view (in this case, mostly `TurtleView`) relies on a `Coordinates` object
   in order to determine position and heading of the turtle it needs to represent, though it has
   access to set methods as well, which forces `TurtleView` to depend on a broad `Coordinates`
   object while it only needs access to its values and some interface to return a string property.

### Feature from the spec made easy by code design

It was especially easy to add new commands for the project to support, as using reflection allowed
creation and support of any type of command as long as it was listed in the resource property file
and was a class named `SOMENAMECommand`, implementing the intended number of parameters and the
`executeCommand` method to its intended effect.

### Reflection on project APIs

Holistically, we have clear components that each work together and contribute to our code design.
Our classes and methods have clear, concise naming patterns that accurately describe what each class
and method do.

* For example, the `Parser` has just three public-facing methods, one method to parse the command
  string (written explicitly as `parseCommandString`) and two methods to set the syntax language.
* Since `Parser` has mainly private methods, the true implementation of `Parser` is encapsulated
  well and can be readily changed without affecting other code (aside from outward-facing
  functionality).
* Code can be complicated both in breadth and in depth. On one hand, you have the parser, which is a
  series of complicated operations to convert an input string to a properly made command tree. On
  the other hand, you have the view, which consists of many different elements that must talk to
  each other to be a cohesive functional unit. While any one part is simple enough, the addition of
  more elements makes the UI increasingly complex. Code is more understandable in smaller parts, and
  javadoc goes a long way with this as well. Having good attention to detail is also essential for
  good code, since weird formatting or logic can throw off new readers of the code.

### Conclusion

We can make changes to the commands, some of the properties, and definitely the parser without too
much difficulty. We have also kept in mind other specifications that could come up to change the
course of our project, most notably the pen being a stamp instead of drawing lines, and also
different coordinate systems. The structure of the command hierarchy has held up well for the
complete spec, so I would think that any new features would also be easy to add, whether it be
directly through the turtle or through the global properties.

## Your Design

### Overall code design, issues

Two things:

* The structure of the commands is designed to be able to handle all different types of commands
  which can rely on the result of other commands. Thus our abstract syntax tree design allowed for
  extension of new commands and implementation of commands depending on other commands to run.

* I also worked on property binding with some of the variables shared between model and view,
  especially those contained in `Turtle`. Adding property binding here allowed linkage of data
  without the problems that strict dependencies can bring. Using this, we can communicate
  effectively between model and view.

I have found no significant design issues, both in reviewing my code and using the design static
analysis tool. My code uses meaningful variable names, is modular with classes having a single
defined purpose, and has little duplication between classes.

### One abstraction

The abstract `Command` class was infinitely useful in reducing the amount of duplicated code I would
have written, along with reducing the amount of work my teammates would have to do to manually
support each new command. Because each command took in some parameters (or zero parameters) and
always had a return value of a double, there were obvious similarities between each of the commands,
allowing that abstraction to be helpful and effective.

### Two features

1. I helped design the structure of the commands (which went through quite a few iterations), and I
   implemented the vast majority of the command classes, mostly in turtle commands, mathematical
   operations, and boolean operations, as well as some stubs in the complete spec until we added
   support for global properties.
    * Support of the commands themselves is done in the `executeCommand` method, no matter what type
      of command it is (turtle commands, math operations, etc.).
    * A few important relevant GIT commits (but there are many more):
        * `3/9/21: edited Node, added Forward and Right Commands`
        * `3/10/21: added some basic turtle commands`
        * `3/12/21: refactored command and changing to protected access`
    * The command structure is designed well, as commands can act upon turtles, and commands can
      link up in such a way that the command tree collapses as intended. Additional duplication was
      removed between `Command` implementations by splitting the method into `execute` and
      `executeCommand`, which allowed certain steps (in this case, an assertion statement for the
      correct number of parameters) to always be run before any command is executed. This was
      further reinforced by setting permissions correctly, so that `executeCommand` could only be
      called by the superclass, since subclasses only have access to the public `execute` method of
      other commands.

2. I did interfacing between model and view, especially dealing with the `Coordinates` abstract
   class. `Coordinates` was one of the interfaces we brainstormed during the planning phase, as we
   wanted to consider multiple coordinate systems, like polar coordinates. `Turtle` in the model
   and `TurtleView` in the view both rely on `Coordinates` to compute and display the location and
   heading of the turtle properly, with various getters and setters for x, y, and heading between
   them.
    * Some GIT commits:
        * `3/19/21: removed existing coordinates listeners and does not work`
        * `3/20/21: changed implementation to string, works for forward and right`
    * While the `Coordinates` interface is functional, it could be improved with some of the
      suggestions I've already listed above. One thing that I'd want to mention specifically is that
      in the current system, because the view has access to the `Coordinates` object itself, the
      view has the ability to set the position and heading however it pleases, which should not be
      possible. It would be much better to split `Coordinates` somehow in order to make one class be
      the property binding and possibly make the other class be immutable.

### Conclusion

I've been relatively successful at making my code as abstract and flexible as possible. The
flexibility of the commands is something I'm quite proud of, and I'm so happy so see that it works.
My specific initiative of having both `execute` and `executeCommand` was great to see that it worked
and also made my code simpler.

While I had some successes with commands, my team went through a few iterations to get that right.
At first we had an even more abstract superclass, `Node`. We also had `Operation` in addition
to `Command`, but all of these got confusing because each one didn't have a tailored purpose, so we
decided to just go with `Command`. There exists a point where you can have too much abstraction and
any additional abstraction becomes extraneous, so this is something to be aware of.

## Alternate Designs

### Effect of multiple turtles

Almost all of the `TurtleCanvas` and elements dealing with that part of the view were changed since
the assumption was that only one turtle could exist at a time. We also had a system of property
change listeners that were tied to the view, and new turtles would implement these property change
events in order to communicate data values. I had to change this to property binding because it
would have been way more difficult otherwise.

We had thought of the n-turtles extension when doing our plan, but we drifted away from that idea
during our basic implementation because it would have been unnecessarily difficult for us at the
time. We reintroduced the idea of `TurtleGeneral`, that which commands all of the turtles (or
rather, every `TurtleController`). This `TurtleGeneral` would control which turtles were active and
would run commands only on those that are active.

### Effectiveness of original design to extensions

I think the vast majority of our project held up well when it was time to do extensions. I think the
biggest two things for us were how we wanted to deal with multiple turtles, and how to be able to
use the palette in model while still being an essential view element. Commands didn't change much,
other than the addition of `GlobalProperties` as an input to execute the command.

### One major assumption/choice

Again, by assuming that we only needed to implement one turtle, we had to change elements dealing
with an exclusive turtle being displayed and acted upon.

### Two design decisions

One design decision that my team considered was how to set up the system of Nodes, Commands, and
Operations. More specifically, it involved how we wanted to set up the `execute` method, with or
without a `Turtle` parameter. While the commands require the turtle to execute, the operations do
not. One option was to separate out the implementation to execute commands or operations, and the
other was to unify the two into a single `execute` method contained in the abstract Node superclass
and deprecate the Operation class altogether. We decided to go with the latter option because the
unified `execute` method was more convenient for executing any type of command, whether it be on the
turtle or not. It does have the downside that operations have access to the turtle when it is not
needed, but in theory, operations have the same backend permissions as commands anyway.

Another design decision my team considered was how to get commands run on the turtle in the first
place. The two basic considerations were either the turtle owning all of the commands, or the
commands owning the turtle. While we didn't exactly go with either of these, we went more toward the
latter, giving the responsibility of the running of the command to the command itself, as we wanted
to avoid having the turtle class be bloated by more and more functions. We have greater
extensibility by giving the command responsibility of running because this allows more commands to
be added without having to modify the turtle class on which many other classes and functions already
depend. It is quite easy to add new commands, as it only requires adding a new element to the
resource property file and implementing a new class extending `Command`, of which only `numParams`
needs to be set and `execute` needs to be implemented. Since the vast majority of commands on the
turtle can be implemented with only a few methods within `Turtle`, there is no need for each command
to have its own method within `Turtle`.

### Conclusion

There's always a trade-off between functionality and good encapsulation/abstraction, and the team
must decide how much is too much as an executive decision, since we're not trying to make
indefinitely extensive programs. There are also dependencies to consider: is this dependency carried
through many lines of code? If so, then there could be great risk of big change to the overall
design later on when specifications change and new features need to be implemented.

There is a convenience factor to having less encapsulation, but not all methods should be exposed to
the outside, for fear of malicious misuse by adversaries or simply accidental misuse by teammates. I
think my use of `execute` and `executeCommand` definitely fell into the latter: it could be
confusing that two methods that seem to do the same thing exist, so removing access to
`executeCommand` from other subclasses was great in clarifying that.

## Conclusions

### Learned about design from reading teammates' code

See above. Again, I reiterate that code broken into smaller pieces is easier to understand, along
with proper documentation that explains the purpose of the code. High attention to detail is also
important for keeping up code quality, since missing comments, weird formatting, or other quirks
like unnecessary logic or duplicated code can be a sign of carelessness.

### How was time most spent

I probably spent the majority of my coding time on the commands, as I was heavily behind the design
of that as well. I was surprised by how much of my time was spent in discussion though , since there
was almost never an easy solution to any of our problems, and we could only come up with a solution
that utilized good design by discussing our thoughts and bouncing ideas off of each other.

### Perspective changes

Design, especially complex design, is best done in a group setting where everybody can participate,
generate ideas, and ask questions. Understanding is important from all sides, and asking questions
is the best way to ensure that.

### To be a better designer

Start doing differently: Be more vigilant and proactive in encapsulation, for example, in the use
of `TurtleProperties`

Keep doing the same: Looking out for refactoring, quirks in code with formatting/logic/etc.; keep
asking questions and seeking to understand all aspects of design

Stop doing: Taking a back seat to others' issues