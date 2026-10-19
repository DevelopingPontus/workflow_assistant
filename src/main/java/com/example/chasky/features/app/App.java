package com.example.chasky.features.app;

import com.example.chasky.features.tui.AppTUI;

public class App {
    
    private AppTUI appTui;

    public App(AppTUI appTui) {
        this.appTui = appTui;
    }

    public void run() {
        appTui.run();
    }

}
