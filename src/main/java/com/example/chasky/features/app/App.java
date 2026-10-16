package com.example.chasky.features.app;

import com.example.chasky.features.tui.TUI;

public class App {
    
    private TUI tui;

    public App(TUI tui) {
        this.tui = tui;
    }

    public void run() {
        tui.run();
    }

}
