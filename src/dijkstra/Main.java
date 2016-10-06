/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.ArrayList;
import java.util.List;
import SortingHandler.SortableData;
import SortingHandler.SortingHandler;

/**
 *
 * @author Huang
 */
public class Main {

    public static Interface mainMenuInterface;
    public static InterfaceOption getPathOption;
    public static SortingHandler sortingHandler;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        loadNodes();
        sortingHandler = new SortingHandler();
        mainMenuInterface = new Interface("Main Menu");
        getPathOption = new InterfaceOption("Get Path", new Runnable() {
            @Override
            public void run() {
                getPath();
            }
        });
        mainMenuInterface.addHeader("Welcome to Dijkstra shortest path assignment by Huang Ching");
        mainMenuInterface.addOption(getPathOption);
        openMainMenu();
    }

    public static void loadNodes() {
        List<String> lines = MyReader.getLines("nodes.txt");
        for (String line : lines) {
            Node node = new Node(line);
            Node.nodes.add(node);
            Debug.LogInfo(node + " has been loaded!");
        }
    }

    public static void getPath() {
        Node startNode = Debug.getFromListWithID(Node.nodes, "Please select youe start point", "Start Node");
        Node endNode = Debug.getFromListWithID(Node.nodes, "Please select youe termination point", "End Node");
        while (endNode == startNode) {
            endNode = Debug.getFromListWithID(Node.nodes, "Your termination point cannot be the same as the start! try again.", "End Node");
        }
        List<SortableData> list = new ArrayList<SortableData>();
        startNode.minValue = 0;
        list.add(startNode);
        SortableData[] l = sortingHandler.sort(list);
        while (l.length > 0) { //V
            Node node = (Node) l[0];
            list.remove(node);
            for (Node linkedNode : node.getLinkedNodes()) { // E
                int cost = node.getCost(linkedNode) + node.minValue;
                if (cost < linkedNode.minValue) {
                    list.remove(linkedNode);
                    linkedNode.minValue = cost;
                    linkedNode.parent = node;
                    list.add(linkedNode);
                }
            }
            l = sortingHandler.sort(list);  // V * E log E
        }
        Debug.LogInfo("Best rout from " + startNode + " to " + endNode + " :");
        Node n = endNode.parent;
        String str = n + " ---(" + n.getCost(endNode) + ")---> " + endNode;
        while (n != startNode && n != null) {
            Node last = n;
            n = n.parent;
            str = n + " ---(" + n.getCost(last) + ")---> " + str;
        }
        Debug.LogInfo(str);
        Debug.LogInfo("Distance: " + endNode.minValue + " Km");
        pauseAndGoMainMenu();
    }

    public static void openMainMenu() {
        mainMenuInterface.showAndGetOption().run();
    }

    public static void pauseAndGoMainMenu() {
        Debug.getString("Enter any key to go to main menu.");
        openMainMenu();
    }
}
