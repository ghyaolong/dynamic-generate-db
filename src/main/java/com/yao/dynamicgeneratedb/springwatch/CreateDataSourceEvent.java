package com.yao.dynamicgeneratedb.springwatch;

import com.yao.dynamicgeneratedb.model.DBModel;
import org.springframework.context.ApplicationEvent;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 13:49
 */
public class CreateDataSourceEvent extends ApplicationEvent {

    private DBModel dbModel;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CreateDataSourceEvent(Object source, DBModel dbModel) {
        super(source);
        this.dbModel = dbModel;
    }

    public DBModel getDbModel() {
        return dbModel;
    }
}
