package com.bereguliak.ui.controllers.main;

import com.bereguliak.processor.model.entity.Question;
import com.bereguliak.processor.model.entity.Theses;
import com.bereguliak.processor.model.entity.net.SearchData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface OnMainTextGeneratorResult {
    void onGeneratorResult(@NotNull Theses theses,
                           @NotNull Question question,
                           @NotNull List<SearchData> searchData);
}
