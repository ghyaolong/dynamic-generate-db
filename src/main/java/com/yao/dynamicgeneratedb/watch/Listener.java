package com.yao.dynamicgeneratedb.watch;


/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 10:59
 */
public interface Listener /*extends java.util.EventListener*/ {
    void fireAfterEventInvoked(Event event);
}
