package br.com.sacola.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.sacola.exception.BagNotFoundException;
import br.com.sacola.repository.SacolaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Sacola service implementation test")
public class SacolaServiceImplTest {

    private final Long INVALID_ID = 0L;

    @Mock
    private SacolaRepository sacolaRepository;

    @InjectMocks
    private SacolaServiceImpl sacolaService;

    @Test
    @DisplayName("Return bag not found when bag not found")
    void returnBagNotFoundWhenBagNotFound() {
        when(sacolaRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        assertThrows(BagNotFoundException.class, () -> sacolaService.verSacola(INVALID_ID));
    }


}
