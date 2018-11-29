package com.bereguliak.ui;

import com.bereguliak.ui.frames.MainFrame;

import javax.swing.*;

public class GeneratorUiRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
