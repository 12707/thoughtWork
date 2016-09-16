package com.thoughtWork.trains.exception;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class NotMatchExactStopsNumberException extends RuntimeException {
    public NotMatchExactStopsNumberException(){}

    public NotMatchExactStopsNumberException(String msg){
        super(msg);
    }
}
