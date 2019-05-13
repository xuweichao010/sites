package com.xwc.commons.expception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/15  15:57
 * 功能：整个系统处理异常的基类,发现系统中的异常不属于该基类的，需要对异常进行统一处理
 */
@SuppressWarnings("all")
public class BaseExpception extends RuntimeException {

    private String[] description;

    public BaseExpception(Exception e) {
        super(e);
    }

    public BaseExpception() {
        super();
    }

    public BaseExpception(String message, String... description) {
        super(message);
        this.description = description;
    }

    public BaseExpception(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseExpception(Throwable cause) {
        super(cause);
    }

    protected BaseExpception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String convertString() throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String message = sw.toString();
        if (null != sw) sw.close();
        if (null != pw) pw.close();
        return message;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }
}
