package com.thoughtWork.trains.exception;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class NoSuchRouteException extends RuntimeException {

    public NoSuchRouteException(){}

    public NoSuchRouteException(String msg){
        super(msg);
    }
}
