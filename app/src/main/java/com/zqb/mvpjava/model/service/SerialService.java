package com.zqb.mvpjava.model.service;

import java.io.FileDescriptor;

public class SerialService {

    static {
        System.loadLibrary("SerialPort");
    }

    private native FileDescriptor open(String path, int baudrate, int flags);

    private native void close();

}
