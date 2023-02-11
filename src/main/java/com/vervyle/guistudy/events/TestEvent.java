package com.vervyle.guistudy.events;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class TestEvent extends Event {

    public static final long serialVersionUID = 20129907L;
    public static final EventType<TestEvent> ANY;
    public static final EventType<TestEvent> TEST_EVENT_GOOD;
    public static final EventType<TestEvent> TEST_EVENT_BAD;

    public TestEvent(EventType<? extends TestEvent> eventType) {
        super(eventType);
        System.out.println("TEST EVENT INSTANCE CREATED! TYPE: " + eventType.toString());
    }

    public TestEvent(Object source, EventTarget target, EventType<? extends TestEvent> eventType) {
        super(source, target, eventType);
        System.out.println("TEST EVENT INSTANCE CREATED! TYPE: " + eventType.toString());
    }

    static {
        ANY = new EventType<>(Event.ANY, "TEST");
        TEST_EVENT_GOOD = new EventType<>(ANY, "TEST_EVENT_GOOD");
        TEST_EVENT_BAD = new EventType<>(ANY, "TEST_EVENT_BAD");
    }
}