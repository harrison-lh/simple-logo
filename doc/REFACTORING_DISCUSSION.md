## Refactoring Lab Discussion
### Team 05
### Marc Chmielewski, David Li, Harrison Huang, Cole Spector


### Issues in Current Code

#### Code Duplication and General Smelliness in Turtle
* `left` method should not exist
    * Redundant and generally not a great idea
* `vars` should be moved out to the lexer
    * Keeps all user-defined information centralized in one location.
    * Keeps the code SHY, by only requiring that things are seen by the things that should be seeing them.
    * Reduces cognitive load to understand the code.
* Duplicate listeners/property change handlers
    * Change to variable binding, can be refactored to avoid hard-coding strings for different values needed to be passed

#### Code Duplication in MenuBar Selectors
* Structure is the same (VBox of title and combobox/colorpicker)
* Constructed very similarly
* All implement Selector interface and corresponding methods
* Easy fix is to create an abstract class, although distinguishing between ComboBoxes and ColorPickers might not be so straightforward


### Refactoring Plan

* What are the code's biggest issues?
    * Still need to move a few things around to accomodate some of the new features.
    * Some duplication still needs to be resolved
    * Listeners need to be fixed yet! (IMPORTANT!!!)
    * Need more tests

* Which issues are easy to fix and which are hard?
    * **EASY:**
        * Tests just take time to write but aren't a terrible lift.
        * Duplication can be easily abstracted, either into a parent abstract-class or into a static method.
        * Relocation is EZ.
    * **HARD:**
        * Listeners might get messy
            * Heavily integrated
            * Code glue

* What are good ways to implement the changes "in place"?
    * Duplication can be solved with extract-method fairly trivially.
    * Tests just need to be written.
    * Relocation is literally the opposite of this, but not a horrific lift.
    * Listeners are going to be completely overhauled so they will NOT be done in-place.
        * The interfaces will be preserved, but the implementation will be completely overhauled.

### Refactoring Work

* Turtle Code Duplication: Removed Duplication. No Alt.
    * Pretty much what it says on the tin.
    * Removed unused methods, added JavaDoc, generally beautified the code.
    * Result is much cleaner. No pracitcal alternative.
    * Substituting the way listeners are used will be handled in a more extensive refactoring in the near future.

* MenuBar selectors duplication
    * Created MenuBarSelector abstract class
    * Each selector item extends the abstract class

* Relocation of Variables to Lexer: Wait Until the Listeners are Ready!
    * We've decided to table the relocation of the variables until after we've reimplemented the listeners, because there's going to be a lot of overhead and it'd be a pain in the butt to do it twice.
    * Once this is done though, the lexer will own all of the user-defined information, which makes a lot more sense than fracturing it between the turtles and the lexer.