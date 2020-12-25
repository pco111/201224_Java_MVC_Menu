Here is a brief description of my roles in Activity7:

1. **Implementor: Areas 1, 2, 3, 4**


    **a)** Initial area 2 implementation:

 - Strived to provide a flexible solution for related objects with decoupled object behavior and inheritance by incorporating  strategy, composite and decorator design patterns
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_39500
7522d261f1a8ff2167872fdd93613fe966f3030c

    **b)** Updated area 1 & area 2 solution:

- To simplify code in MenuPanel by incorporating streams and remove unnecessary local data storage
9666e811397c12edc356403dee17500ab233ca27

- To implement Abstract class that removes unnecessary multiple declarations of interface methods
9cc6e3256db6197aa0ac60b74f2741415529d5e5

- To allow Sizeable to be a composite of 3 decorated Item objects that are created at once
5e21a4fc224962ff7d73a377917e827fd78d8e16

- Implement second Abstract class, resolve minor bugs throughout
86396f4cd9a15a3b1c416fbabafac00d65e4876e

    **c)** Edited full solution:

- To improve syntax by changing method names/variables to be consistent, concise, and clear (all)

- To enable functional-style data processing by changing lists to streams where appropriate (all)

- To improve readability by applying high order functions to stream, hence reducing code

- To improve flexibility by adding alternative display option for client (area 3)

- To meet activity requirements by adding third and fourth config options (area 4)
c9d67b105991fbaa3b81e8c5d95934e86d8106d3


**2. Reviewer: Areas 1, 2, 3, 4**

    **a)** Area 1:

- Provided suggestions to improve code syntax and clarify developer purpose
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/3#note_43184

- Recognized errors in addItem() and provided explanation
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/3#note_45430

    **b)** Area 2:

- Compared alternative solution to my implementation and provided suggestions to meet the requirements
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_41607

- Provided alternative solution for constructor and setDiets() to simplify code
2807fc3a64caac8c18943adc468972e8f98434ae

- Provided alternative solution for Sizeable so it composes decorated objects
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_47000

    **c)** Area 3:

- Suggested 2 method push data-flow strategy between Menu and Observers to avoid creating a new list upon every change

- Suggested incorporating streams into the design to avoid storing data when it doesn't need to be, and improve readability by using high order functions and reducing code, provided implementation as example
9666e811397c12edc356403dee17500ab233ca27
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/2#note_41254
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/2#note_43244

    **d)** All:

- Recognized various errors and provided explanation with proposed solution implementation to improve the design and meet requirements
https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/6#note_43831

**3. Tester : Area 1**

- Carried out several tests on the methods in the Menu class

- Updated tests to get coverage&accuracy on implementation changes
e5f7859f52127de92546992994e0b7767478869f
4800ae1cc7b0e2bdfc1243552a2d71965ec940b2
3d1931dafff38aa888e0d3b6fb1d22c4544d7464

- Updated area1 tests and make minor changes in other tests to be compatible with area2 changes
321be144268a349305db7e2da7f052cc1fff861d


