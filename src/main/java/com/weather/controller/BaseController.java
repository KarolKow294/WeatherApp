package com.weather.controller;

import com.weather.view.ViewFactory;

abstract public class BaseController {
    protected ViewFactory viewFactory;
    private final String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = "/" + fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
