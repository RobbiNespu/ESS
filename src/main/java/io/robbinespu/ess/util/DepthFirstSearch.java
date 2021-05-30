/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.util.DepthFirstSearch
 * Last modified:  5/29/21, 9:26 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.util;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepthFirstSearch {
  private static final Logger logger = LoggerFactory.getLogger(DepthFirstSearch.class);

  public DepthFirstSearch() {
    logger.debug("DFS initialize !!!");
  }

  public Map<String, ArrayList<String>> link(
      Map<String, ArrayList<String>> map, String src, String dest) {
    insert(map, src);
    insert(map, dest);
    map.get(src).add(dest);
    map.get(dest).add(src);
    return map;
  }

  public void insert(Map<String, ArrayList<String>> map, String node) {
    logger.debug("DFS insert node = {}", node);
    if (!map.containsKey(node)) {
      ArrayList<String> list = new ArrayList<>();
      map.put(node, list);
    }
  }

  @SuppressWarnings("unchecked")
  public ArrayList<String> getNeighbours(Map<String, ArrayList<String>> map, String node) {
    ArrayList<String> neighbours = map.get(node);
    Collections.sort(neighbours);
    // logger.trace(node + " neighbors: " + neighbours); // uncomment to see node neighboor
    return (ArrayList<String>) neighbours.clone();
  }

  public HashMap<String, ArrayList<String>> dfs(
      Map<String, ArrayList<String>> map, String src, String dest) {
    ArrayList path = new ArrayList<>();
    ArrayList<String> visited = new ArrayList<>();
    HashMap<String, ArrayList<String>> h = new HashMap<>();
    Stack<List<String>> s = new Stack<>();

    path.add(src);
    s.push(path);

    while (true) {
      // logger.trace(path); // uncomment to see full travel edge
      if (s.size() == 0) {
        h.put("Visited", visited);
        h.put("Path", null);
        return h;
      }

      path = (ArrayList) s.pop();
      String last = (String) path.get(path.size() - 1);

      if (last.equals(dest)) {
        h.put("Visited", visited);
        h.put("Path", path);
        return h;
      }

      if (!visited.contains(last)) visited.add(last);

      ArrayList<String> neighbours = getNeighbours(map, last);

      for (int i = neighbours.size() - 1; i >= 0; i--) {
        String n = neighbours.get(i);
        if (!visited.contains(n)) {
          List<String> newpath = (ArrayList<String>) path.clone();
          newpath.add(n);
          s.add(newpath);
        }
      }
    }
  }

  public void print(Map<String, ArrayList<String>> map, HashMap<String, ArrayList<String>> h) {
    ArrayList<String> path = h.get("Path");

    if (path == null) return;

    logger.debug("\n1. path are expanded in the following order:\n" + h.get("Visited"));
    // Above statement does not print destination node since it is only explored and not expanded.
    logger.debug("\n2. Total number of node expanded : " + h.get("Visited").size());
    logger.debug(
        "\n3. The path to reach " + path.get(0) + " from " + path.get(path.size() - 1) + " is :");

    for (int i = 0; i < path.size(); i++) {
      logger.debug(path.get(i));
      if (i != path.size() - 1) System.out.print(" -> ");
    }

    logger.debug("\n\n4. " + path.get(path.size() - 1) + " slot to use are :");
    for (String string : this.getNeighbours(map, path.get(path.size() - 1))) {
      if (string.startsWith("SL")) {
        System.out.println(string);
      }
    }
  }
}
