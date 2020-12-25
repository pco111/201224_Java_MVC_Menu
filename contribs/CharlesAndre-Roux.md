#### Implementation: Area 2

See issue [#4](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4), merge request [!10 (merged)](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/merge_requests/10), merge request [!4 (closed)](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/merge_requests/4), and the following branches: [are2_2](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/tree/are2_2), [charles](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/tree/charles), [area2_charles](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/tree/area2_charles)


- Initially implemented an abstract class called AbstractItem, which would be the super class of MenuItem, as well as Combo (this class was later removed from the master since new design choices rendered it pretty useless)
- Implemented the Combo class, which uses the Composite DP and overrides the `price()` method in the abstract super class
- Implemented the SizeableItem class by having it be a subclass of MenuItem. This class created three MenuItems with modified prices due to their size (small, medium, large) and overriden the AbstractItem's `name()` class (see below)
- Implemented the description feature in the AbstractItem class by calling the `name()`, `info()`, and `price()` methods
- Initially implemented the Special Discount feature by making it a field in the AbstractClass, thus making it available to MenuItems and Combos alike (this was later replaced by the use of the Decorator DP to handle Special Discounts, which I implemented in the most recent "charles" branch)

#### Review: Area 1/Area 2

See issues [#4](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4) and [#3](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/3), merge request [!4 (closed)](https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/merge_requests/4)

##### Area 1

- Suggested the use of enums to represent item categories and dietary categories instead of making them classes of their own (also implemented it in !4)
- Suggested and implemented that Item implement Comparable<Item>
- Presented in !4 a combination of solutions for Area 1 and 2, which had conflicts
- Modified the menu sorting methods to be compatible with the AbstractItem class

##### Area 2

- Extensively discussed the different possibilities for the implementation of the requirements (#4), and provided code of my ideas in multiple comments

#### Testing: Area 1/Area 2

- Modified existing tests in Area 1 to be compatible with the AbstractItem class
- Made tests for Area 2 in the are2_2 and charles branches for my implementation of the Combo, SizeableItem, and SpecialItem classes
