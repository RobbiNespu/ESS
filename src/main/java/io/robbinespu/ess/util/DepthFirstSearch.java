/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.util.DepthFirstSearch
 * Last modified:  5/28/21, 2:15 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DepthFirstSearch {
  public Map<String, ArrayList<String>> map;

  public void SearchDFS() {
    map = new HashMap<String, ArrayList<String>>();
    initialize();
  }

  public void initialize() {
    String csvFile = "./data.txt";
    BufferedReader bf = null;
    String line = "";
    String by = ",";

    try {
      String[] node_info;

      bf = new BufferedReader(new FileReader(csvFile));
      while ((line = bf.readLine()) != null) {

        if (line.length() == 0) continue;

        node_info = line.split(by);
        link(node_info[0].trim(), node_info[1].trim());
      }

    } catch (FileNotFoundException e) {
      System.err.println(System.getProperty("user.dir"));
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void link(String src, String dest) {
    insert(src);
    insert(dest);
    map.get(src).add(dest);
    map.get(dest).add(src);
  }

  public void insert(String node) {
    if (map.containsKey(node) != true) {
      ArrayList<String> list = new ArrayList<String>();
      map.put(node, list);
    }
  }

  @SuppressWarnings("unchecked")
  public ArrayList<String> getNeighbours(String node) {

    ArrayList<String> neighbours = map.get(node);

    Collections.sort(neighbours);
    // System.out.println(node+" neighbors: " + neighbours); // uncomment to see node neighboor
    return (ArrayList<String>) neighbours.clone();
  }

  public HashMap<String, ArrayList<String>> dfs(String src, String dest) {

    ArrayList<String> path = new ArrayList<String>();
    ArrayList<String> visited = new ArrayList<String>();
    HashMap<String, ArrayList<String>> h = new HashMap<String, ArrayList<String>>();
    Stack<List<String>> s = new Stack<List<String>>();

    path.add(src);
    s.push(path);

    while (true) {
      // System.out.println(path); // uncomment to see full travel edge
      if (s.size() == 0) {
        h.put("Visited", visited);
        h.put("Path", null);
        return h;
      }

      path = (ArrayList) s.pop();
      String last = path.get(path.size() - 1);

      if (last.equals(dest)) {
        h.put("Visited", visited);
        h.put("Path", path);
        return h;
      }

      if (visited.indexOf(last) == -1) visited.add(last);

      ArrayList<String> neighbours = getNeighbours(last);

      for (int i = neighbours.size() - 1; i >= 0; i--) {
        String n = neighbours.get(i);
        if (visited.indexOf(n) == -1) {
          List<String> newpath = (ArrayList) path.clone();
          newpath.add(n);
          s.add(newpath);
        }
      }
    }
  }

  public void print(HashMap<String, ArrayList<String>> h) {
    ArrayList<String> path = h.get("Path");

    if (path == null) return;

    System.out.println("\n1. path are expanded in the following order:\n" + h.get("Visited"));
    // Above statement does not print destination node since it is only explored and not expanded.

    System.out.println("\n2. Total number of node expanded : " + h.get("Visited").size());

    System.out.println(
        "\n3. The path to reach " + path.get(0) + " from " + path.get(path.size() - 1) + " is :");

    for (int i = 0; i < path.size(); i++) {
      System.out.print(path.get(i));
      if (i != path.size() - 1) System.out.print(" -> ");
    }

    System.out.println("\n\n4. " + path.get(path.size() - 1) + " slot to use are :");
    for (String string : this.getNeighbours(path.get(path.size() - 1))) {
      if (string.startsWith("SL")) {
        System.out.println(string);
      }
    }
  }
}
