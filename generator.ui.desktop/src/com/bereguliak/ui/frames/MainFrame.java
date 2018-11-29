package com.bereguliak.ui.frames;

import com.bereguliak.generator.utility.annotations.LateInit;
import com.bereguliak.processor.model.entity.Question;
import com.bereguliak.processor.model.entity.Theses;
import com.bereguliak.ui.controllers.main.MainController;
import com.bereguliak.ui.controllers.main.MainControllerApi;
import com.bereguliak.ui.controllers.main.OnMainTextGeneratorResult;
import com.bereguliak.ui.core.BaseFrame;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MainFrame extends BaseFrame {

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
        mainController = new MainController(new OnMainTextGeneratorResult() {
            @Override
            public void onGeneratorResult(@NotNull Theses theses, @NotNull Question question) {
                String stringBuilder = theses.getTitle() +
                        "\n" +
                        theses.getTheses() +
                        "\n\n" +
                        question.getTitle() +
                        question.getText();
                textFieldResultText.setText(stringBuilder);
            }
        });
    }

    private void initUiComponents() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(contentPanel);
    }

    private void initButtonListener() {
        buttonStartProcess.addActionListener(e -> {
            String sourceText = textFieldSourceText.getText();
            if (sourceText.isEmpty()) {
                showErrorDialog("Введіть текст для опрацювання!!!");
            } else {
                mainController.startGenerator(sourceText);
            }
        });
    }
    //endregion
}
