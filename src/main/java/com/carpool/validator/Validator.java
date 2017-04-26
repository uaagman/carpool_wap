package com.carpool.validator;

import java.util.Map;

/**
 * Created by Crawlers on 4/24/2017.
 */
public interface Validator<T> {
    public Map<Boolean, String> validate(T t);
}
