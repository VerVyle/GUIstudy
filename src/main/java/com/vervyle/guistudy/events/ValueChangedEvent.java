package com.vervyle.guistudy.events;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class ValueChangedEvent extends Event {
    public ValueChangedEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public ValueChangedEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }

    public static EventType<ValueChangedEvent> VALUE_INCREASED;
    public static EventType<ValueChangedEvent> VALUE_DECREASED;
}
