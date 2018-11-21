package com.bereguliak.porter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Porter {

    private static final Pattern PERFECTIVEGROUND = Pattern.compile("(учи|ючи|ачи|ячи|ши|виш)$");

    private static final Pattern PARTICIPLE = Pattern.compile("(учи|ючи|ачи|ячи|ши|вши)$");

    private static final Pattern NOUN = Pattern.compile("((ею|єю|їв|ів|ей|єй|ою|ові|ом)|((?<=[ая])(м|ми|х|ти|ті|та|тами))|((?<=[еє])(ві|м|ні|нем|нами)))$");

    private static final Pattern RVRE = Pattern.compile("^(.*?[аеєиіїоуюя])(.*)$");

    private static final Pattern NN = Pattern.compile("нн$");

    public String stemer(String word) {
        word = word.toLowerCase();

        word = word.replace('г', 'г');
        word = word.replace('`', ' ');

        Matcher m = RVRE.matcher(word);
        if (m.matches()) {
            String pre = m.group(1);
            String rv = m.group(2);
            String temp = PERFECTIVEGROUND.matcher(rv).replaceFirst("");
            if (temp.equals(rv)) {
                rv = temp;
                rv = PARTICIPLE.matcher(rv).replaceFirst("");
                rv = NOUN.matcher(rv).replaceFirst("");
            } else {
                rv = temp;
            }

            if (temp.equals(rv)) {
                rv = NN.matcher(rv).replaceFirst("н");
            } else {
                rv = temp;
            }
            word = pre + rv;

        }

        return word;
    }

}
