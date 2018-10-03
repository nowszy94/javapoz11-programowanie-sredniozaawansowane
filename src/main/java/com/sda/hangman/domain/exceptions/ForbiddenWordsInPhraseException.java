package com.sda.hangman.domain.exceptions;

public class ForbiddenWordsInPhraseException extends GameException {
    public ForbiddenWordsInPhraseException(String message) {
        super(message);
    }
}
