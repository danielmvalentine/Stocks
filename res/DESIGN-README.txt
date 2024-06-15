Stock Program Design

Created by: Daniel Valentine and Baer Istok

The design of our program very strictly followed the guidelines given by the concept of the Model View Controller design pattern.
We strictly seperated our classes into each category based on their role in the greater program.
For example, our view is where we store the data to hold our welcome text, our menu, and our options, while our controller is where
we held the scanner that dealt with inputs given in response to this menu.

We then further seperated our specific functions into a new package within model. Classes that we counted as functions were ones that
would be used in direct response to user inputs, and all followed an interface given within the functions package. This allowed for easy 
addition of new functions.

Additionally, portfolio functionality is essentially shifted into its own Model View Controller pattern of design within
the greater program. It was set up extremely similarly to the program at large, also having certain functions being in their own package.