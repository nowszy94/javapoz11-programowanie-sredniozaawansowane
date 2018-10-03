package com.sda.hangman.domain;

import com.sda.hangman.domain.port.ForbiddenWordsRepository;

public class ForbiddenWordsValidator {
    private ForbiddenWordsRepository forbiddenWordsRepository;

    public ForbiddenWordsValidator(ForbiddenWordsRepository forbiddenWordsRepository) {
        this.forbiddenWordsRepository = forbiddenWordsRepository;
    }

    public boolean validate(String phrase) {
        String preparedPhrase = preparePhrase(phrase);
        return forbiddenWordsRepository.findAll()
                .stream()
                .map(e -> preparePhrase(e))
                .noneMatch(e -> preparedPhrase.contains(e));
    }

    private String preparePhrase(String phrase) {
        return phrase.replaceAll(" ", "")
                .toLowerCase();
    }
}
