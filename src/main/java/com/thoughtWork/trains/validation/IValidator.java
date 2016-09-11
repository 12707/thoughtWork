package com.thoughtWork.trains.validation;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public interface IValidator<T> {
	boolean validate(T t);
}
