# Data Structure & Graph Performance Analyzer

## Project Description
A Java console application for CIT300 Graded Practical Assignment 2. It demonstrates arrays, stacks, queues, linked lists, searching (linear and binary), graphs (adjacency list with BFS and DFS) and a performance/complexity comparison, all integrated into one menu-driven system.

## Team Members

| Student Name | Student ID | Assigned Responsibility |
|---|---|---|
| MR.Rishni Ruzaid | 23DA2-1114 | Array and Searching |
| MI.Amnath Nadha | 23DA2-0905 | Stack and Queue |
| TM.Hakeem | 23DA2-0759 | Linked List and Performance Comparison |
| AK.Sanij Kalees | 23DA2-0683 | Graph, Main Menu, Integration and Testing |

## Individual Contributions

Student Name:MR.Rishni Ruzaid
Student ID:23DA2-1114
Assigned Responsibility: Array and Searching
Individual Contribution:
- Implemented DynamicArray (insert, insert at index, delete, search, display, sort)
- Implemented SearchOps (linear search and binary search with step counting)
- Tested array and searching functionality

Student Name:MI.Amnath Nadha
Student ID:23DA2-0905
Assigned Responsibility: Stack and Queue
Individual Contribution:
- Implemented StackDS (push, pop, peek, display, overflow and underflow handling)
- Implemented QueueDS (circular queue: enqueue, dequeue, peek, display, empty and full handling)
- Tested stack and queue functionality

Student Name:TM.Hakeem
Student ID:23DA2-0759
Assigned Responsibility: Linked List and Performance Comparison
Individual Contribution:
- Implemented LinkedListDS (insert at head, tail and position, delete, search, display)
- Implemented PerformanceAnalyzer and ResultLog
- Tested linked list and performance output

Student Name:AK.Sanij Kalees
Student ID:23DA2-0683
Assigned Responsibility: Graph, Main Menu, Integration and Testing
Individual Contribution:
- Implemented Graph (add vertex, add edge, display, BFS, DFS, shortest path)
- Implemented Main menu system and InputUtil input validation
- Integrated all modules and tested the complete application

## Technologies Used
- Java 17 or newer
- Git and GitHub

## Main System Features
- Array, stack, queue and linked list operations with input validation and empty/full handling
- Linear search and binary search with a side-by-side comparison
- Undirected graph using an adjacency list, with BFS, DFS and shortest path search
- Performance comparison table showing steps, execution time and complexity
- Recorded results viewer

## Project Structure
- src/Main.java: main menu and submenus
- src/InputUtil.java: validated input
- src/DynamicArray.java, StackDS.java, QueueDS.java, LinkedListDS.java: data structures
- src/SearchOps.java: searching algorithms
- src/Graph.java: graph and traversals
- src/PerformanceAnalyzer.java, ResultLog.java: performance and results

## Running the Program
```
javac -d out src/*.java
java -cp out Main
```
