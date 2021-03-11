package com.liuyao.spcld.userapi.entity;

public class Person {

    String id;
    String name;
    int port;
    String methodName;
    String msg;

    public void addMsg(String m) {
        if (null == m) return;
        if (null == msg) {
            this.msg = m;
        } else {
            this.msg += m;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
