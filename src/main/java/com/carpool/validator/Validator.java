package com.carpool.validator;

/**
 * Created by Crawlers on 4/24/2017.
 */
public interface Validator<T> {
    public boolean validate(T t);
}
