package com.bereguliak.ui.frames;

import com.bereguliak.generator.utility.annotations.LateInit;
import com.bereguliak.processor.model.entity.net.SearchData;
import com.bereguliak.ui.controllers.main.MainController;
import com.bereguliak.ui.controllers.main.MainControllerApi;
import com.bereguliak.ui.core.BaseFrame;

import javax.swing.*;

public class MainFrame extends BaseFrame {

    private static final int FRAME_HEIGHT = 450;
    private static final int FRAME_WIDTH = 600;

    @LateInit
    private JPanel contentPanel;

    @LateInit
    private JButton buttonStartProcess;

    @LateInit
    private JTextArea textAreaResultText;

    @LateInit
    private JTextArea textAreaSourceText;

    @LateInit
    private MainControllerApi mainController;

    public MainFrame() {
        initUiComponents();
        initButtonListener();
        initMainController();
    }

    //region Utility API
    private void initMainController() {
        mainController = new MainController((theses, question, searchData) -> {
            StringBuilder stringBuilder = new StringBuilder(theses.getTitle() +
                    "\n" +
                    theses.getTheses() +
                    "\n\n" +
                    question.getTitle() +
                    "\n" +
                    question.getText() +
                    "\n\n");

            for (SearchData search : searchData) {
                stringBuilder.append(search.getName())
                        .append("\n")
                        .append(search.getTitle())
                        .append("\n")
                        .append(search.getUrl())
                        .append("\n\n");
            }

            textAreaResultText.setText(stringBuilder.toString());
        });
    }

    private void initUiComponents() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(contentPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initButtonListener() {
        buttonStartProcess.addActionListener(e -> {
            String sourceText = textAreaSourceText.getText();
            if (sourceText.isEmpty()) {
                showErrorDialog("Введіть текст для опрацювання!!!");
            } else {
                mainController.startGenerator(sourceText);
            }
        });
    }
    //endregion
}
