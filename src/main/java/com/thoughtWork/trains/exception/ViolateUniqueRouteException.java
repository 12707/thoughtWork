package com.thoughtWork.trains.exception;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class ViolateUniqueRouteException extends RuntimeException {
    public ViolateUniqueRouteException(){}

    public ViolateUniqueRouteException(String msg){
        super(msg);
    }
}
