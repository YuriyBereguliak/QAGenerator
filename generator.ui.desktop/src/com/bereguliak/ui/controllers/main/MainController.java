package com.bereguliak.ui.controllers.main;

import com.bereguliak.configuration.processor.ProcessorConfig;
import com.bereguliak.generator.utility.annotations.LateInit;
import com.bereguliak.processor.generator.TextGeneration;
import com.bereguliak.processor.model.entity.DataChain;
import com.bereguliak.processor.model.listeners.OnTextGeneratorResult;
import org.jetbrains.annotations.NotNull;

import static com.bereguliak.configuration.DefaultTrainPathsExtensionKt.*;

public class MainController implements MainControllerApi, OnTextGeneratorResult {

    @NotNull
    private TextGeneration mTextGeneration;

    @LateInit
    private ProcessorConfig mProcessorConfig;

    public MainController() {
        mTextGeneration = new TextGeneration(this);
        initConfigBuilder();
    }

    //region MainControllerApi
    @Override
    public void startGenerator(@NotNull String sourceText) {
        mTextGeneration.runTextGenerator(sourceText, mProcessorConfig);
    }
    //endregion

    //region OnTextGeneratorResult
    @Override
    public void onResult(@NotNull DataChain data) {

    }
    //endregion

    //region Utility API
    private void initConfigBuilder() {
        ProcessorConfig.Builder builder = new ProcessorConfig.Builder();
        builder.setSentenceModelPath(getSentenceBinModelPath());
        builder.setTokenizerModelPath(getTokenizerBinModelPath());
        builder.setNerModelPath(getNerNameBinModelPath());
        builder.setChunkerModelPath(getChunkerModelPath());
        builder.setPosModelPath(getPosBinModelPath());
        mProcessorConfig = builder.build();
    }
    //endregion
}
