package com.thoughtWork.trains.exception;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class SameStartAndEndPointException extends RuntimeException {
    public SameStartAndEndPointException(){}

    public SameStartAndEndPointException(String msg){
        super(msg);
    }
}
