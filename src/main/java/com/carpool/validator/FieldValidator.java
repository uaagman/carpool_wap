package com.carpool.validator;

import com.carpool.exception.ErrorMessage;
import com.carpool.exception.ErrorSource;
import com.carpool.validator.constraint.*;
import com.carpool.validator.constraint.Number;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FieldValidator<T> implements Validator<T> {
    private static final String CAUSED_MESSAGE = "Caused by invalid field value";

    public boolean validate(T t) {
        List<ErrorMessage> errorMessages = new ArrayList<>();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        boolean result = true;
        Class clazz = t.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                field.setAccessible(true);
                Object obj = null;
                Pattern pattern;
                Matcher matcher;
                try {
                    obj = field.get(t);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (annotation instanceof Mandatory) {
                    if (obj == null) {
                        result = false;
                        errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                            String.format("%s should not be null", field.getName()), CAUSED_MESSAGE));
                    }
                }

                if (annotation instanceof Mandatory) {
                    if (obj != null && obj.toString().isEmpty()) {
                        result = false;
                        errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                            String.format("%s should not be empty", field.getName()), CAUSED_MESSAGE));
                    }
                }

                // Validating Length
                if (annotation instanceof Length) {
                    if (obj != null) {
                        int len = obj.toString().length();
                        Length length = (Length) annotation;
                        if (len < length.min() || len > length.max()) {
                            errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                String.format("%s should be %d to %d characters", field.getName(), length.min(), length.max()),
                                CAUSED_MESSAGE));
                            result = false;
                        }
                    }
                }

                // Email Validation
                if (annotation instanceof Email) {
                    if (obj != null) {
                        if (!obj.toString().isEmpty()) {
                            String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[a-z]{2,3}+)*(\\.[a-z]{2,3})$";
                            pattern = Pattern.compile(EMAIL_REGEX);
                            matcher = pattern.matcher(obj.toString());
                            if (!matcher.matches()) {
                                errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                    String.format("%s is invalid", field.getName()),
                                    CAUSED_MESSAGE));
                                result = false;
                            }
                        }
                    }
                }

                // Url Validation
                if (annotation instanceof Url) {
                    if (obj != null) {
                        if (!obj.toString().isEmpty()) {
                            String WEBSITE_REGEX = "^(http(s{0,1}):\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
                            pattern = Pattern.compile(WEBSITE_REGEX);
                            matcher = pattern.matcher(obj.toString());
                            if (!matcher.matches()) {
                                errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                    String.format("%s url is invalid", field.getName()),
                                    CAUSED_MESSAGE));
                                result = false;
                            }
                        }
                    }
                }

                // Date Format Validation
                if (annotation instanceof DateTime) {
                    if (obj != null) {
                        if (!obj.toString().isEmpty()) {
                            DateTime dateTime = (DateTime) annotation;
                            SimpleDateFormat sdf = new SimpleDateFormat(dateTime.format());
                            sdf.setLenient(false);
                            try {
                                sdf.parse(obj.toString());
                            } catch (ParseException e) {
                                errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                    String.format("%s should be in %s format", field.getName(), dateTime.format()),
                                    CAUSED_MESSAGE));
                                result = false;
                            }
                        }
                    }
                }

                // Number Validation
                if (annotation instanceof Number) {
                    if (obj != null) {
                        if (!obj.toString().isEmpty()) {
                            String NUMBER_REGEX = "[-+]?([0-9]*\\.[0-9]+|[0-9]+)";
                            pattern = Pattern.compile(NUMBER_REGEX);
                            matcher = pattern.matcher(obj.toString());
                            if (!matcher.matches()) {
                                errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                    String.format("%s is not a number", field.getName()),
                                    CAUSED_MESSAGE));
                                result = false;
                            }
                        }
                    }
                }

                // Number Validation
                if (annotation instanceof Password) {
                    if (obj != null) {
                        if (!obj.toString().isEmpty()) {
                            String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d$@$!%*?&]).*$";
                            pattern = Pattern.compile(PASSWORD_REGEX);
                            matcher = pattern.matcher(obj.toString());
                            if (!matcher.matches()) {
                                errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                    String.format("%s is not a valid password", field.getName()),
                                    CAUSED_MESSAGE));
                                result = false;
                            }
                        }
                    }
                }

                // Age Validation
                if (annotation instanceof Age) {
                    if (obj != null) {
                        if (!obj.toString().isEmpty()) {
                            Integer year = LocalDate.now().getYear();
                            if (year - Integer.parseInt(field.getName()) < 18) {
                                errorMessages.add(new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                                    String.format("Age Must be more than 18", field.getName()),
                                    CAUSED_MESSAGE));
                                result = false;
                            }
                        }
                    }
                }
            }

        }
        if (!result) {
            String json = null;
            try {
                json = ow.writeValueAsString(errorMessages);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println("Validation Errors:" + json);
            //return new ValidationHandler(json);
        }
        System.out.println("Validation Result:" + result);
        return result;
    }
}
