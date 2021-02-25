# API Lab Discussion
# Cell Society API Discussion

## Names and NetIDs

Cole Spector (cgs26)

Marc Chmielewski (msc68)

Harrison Huang (hlh38)

David Li (dl303)


### cellsociety_02 Simulation API Motivation/Analogies

#### External
Task failed successfully
CRUD methods were fairly effective, in the sense that they allowed the external packages to create bespoke Cells without access to specific implementation details.
However, many of the public methods didn’t do much *work* they mostly just got Cells from parameter maps
A CellFactory class could have alleviated this!

#### Internal
In general, the internal code was very SHY and willing to tell the other guy. That is, it was designed to supply information to do work rather than just providing the results thereof in inter-class, intra-package communications.


### cellsociety_02 Simulation API Classes/Methods

#### External
Most public-facing methods are getters/setters
This is functional, but something of an anti-pattern, as APIs should tell the process when to do work, as opposed to expecting the work to already be done.
This anti-pattern makes concurrent programming, consensus, etc. a pain in the ass. DON’T DO THIS ON SYSTEMS!!!
SimulationFactory provides a method to make Simulations on demand.
This is the right way to be doing things :)

#### Internal
Abstract Cell allows for extensions to implement different rules and simulations (the abstract method computeNextState)
The CellGrid is populated with many Cells, which have public-facing methods that define an API through which to determine Cell behavior and display characteristics.
Interfaces cleanly with GraphicalCellGrid.

