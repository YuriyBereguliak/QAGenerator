package com.bereguliak.ui.controllers.main;

import com.bereguliak.configuration.processor.ProcessorConfig;
import com.bereguliak.generator.utility.annotations.LateInit;
import com.bereguliak.processor.builder.question.SimpleQuestionGenerator;
import com.bereguliak.processor.builder.search.SimpleGoogleSearchGenerator;
import com.bereguliak.processor.builder.these.TheseGenerator;
import com.bereguliak.processor.generator.TextGeneration;
import com.bereguliak.processor.model.entity.DataChain;
import com.bereguliak.processor.model.entity.Question;
import com.bereguliak.processor.model.entity.Theses;
import com.bereguliak.processor.model.entity.net.SearchData;
import com.bereguliak.processor.model.listeners.OnTextGeneratorResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.bereguliak.configuration.DefaultTrainPathsExtensionKt.*;
import static com.bereguliak.generator.utility.LoggerKt.log;

public class MainController implements MainControllerApi, OnTextGeneratorResult {

    @NotNull
    private TextGeneration mTextGeneration;

    @LateInit
    private ProcessorConfig mProcessorConfig;

    @NotNull
    private OnMainTextGeneratorResult mOnMainTextGeneratorResult;

    public MainController(@NotNull OnMainTextGeneratorResult onMainTextGeneratorResult) {
        mOnMainTextGeneratorResult = onMainTextGeneratorResult;
        mTextGeneration = new TextGeneration(this);
        initConfigBuilder();
    }

    //region MainControllerApi
    @Override
    public void startGenerator(@NotNull String sourceText) {
        new Thread(() -> mTextGeneration.runTextGenerator(sourceText, mProcessorConfig)).start();
    }
    //endregion

    //region OnTextGeneratorResult
    @Override
    public void onResult(@NotNull DataChain data) {
        log(data.getSentences(), "Sentence");
        log(data.getTokens(), "Tokens");

        Theses theses = new TheseGenerator(data).generate();
        Question questions = new SimpleQuestionGenerator(data).generate();
        List<SearchData> searchData = new SimpleGoogleSearchGenerator(data).generate();

        mOnMainTextGeneratorResult.onGeneratorResult(theses, questions, searchData);
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
