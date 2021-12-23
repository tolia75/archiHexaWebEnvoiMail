package com.hexagonal.apiwebhexa.app.rest;

import com.hexagonal.apiwebhexa.domain.exception.EnvoiMailException;
import com.hexagonal.apiwebhexa.domain.exception.ReglesMetierException;
import com.hexagonal.apiwebhexa.domain.exception.ValidatorMetierException;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionHabitants;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionMail;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MailControllerAdapter.class)
class MailAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GestionHabitants gestionHabitants;
    @MockBean
    GestionMail gestionMail;

    @BeforeEach
    void setUp() {
    }


    @ParameterizedTest()
    @ValueSource(strings = {"habitants","categorieSocioProfessionnelle/9","trancheDage/5/66"})
    void shouldSendMailIfParametersAndTreatmentAreOk(String argumentsRequete) throws Exception{
        // When
        mockMvc.perform(get("/api/mails/"+argumentsRequete+"/objetDuMail"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"habitants","categorieSocioProfessionnelle/7","trancheDage/5/66"})
    void shouldBeAInternalErrorBecauseOfRegleMetierException(String argumentsRequete) throws Exception{
        // Given
        doThrow(ReglesMetierException.class).when(gestionMail).envoyerMail(anyList(), anyString());

        // When
        mockMvc.perform(get("/api/mails/"+argumentsRequete+"/objetDuMail"))
                .andExpect(status().isInternalServerError());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"habitants","categorieSocioProfessionnelle/21","trancheDage/-5/-66"})
    void shouldBeABadRequestIfValidatorMetierExceptionIsThrown(String argumentsRequete) throws Exception{
        // Given
        doThrow(ValidatorMetierException.class).when(gestionMail).envoyerMail(anyList(), anyString());

        // When
        mockMvc.perform(get("/api/mails/"+argumentsRequete+"/objetDuMail"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"habitants","categorieSocioProfessionnelle/21","trancheDage/-5/-66"})
    void shouldBeAnInternalServerErrorIfEnvoiMailExceptionIsThrown(String argumentsRequete) throws Exception{
        // Given
        doThrow(EnvoiMailException.class).when(gestionMail).envoyerMail(anyList(), anyString());

        // When
        mockMvc.perform(get("/api/mails/"+argumentsRequete+"/objetDuMail"))
                .andExpect(status().isInternalServerError());
    }
}
