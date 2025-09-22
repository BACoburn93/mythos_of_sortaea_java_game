package model.navigation;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Node {
    private String description;
    private List<String> options;  // Player choice options
    private Map<Integer, Node> nextNodes;  // Mapping from choice index to next Node

    public Node(String description, List<String> options) {
        this.description = description;
        this.options = options;
        this.nextNodes = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public List<String> getOptions() {
        return options;
    }

    public void addNextNode(int optionIndex, Node nextNode) {
        nextNodes.put(optionIndex, nextNode);
    }

    public Node getNextNode(int optionIndex) {
        return nextNodes.get(optionIndex);
    }
}

