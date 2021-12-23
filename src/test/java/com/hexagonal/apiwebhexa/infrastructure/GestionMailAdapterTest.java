package com.hexagonal.apiwebhexa.infrastructure;

import com.hexagonal.apiwebhexa.infrastructure.mail.GestionMailAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"com.hexagonal.application.smtpHost=localhost", "com.hexagonal.application.smtpPort=9999"})
public class GestionMailAdapterTest {

    private GestionMailAdapter gestionMailAdapter;

    @BeforeEach
    void setUp() {
        gestionMailAdapter = new GestionMailAdapter("9999", "localhost", "moi@mairie.com");
    }

}
