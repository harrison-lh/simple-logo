SLogo
====

This project implements a development environment that helps users write programs to draw using a turtle.

![](https://i.imgur.com/uW6I99g.png)


Names:
* Marc Chmielewski (msc68)
* Harrison Huang (hlh38)
* David Li (dl303)
* Cole Spector (cgs26)


### Timeline

Start Date:

2/26/2021

Finish Date:

3/21/2021

Hours Spent:

* Marc Chmielewski: > 80 hours
* Harrison Huang: 50 hours
* David Li: > 60 hours
* Cole Spector: > 20 hours per week

### Primary Roles
* Marc Chmielewski:
    * Model/Controller
        * Led development of Lexer/Parser and worked extnesively on the associated middleware to link it all together.
* Harrison Huang:
    * Model/Controller/Commands
        * Interfacing between model and view using property binding
* David Li:
    * View
        * Constructed the entire view, receiving updates from the back-end and updating back-end properties with user interaction
* Cole Spector:
    * Commands

### Resources Used

* Stack Overflow
    * General guidance on parser design and ASTs
* [This Great Medium Tutorial On Writing Parsers Without ANTLR](https://medium.com/swlh/writing-a-parser-getting-started-44ba70bb6cc9)
* Google Images
    * Cool Turtle pictures!
* SLogo Spike
    * Initial Lexer/Parser inspiration
* [Property Binding Documentation](https://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm)

### Running the Program

Main class:
Our Slogo project can be run from our Main.java class.

Data files needed:
The data files needed in order to run our project are the Syntax.properties, and in order to use commands any language file (Chinese.properties, English.properties, French.properties, German.properties, Italian.properties, etc.).  Along with this, turtle-default.png and turtle-realistic.png are needed to display the turtle icon.

All of these are stored in the `resources` directory of our `src` folder.

Features implemented:
Our Slogo project implemental all of the Basic requirements, along with most of the basic extensions. We added all of the additional View Commands. (Change Shape, Pen Size, Color, etc.)  We added a display box for the turtles and their properties, as well as a display for the color palette and turtle shape palette. We also implemented the ability to click-execute commands from the command history and the user-defined commands (although not fully functional for commands with parameters), to click to edit the value of variables, provided a way to move the current turtle graphically, and a way to change the pen's properties graphically.  Additionally, we allowed users to create multiple turtle display areas with different numbers of turtles, different command histories and active variables, different user-defined commands, and different palettes for image colors.  Finally, we added all the additional commands, the ability for users to save and read a file of valid SLogo functions and variables.

For the challenge extensions, we added the ability to do grouping commands.



### Notes/Assumptions

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

Interesting data files/one-liners:

**That Super-Trippy Psychedelic Flower Thingy at the Top of This Doc:**
`TO color_flower [ ] [ repeat 18 [ SETPENCOLOR RANDOM 10 repeat 180 [ fd 3 rt 2 ] rt 20 ] SETBG RANDOM 10 ]`

**Same Thing, Only Even Trippier**
`TO color_flower_trippy [ ] [ repeat 18 [ SETPENCOLOR RANDOM 10 repeat 180 [ fd 3 rt 2 ] rt 20 SETPENSIZE RANDOM 20 ] SETBG RANDOM 10 ]`

Known Bugs:

* Ocassionally `Tell` will get a bit wonky, particularly with large numbers of Turtles. This usually is accompanied by a fairly dramatic slowdown in the entire program, which leads us to believe there's some sort of memory or runtime constraint we're running into.
* `Ask` can encounter unexpected behavior when operating on many turtles.
* Grouping behavior has a heisenbug. (Passes test cases, but ocassionaly breaks on odd numbers of input?)
* Loading and Saving libraries only work for English
* Clicking on user-defined commands with parameters in the commands box throws an error window.

Extra credit:

* We have an interactive color picker that allows the user to select any custom color for the background or for the pen directly in the user interface.
* There is a dropdown box that the user can select whether to display axes lines or not.
* Our UI window can be resized by the user without affecting the overall display functionality.
* Ctrl/CMD + Enter can be used as a shortcut to execute commands immediately instead of having to press the Go button.


### Impressions

Marc Chmielewski (msc68)

Overall I enjoyed this project. As someone who has a lot of interest (and a little bit of experience) in the development of programming languages and compilers, but who had never approached the problem from an Object-Oriented context this was a unique challenge and I definitely learned a ton. OOP is very different from the procedural and functional paradigms I'm more familiar with, so being able to leverage tools like reflection made some things easier (namely Commands) but having to contend with layers upon layers of abstraction certianly made some things harder. (See the beautiful mess that is our state management) With respect to pacing, this was definitely more of a "spurty" project than Cell Society, and there were definitely some moments where I got rather frustrated but we'll discuss that more in the Analysis...

Harrison Huang (hlh38)

I thought SLogo was really interesting in that we had many discussions on how we wanted to approach design, and our structure was constantly being challenged. There are many different ways that we had to collaborate as a team in order to create a design that we all were happy with, and I have to say that I'm happy with the way it turned out. There were two things in particular that I really enjoyed doing: (1) refactoring the commands in such a way that I could both reduce an assertion statement in the class of each new command and set permissions of public/protected such that the assertion statement must always be run by the subclasses, and (2) refactoring the existing listeners mechanism in order to incorporate property binding, which made the extension to n-turtles much easier. Overall, it was cool to see how everything came together, from the user input to the command structure to the updates in the view.

David Li (dl303)

I actually really enjoyed this project. It was my first time primarily focusing on the front-end, and I think I learned a lot about using JavaFX with coloring, resizing, handling user interaction, etc. Figuring out how to link up the view and the model was quite the challenge, especially with the complete extensions. We experimented more with listeners and properties, which was quite the experience. I think the end product looks really clean and user-friendly, and we were able to get most of the functionality done with decently designed code (although not perfect).

Cole Spector (cgs26)

I thought that the SLogo project was very informative in parsing for OOC languages.  I am a firm believer that you don't truly know something unless you know it from (close to) the ground up, and even after taking 250 one part of the programming process that I was still confused about was parsing commands.  Focusing my efforts on the Commands, this project gave first hand experience with just that, clearing a lot up and giving me a better feel for how coding environments like IntelliJ work.  Along with this, working with turtles was one of my first coding projects in high school, so it was cool to see everything come full circle.


[^1]: Marc had a lot of things due this weekend and all of these are things he tried...