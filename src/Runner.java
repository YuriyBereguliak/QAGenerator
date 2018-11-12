import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class Runner {

    private static final String FOLDER_MODELS = "models";
    private static final String FOLDER_TRAIN = "train";
    private static final String FOLDER_NER = "ner";

    private static final String FILE_SENTENCE_TRAIN = "Sentence.txt";
    private static final String FILE_NER_MODEL = "ner-custom-model.bin";

    private static final String FILE_SENTENCE_TRAIN_UA = "UkraineTrainData.txt";
    private static final String FILE_NER_MODEL_UA = "ner-network-model.bin";

    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_UA = "uk";

    public static void main(String[] args) {
        // reading training data
        InputStreamFactory in = null;
        try {
            in = new MarkableFileInputStreamFactory(new File(getTrainPathNameUA()));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }

        ObjectStream sampleStream = null;
        try {
            sampleStream = new NameSampleDataStream(
                    new PlainTextByLineStream(in, StandardCharsets.UTF_16));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // training the model using TokenNameFinderModel class
        TokenNameFinderModel nameFinderModel = null;
        try {
            nameFinderModel = NameFinderME.train(LANGUAGE_UA, null, sampleStream,
                    TrainingParameters.defaultParams(),
                    TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // saving the model to "ner-custom-model.bin" file
        try {
            File output = new File(getNerPathNameUA());
            FileOutputStream outputStream = new FileOutputStream(output);
            nameFinderModel.serialize(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sentence = "Комп'ю́терна мере́жа — система зв'язку між двома чи більше комп'ютерами. Кооксіальні кабелі, виті пари";

        String[] ukrSentence = WhitespaceTokenizer.INSTANCE.tokenize(sentence);

        // testing the model and printing the types it found in the input sentence
        TokenNameFinder nameFinder = new NameFinderME(nameFinderModel);

        System.out.println("Finding types in the test sentence..");
        Span[] names = nameFinder.find(ukrSentence);
        for (Span name : names) {
            String personName = "";
            for (int i = name.getStart(); i < name.getEnd(); i++) {
                personName += ukrSentence[i] + " ";
            }
            System.out.println(name.getType() + " : " + personName + "\t [probability=" + name.getProb() + "]");
        }
        nameFinder.clearAdaptiveData();
    }

    //region Utility API
    @NotNull
    private static String getTrainPathNameUA() {
        return FOLDER_MODELS + File.separator + FOLDER_TRAIN + File.separator + FILE_SENTENCE_TRAIN_UA;
    }

    @NotNull
    private static String getNerPathNameUA() {
        return FOLDER_MODELS + File.separator + FOLDER_NER + File.separator + FILE_NER_MODEL_UA;
    }

    @NotNull
    private static String getTrainPathName() {
        return FOLDER_MODELS + File.separator + FOLDER_TRAIN + File.separator + FILE_SENTENCE_TRAIN;
    }

    @NotNull
    private static String getNerPathName() {
        return FOLDER_MODELS + File.separator + FOLDER_NER + File.separator + FILE_NER_MODEL;
    }
    //endregion
}
