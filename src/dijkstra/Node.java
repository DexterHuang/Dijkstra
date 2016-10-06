/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import SortingHandler.SortableData;

/**
 *
 * @author Huang
 */
public class Node extends SortableData {

    String name;
    HashMap<String, Integer> nodeCost = new HashMap<String, Integer>();
    Node parent;
    int minValue = Integer.MAX_VALUE;

    public static List<Node> nodes = new ArrayList<Node>();

    public static Node getNode(String name) {
        for (Node node : nodes) {
            if (node.name.equalsIgnoreCase(name)) {
                return node;
            }
        }
        return null;
    }

    public Node(String line) {
        String[] l = line.split(",");
        this.name = l[0];
        int index = 0;
        String linkName = null;
        for (String str : l) {
            if (index != 0) {
                if (index % 2 != 0) {
                    linkName = str;
                } else {
                    int cost = Integer.parseInt(str);
                    nodeCost.put(linkName, cost);
                }
            }
            index++;
        }
    }

    Node() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Node> getLinkedNodes() {
        List<Node> nodes = new ArrayList<Node>();
        for (String nodeName : nodeCost.keySet()) {
            Node node = getNode(nodeName);
            if (node != null) {
                nodes.add(node);
            } else {
                Debug.LogError("Cannot find node with name : " + nodeName);
            }
        }
        return nodes;
    }

    public void setCost(String nodeName, int cost) {
        nodeCost.put(nodeName, cost);
    }

    public Integer getCost(Node node) {
        return nodeCost.get(node.name);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node node) {
        parent = node;
    }

    public boolean equals(Node other) {
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int getValue() {
        return minValue;
    }

    @Override
    public String toString() {
        return name + " Station";
    }

    @Override
    public String getID() {
        return name;
    }
}
