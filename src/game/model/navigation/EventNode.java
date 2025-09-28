// Only create this if you need extra event-specific fields or methods
package model.navigation;

import java.util.List;

public class EventNode extends Node {
    private String eventType;
    // Need to add event-specific fields here

    public EventNode(String description, List<String> options, String eventType) {
        super(description, options);
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    // Add in event-specific methods here when ready to work on this
}
