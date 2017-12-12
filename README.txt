Name: James Emery
Class: CSC 172
Project: 4
Email: jemery7@u.rochester.edu
Class ID: 51

Partner Info:
Name: Ali Hashim
Email: ahashim@u.rochester.edu
Class ID: 7

For this project, we use Dijkstra's algorithm to find the shortest path between any two connected vertices on a graph.  To test this project, run StreetMap.java using the commands specified in the project sheet.

Runtime Analysis of Shortest Path: O(|E|log|V|)
The runtime of Djikstra's algorithm is O(|E|log|V|) where |E| is the number of edges and |V| is the number of vertices.  The important step of assessing the runtime is adding to and removing from the priority queue, which has big-O runtime O(log|V|).  Each vertex must be added once, so that section of the runtime is O(|V|log|V|).  Each time we take from the queue, we also find each vertex adjacent to the polled vertex, which requires traversing the entire edge list to generate the list map (O(|E|)).  If we consider the worst case for the adjacency list, which requires each adjacent vertex to be added to the queue (O(log|V|)), then that part of the runtime isO(|E|log(|V|)).  The total runtime will then be O(|V|log|V| + |E|log|V|), which can be simplified to O(|E|Log|V|) because |E| is assumed to be much greater than |V|.

Runtime Analysis of Graphing: O(|E|)
The graph works by getting each edge, which has runtime O(|E|), finding the coordinates, which has runtime O(1), and then graphing a line between the two coordinate sets, which also has constant runtime.  Therefore, overall graphing has O(|E|) runtime

Code Structure:
Our graph class contains HashMaps of Vertex and Edge classes.  Graph also handles creating the adjacency list, which is another HashMap where the keys are vertex IDs and the values are arrayLists of the adjacent vertices to the key vertex.  There is a class Helper that is used to find the weight of an edge from the coordinates of its two vertices.  It uses the Haversine formula because we account for the curvature of the Earth when calculating distances.  

Significant Challenges:
The challenges of using the larger data sets stem from the access time of the lists of vertices and edges.  Originally, we were using ArrayLists of vertices and edges, which have O(n) access time.  On the nys.txt data set, this access time caused drawing the map to take ~20 minutes.  Switching to a HashMap cuts the access time to O(1), improving the mapping time to a few seconds.


File List:
SteetMap.java
Graph.java
Vertex.java
Edge.java
Helper.java
README.txt
ur.txt
monroe.txt
nys.txt

How it works:
Vertex.java
	- Implements the comparable interface to compare the current shortest path that it 	took to get to that vertex and another double
	- Contains an id, latitude, and longitude

Edge.java
	- Contains an id, a start vertex, an end vertex, and a weight (the distance 		between two vertices)
Helper.java
	- Contains the Haversine implementation to calculate the distance between to 		vertices by assuming the Earth is a sphere.
Graph.java
	- Contains a HashMap of vertices and edges values stored by id, and iterates over 	the hashmap of edges, to build an adjacency list that is a HashMap with the key of 	the vertex and a list of the adjacent vertices
	- Contains the function shortestPath which uses Dijkstra's algorithm to return the 	shortest path from one vertex id to another. If the start and end are equal, or 	the start or end point aren't found from the vertices, an error message is thrown. 
	- The shortestPath works by adding the start vertex to the priority queue with a 	distance of 0. While the queue is not empty, it fetches the vertex with the 		smallest distance, and if that vertex is the endVertex it breaks. Otherwise, it 	iterates through the smallest vertex's adjacent vertices and updates the weights 	of previous vertices in the queue or if they aren't in the queue yet and then adds 	the vertex back in the queue. The parent of this vertex is set as the min vertex. 	After this while loop, the function iterates from the endVertex until its parent 	is null, adding the vertices to a path list that is returned.
StreetMap.java
	- The driver class to run the program extends JPanel in order to draw the map. 
	- The main function reads in the command line arguments and prints an error if 		there are no command line arguments or if --show or --directions are both not 		typed, or if two points aren't entered after --directions.
	- StreetMap then reads in the input file, adding vertices and edges to their 		respective hash maps. If the user specifies directions, a graph is constructed 		from the vertices and edges and the path is found from the startID and endID 		specified by the user.
	- The path is printed to the console, and the total distance in miles is also 		printed to the console
	- If the user specifies to show the map, the JFrame is set visible. The edges will 	be iterated over and the vertices latitude, longitude, and distance will be 		shifted and scaled to fit onto the window. If the user inputs directions, The 		shortest path will be drawn in red with a stroke width of 5


Workload:
Ali: Graph, StreetMap classes, implementing Dijkstra's algorithm (shared)
James: Vertex, Edge classes, runtime analysis, README, Dijkstra (shared)