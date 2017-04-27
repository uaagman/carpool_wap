package com.carpool.validator;

import com.carpool.exception.ErrorMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 4/24/2017.
 */
public interface Validator<T> {
    public Map<Boolean, List<ErrorMessage>> validate(T t);
}
