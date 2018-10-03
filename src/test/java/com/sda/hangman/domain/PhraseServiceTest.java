package com.sda.hangman.domain;

import com.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import com.sda.hangman.domain.exceptions.PhraseAlreadyExistsException;
import com.sda.hangman.domain.port.PhraseRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

public class PhraseServiceTest {

    @Test
    public void addPhraseShouldAddNewPhrase() throws Exception {
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(false);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);

        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);

        //when
        phraseService.addPhrase("phrase with forbiddenWord");

        //then
        Mockito.verify(phraseRepository, Mockito.times(1)).save("phrase with forbiddenWord");
    }

    @Test(expected = PhraseAlreadyExistsException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseAlreadyExists() throws Exception {
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(true);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);

        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);

        //when
        phraseService.addPhrase("phrase with forbiddenWord");
    }

    @Test(expected = ForbiddenWordsInPhraseException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseContainsForbiddenWords() throws Exception {
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(false);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(false);

        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);

        //when
        phraseService.addPhrase("phrase with forbiddenWord");
    }
}
