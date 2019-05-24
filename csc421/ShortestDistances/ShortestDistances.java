package csc403;

import algs31.BinarySearchST;
import algs41.BreadthFirstPaths;
import algs41.Graph;
import stdlib.StdIn;
import stdlib.StdOut;

public class ShortestDistances {
	
	public static void main(String[] args) {
		
		StdIn.fromFile("data/a6cities.txt");
		BinarySearchST<String, Integer> cities = new BinarySearchST<String, Integer>();
		
		String[] citiesArray = StdIn.readAllStrings();
		int n = citiesArray.length;
		for (int i = 0; i < n; i++) {
			cities.put(citiesArray[i], i);	
		}
		
		StdIn.fromFile("data/a6majorcities.txt");
		String[] majorCities = StdIn.readAllStrings();
		
		Graph cityGraph = new Graph(n);
		StdIn.fromFile("data/a6connections.txt");
		
		while (StdIn.hasNextLine()) {
			 String line = StdIn.readLine();
			 String[] connectedCities = line.split("\\s+");
			 cityGraph.addEdge(cities.get(connectedCities[0]), cities.get(connectedCities[1]));	 
		}
		
		StdOut.printf("%20s", "");
		for (String majorCity: majorCities) {
			StdOut.printf("%-12s", majorCity);
		}
		StdOut.println();
		
		
		for(String row : citiesArray) {
			StdOut.printf("%-15s\t", row);
			for(String col: majorCities) {
				BreadthFirstPaths source = new BreadthFirstPaths(cityGraph, cities.get(col));
				int path = source.distTo(cities.get(row));
				StdOut.printf("    %3d     ", path);
			}
			StdOut.println();
		}
	}
}
