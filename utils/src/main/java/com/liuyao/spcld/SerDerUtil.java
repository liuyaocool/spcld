package com.liuyao.spcld;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerDerUtil {
    static ByteArrayOutputStream out = new ByteArrayOutputStream();

    public synchronized  static byte[] ser(Object msg){
        if (msg instanceof String) {
            return ((String) msg).getBytes();
        }
        if (msg instanceof byte[]) {
            return (byte[]) msg;
        }
        out.reset();
        ObjectOutputStream oout = null;
        byte[] msgBody = null;
        try {
            oout = new ObjectOutputStream(out);
            oout.writeObject(msg);
            msgBody= out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return msgBody;


    }
}
