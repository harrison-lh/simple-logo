## SLogo API Changes
### Team 05
### Marc Chmielewski, David Li, Harrison Huang, Cole Spector


### Changes to the Initial APIs

#### Backend External

* Removed `getCommand()`:

    * Why was the change made?
        * We changed how we were handling commands by making them local to the Turtle in question

    * Major or Minor (how much they affected your team mate's code)
        * It was certainly significant, but since it was a decision made fairly early, it wasn't a breaking change.

    * Better or Worse (and why)
        * Better. It kept our Commands closer to the operative agent, that is the Turtle, and thus made the overall system cleaner.


* Split "Ball of MUD" Controller Into Two More Purposeful Controllers:

    * Why was the change made?
        * Having one big terrible controller was a poor life decision.
        * Having two more specific controllers with a reasonable ownership design was a much better life decision.
        * Pursue continuing education kids :)

    * Major or Minor (how much they affected your team mate's code)
        * Major, but not breaking.
        * Early comms was key to making this a non-breaking change

    * Better or Worse (and why)
        * Better because...obviously
        * MUD BAD!


#### Backend Internal

* `TurtleInfantry` was incorporated into `TurtleController`:

    * Why was the change made?
        * `TurtleInfantry` was redundant, and its functionality could be added to the `TurtleController` without breaking the SRP.

    * Major or Minor (how much they affected your team mate's code)
        * Minor. This was more a consequence of the development of the `TurtleController`.

    * Better or Worse (and why)
        * Better. Cleaner, upholds the SRP, etc.


* Refactored Down the Node Hierarchy:

    * Why was the change made?
        * Node was redundant and was becoming quite unweidly.
            * Lasagna code

    * Major or Minor (how much they affected your team mate's code)
        * Major, in the sense that it required a lot of re-typing. Minor in the sense that it wasn't a horrifically involved change.

    * Better or Worse (and why)
        * Better. Less redundancy, easier to comprehend, just generally less-bad.


#### Frontend External

* Got rid of view interface entirely:

    * Why was the change made?
        * Initally used a bunch of model/controller methods, which added unnecesary redirection and purposeless abstraction.
            * View wasn't doing anything
        * Now using listeners which is more effective, reactive, etc.

    * Major or Minor (how much they affected your team mate's code)
        * ***QUITE MAJOR***

    * Better or Worse (and why)
        * Better because it's not an anti-pattern.
        * Reactive designs are generally faster/lower-overhead, and more responsive.


#### Frontend Internal

* Almost all public methods were made private because of our use of consumers and listeners

    * Why was the change made?
        * Public methods aren't called on objects by other objects, but they are notified when to get called by consumer.accept() or by an action event.

    * Major or Minor (how much they affected your team mate's code)
        * Somewhat minor since what each method does didn't the same, but how they were called did.

    * Better or Worse (and why)
        * Better, because now classes are less dependent on each other in terms of calling each others' public methods, but rather only care about notifying its listeners of changes, and the listeners only care about when and how to update when something is changed.

* moveTurtle() was moved from the TurtleCanvas to the TurtleView

    * Why was the change made?
        * With multiple turtles, it makes more sense for each turtle to move itself rather than the TurtleCanvas to determine which turtles to move and then to move them.

    * Major or Minor (how much they affected your team mate's code)
        * Minor, since in both classes all it does is call setTranslateX and setTranslateY

    * Better or Worse (and why)
        * Better, because gives Turtles independence and makes them responsible for themselves.