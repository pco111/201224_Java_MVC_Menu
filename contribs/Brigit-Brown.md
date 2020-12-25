# Contributions 

## Implementation: Area 1 
Branch Name:  problem3_Implementation_Brigit

Merge Requests: !11, !12, !13

Main Classes Modified: Combo, MenuItem, Menu

### Design Choices: 
1. Implemented SINGLETON design pattern for Menu 
   * Benefits of Design: dp ensures that we can have only one instance of a Menu object at any point in the execution of the code 
   * This is beneficial because the Menu object manages a large amount of the information for our program and we should only have one being used at a time
2. Implemented FLYWEIGHT design pattern for MenuItem & Combo 
   * Benefits of Design: prevents the client code from creating multiple objects with the same identifier, defined the identifier as the object name
   * This combined with the contains() check in Menu ensures object Uniqueness within the Menu (by our definition of having no two object of the same type with the same name)
   * This means that the client code cannot create two MenuItems or Combos with the same name. If they try to create an item with a pre-existing key, the original item will be returned. 
   * Added an access method with just the name parameter that will return the pre-existing object if it exists 
   * With my implementation, Sizeable objects were created using the flyweight access method getMenuItem. The design of Sizeable required Menu to be protected (because Sizeable had to call super()). In the updated implementation, Sizeable objects are decorators and no longer extend MenuItem. This means that MenuItem can be private but that the Sizeable object names aren't included in the flyweight objects. 
   * Consideration - this flyweight design does not prevent the client from creating a Combo & regular MenuItem with the same name
3. Updates to Combo Class Implementation 
    * Added methods to add & remove items from the Combo 
    * Enhanced how the client code can set the price of the Combo - created an option to set a % discount or set the price manually, removed discount % from constructor 
4. Updates to MenuItem & Combo Classes
    * Fixed Diet representation so that only diets that are present in all objects are included 
    * Simplified the description method 
    * Overrode the equals() method to use the names of objects as their unique identifiers. This was used to prevent duplicate items from being added to Menu (before implementation of flyweight - now the contains() works with flyweight to check)

Related Comments (Discussion): https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/3#note_44800

## Review: Area 2
Issue Number:  #7

### Key Points Mentioned in Review: 
1. Implementation of Sizeable Item Extending MenuItem 
    * Benefits: uses inheritance to prevent the unnecessary replication of code (inherits properties of MenuItem), strong choice of Enums for sizes 
    * Downsides: Redundant for Sizeable to hold a general MenuItem object and then 3 Sizeable objects, implementation does not allow for Sizeable Combo objects, Sizeable is not flexible (cannot be added or removed from a pre-existing object)
    * Suggested Solution: Implement Sizeable using the DECORATOR design pattern, design consideration: discussed if we needed a distinct object for each size (which we did) and suggested that if we did we should implement a different decorator for each size, brought up the question of how we connect the 3 decorated objects if it is okay that they are just connected through decorating the same Item 
2. Implementation of Special using AbstractItem 
    * Benefits: The special discount field not impact the price field in MenuItem and therefore seperates the functionality from the implementation
    * Downsides: Class is redundant because not all Items are going to have a special, should have a way to add & remove special features from an Item 
    * Suggested Solution: Implement Special using the DECORATOR design pattern, benefits: allows an object to add/remove/modify a special for a pre-existing object, items decorated with Special will be treated the same as all other implementations of the Item interface, removes the requirement for a redundant abstractItem class  
3. Combo Implementation
    * Strong Design decision to use the COMPOSITE design pattern for Combo. Benefits: allow us to combine components (menu Items) and treat the new object the same as a regular Item (since they both implement the Item interface) & the implementation of Combo does not affect its component objects 
    * Suggested Improvements: allowing more than 2 items in a Combo, modifying the price implementation to give the client flexibility in choosing the price or discount 
4. Provided a Class Diagram for my Suggested Solution 


Related Discussions: #4 
Comments: 
1. https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_44355
2. https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_44392
3. https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_44467
4. https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_44726
5. https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_45693
6. https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/4#note_45825 

## Review: Area 1 (Focus on Filtering)
Discussion: #3
Comment: https://gitlab.cs.mcgill.ca/mnassif/303a7t9/-/issues/3#note_45769

### Key Points Mentioned in Review: 
1. Smart design decision to use Streams over Collections 
    * Use of the observer design pattern removes the requirement to store the filtered objects in a collection, makes more sense for the filtered objects to be represented in a flow of data 
    * Streams can take other streams as input - this makes it simple to combine multiple filters 
    * Streams directly support the filter() function as an intermediate operation in a datapipeline of stream --> stream, use of this function simplifies our design 
2. Great approach to implement the filtering functions as factory methods of the interface ItemListFiler 
    * Lambda functions prevent duplicated code because they use parameters  
3. Compared current implementation to a more standard appraoch of using method references
    * Described implementation and outlined reasons why it wouldn't work well for our problem
    * Reason 1: method references require us to implement the filtering function without parameters, we have many fields that we want to filter and so this would lead to duplicated code 
    * Reason 2: methods would have to be defined in the class we are filtering, this would make it hard to combine the filter results of MenuItem and Combo

## Tester: Area 3
Branch Name: Area_3_Updated_Tests_Brigit

Merge Requests: !3

Tests Implemented: JUnit Tests
1. Add/Remove MenuItem configuration change test for each panel (LEFT, CENTRE, RIGHT)
2. In each test, tested that multiple elements were each in the correct location in the list. The previous test just looked at the first element
3. Tests for changing the configuration order in each panel. Included assert statements that tested if the order of other panels were modified.

Additional Tests Implemented: 
1. Area_1_Tests: Test if items with the same name can be added to the menu

 