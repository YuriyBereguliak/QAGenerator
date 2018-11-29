package com.bereguliak.ui.frames;

import com.bereguliak.generator.utility.annotations.LateInit;
import com.bereguliak.ui.controllers.main.MainController;
import com.bereguliak.ui.controllers.main.MainControllerApi;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int FRAME_HEIGHT = 450;
    private static final int FRAME_WIDTH = 600;

    @LateInit
    private JPanel contentPanel;

    @LateInit
    private JTextField textFieldSourceText;

    @LateInit
    private JTextField textFieldResultText;

    @LateInit
    private JButton buttonStartProcess;

    @LateInit
    private MainControllerApi mainController;

    public MainFrame() {
        initUiComponents();
        initButtonListener();
        initMainController();
    }

    //region Utility API
    private void initMainController() {
        mainController = new MainController();
    }

    private void initUiComponents() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(contentPanel);
    }

    private void initButtonListener() {
        buttonStartProcess.addActionListener(e -> {
            String sourceText = textFieldSourceText.getText();
            if (sourceText.isEmpty()) {

            } else {
                mainController.startGenerator(sourceText);
            }
        });
    }
    //endregion
}
