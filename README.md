# eatingismylife
My Build Your Own Game (BYOG) project from CS61B. I built this project with my partner Wonjun during sophomore year. The objective of the game is to eat various "fruits" inside a cave. Each type of fruit gives you some unique perk, such as increasing your field of vision, giving your more steps, or letting you remove walls. You lose once you run out of steps, and this is an amount that the player can set. I implemented core game features, such as the random world generation, field of view, and features like teleportation. I also rewrote/reorganized the game code, splitting up the various features and roles into the classes and interfaces that currently exist. 

### NewWorld & Rooms
The _NewWorld_ class is a reiteration of the older _World_ class. It serves as the superstructure of individual rooms, and as a mediator between rooms and the visualizer object. The _Room_ class serves as the model for a room, storing basic information for rendering such as its dimensions, the special tile it holds, and the various rooms its linked to.

#### Random World Generation
The random world generation process begins with creating the rooms, which begins the _NewWorld_ class generating room specifications. The main method that accomplishes this is the generateRooms() class, which repeatedly generates different specifications for rooms until a valid room specification has been generated. To clarify, a valid room is simply one that doesn't overlap with others.

The next process is connecting rooms together, and this is done by checking the distance between the the _hallnode_ of a room. The _hallnode_ is essentially the main feature of a room, and it's usually some special tile that the user can consume, or a portal they can use. We add each nearest neighbor to the neighbors array, until there are no neighbors left that meet the maximum distance, or we've reached the quota of allowed neighbors.

Finally, the visualization process occurs by feeding this information to the visualizer. Rooms are drawn in a manner similar to indexing through a 2D-array and editing its value, while hallways are drawn using a pathfinding algorithm that creates a "taxicab" path to each neighbor. The resulting worlds tend to have large, spacious rooms, and snakey hallways that sometimes converge to create intersections, and larger hallways.

#### Field of Vision
The illusion of a field of vision is by using a second copy of the board where all the tiles are replaced with empty tiles. This black board is used as the primary view, and we transcribe the player's information, as well as surrounding tiles within their field of view, over onto the blackboard. This display is then passed over to the visualizer to render.

#### Teleportation
One of the tiles in the game is the portal block, which allows the user to travel (uncertainly) to another portal. Each portal is linked solely to another one, and the expectation is moving into one portal will randomly place you around the other. Portals are linked to one another during the random world generation phase. When the player moves into the portal, the game will then look for a clear spot around the portal to randomly drop the player in. 

