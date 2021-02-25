# API Lab Discussion
# Collections API Discussion

## Names and NetIDs

Cole Spector (cgs26)

Marc Chmielewski (msc68)

Harrison Huang (hlh38)

David Li (dl303)

### What is the purpose of each interface implemented by LinkedList?
Serializable
LinkedLists can be represented as a sequence of bytes (bytestream), which has networking and I/O implications.
Clonable
Requires that the object implement the clone() method, which implies that it can be copied on a field-for-field basis.
Iterable<E>
Allows the object to be iterated through by a for-each loop
Collection<E>
Means that this holds a group of objects which you can add to, remove from, etc.
Deque<E>
Indicates that the object is a linear data structure that can have additional data pushed onto or popped off of either end of the collection. (Like a deck of playing cards.) The E implies that it is a generic interface and thus is type-agnostic.
Implements addFirst, addLast, pollFirst, pollLast, etc.
List<E>
Represents an ordered collection (i.e. can access elements by a certain index).
Queue<E>
Allows for the use of add() and remove(), to put/remove objects into the data structure at the head.


### What is the purpose of each superclass of HashMap?
java.lang.Object
The universal object from which all other Java Objects are descended.
java.util.AbstractMap<K,V>
Provides a skeletal implementation of the HashMap interface in the form of an abstract class.
Reduces overhead involved in implementing other types of Maps.

### How many different implementations are there for a Set?
Two subinterfaces and nine implementing classes: NavigableSet<E>, SortedSet<E>, and AbstractSet, ConcurrentHashMap.KeySetView, ConcurrentSkipListSet, CopyOnWriteArraySet, EnumSet, HashSet, JobStateReasons, LinkedHashSet, TreeSet

### What methods are common to all collections?

Add, addAll, clear, contains, containsAll, equals, hashCode, isEmpty, iterator, remove, removeAll, retainAll, size, and toArray both with and without parameters, along with all iterable methods.

### What methods are common to all Queues?

Add, offer, remove, poll, element, and peek, along will all of the collection methods.

### What is the purpose of the collection utility classes?
To implement some common behaviors that can be performed upon all Collections such as shuffling, sorting, and frequency analysis.
So long as the object implements all of the Collection methods, it should be designed in such a way as to support these operations.


