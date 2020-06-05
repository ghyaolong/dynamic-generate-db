package com.yao.dynamicgeneratedb.watch;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: 事件对象
 * @date 2020/6/5 10:10
 */

public class CreateDataSourceEvent extends Event{

    private String eventName;


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CreateDataSourceEvent(Object source,String eventName) {
        super(source);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
