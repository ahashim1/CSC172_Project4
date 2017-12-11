Name: James Emery
Class: CSC 172
Project: 4
Email: jemery7@u.rochester.edu
Class ID: 51

Partner Info:
Name: Ali Hashim
Email: ahashim@u.rochester.edu
Class ID: 7

For this project, we use DJ Ikstra's algorithm to find the shortest path between any two connected vertices on a graph.  To test this project, run StreetMap.java using the commands specified in the project sheet.

Runtime Analysis of Shortest Path: O(ElogV)
The runtime of Djikstra's algorithm is O(ElogV) where E is the number of edges and V is the number of vertices.  The important step of assessing the runtime is adding to and removing from the priority queue, which has big-O runtime O(logV).  Each vertex must be added once, so that section of the runtime is O(VlogV).  Each time we take from the queue, we also find each vertex adjacent to the polled vertex, which requires traversing the entire edge list to generate the list map (O(E)).  If we consider the worst case for the adjacency list, which requires each adjacent vertex to be added to the queue (O(logV)), then that part of the runtime isO(Elog(V)).  The total runtime will then be O(VlogV + ElogV), which can be simplified to O(ELogV) because E is assumed to be much greater than V.

Runtime Analysis of Graphing: O(E)
The graph works by getting each edge, which has runtime O(E), finding the coordinates, which has runtime O(1), and then graphing a line between the two coordinate sets, which also has constant runtime.  Therefore, overall graphing has O(E) runtime

Code Structure:
Our graph class contains HashMaps of Vertex and Edge classes.  Graph also handles creating the adjacency list, which is another HashMap where the keys are vertex IDs and the values are arrayLists of the adjacent vertices to the key vertex.  There is a class Helper that is used to find the weight of an edge from the coordinates of its two vertices.  It uses the Haversine formula because we, unlike Kyrie Irving, account for the curvature of the Earth when calculating distances.  

Significant Challenges:
The challenges of using the larger data sets stem from the access time of the lists of vertices and edges.  Originally, we were using ArrayLists of vertices and edges, which have O(n) access time.  On the nys.txt data set, this access time caused drawing the map to take ~20 mintes.  Switching to a HashMap cuts the access time to O(1), improving the mapping time to a few seconds.


File List:
SteetMap.java
Graph.java
Vertex.java
Edge.java
README.txt
ur.txt
monroe.txt
nys.txt

Workload:
Ali: Graph, StreetMap classes, implementing Dijkstra's algorithm (shared)
James: Vertex, Edge classes, runtime analysis, README, Dijkstra (shared)