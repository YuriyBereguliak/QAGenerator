package com.bereguliak.ui.controllers.main;

import com.bereguliak.processor.model.entity.Question;
import com.bereguliak.processor.model.entity.Theses;
import org.jetbrains.annotations.NotNull;

public interface OnMainTextGeneratorResult {
    void onGeneratorResult(@NotNull Theses theses,
                           @NotNull Question question);
}
