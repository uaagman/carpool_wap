package com.carpool.customTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by ashok on 4/26/2017.
 */
public class ShowMessageTag extends SimpleTagSupport {
    String color;
    String text;

    public void setColor(String color) {
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.write(String.format("<span style='color:%s'>%s</span>", color, text));
    }
}
