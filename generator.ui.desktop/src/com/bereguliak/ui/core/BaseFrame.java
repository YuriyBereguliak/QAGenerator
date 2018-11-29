package com.bereguliak.ui.core;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class BaseFrame extends JFrame {

    public void showErrorDialog(@NotNull String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
