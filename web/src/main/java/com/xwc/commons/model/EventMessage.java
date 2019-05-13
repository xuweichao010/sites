package com.xwc.commons.model;

import com.alibaba.fastjson.JSONObject;
import com.xwc.config.web.EventType;

/**
 * 创建人：徐卫超
 * 时间: 2018/5/29
 * 功能：JSON 响应标准体
 * 描述：
 */

public class EventMessage {


    private Object data;
    private EventType type;


    public static EventMessage create(EventType type, Object data) {
        EventMessage e = new EventMessage();
        e.type = type;
        e.data = data;
        return e;

    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
