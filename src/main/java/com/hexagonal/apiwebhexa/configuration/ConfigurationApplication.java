package com.hexagonal.apiwebhexa.configuration;

import com.hexagonal.apiwebhexa.domain.port.primaire.GestionHabitants;
import com.hexagonal.apiwebhexa.domain.port.secondaire.GestionMailPort;
import com.hexagonal.apiwebhexa.domain.port.secondaire.HabitantsPort;
import com.hexagonal.apiwebhexa.domain.usecase.GetHabitants;
import com.hexagonal.apiwebhexa.domain.usecase.PreparerEnvoiMail;
import com.hexagonal.apiwebhexa.infrastructure.mail.GestionMailAdapter;
import com.hexagonal.apiwebhexa.infrastructure.database.HabitantsDatabaseAdaptater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApplication {

    private HabitantsDatabaseAdaptater gestionHabitantsHadaptater;
    private ProprieteApplication proprieteApplication;
    private GestionMailPort gestionMailAdaptateur;

    public ConfigurationApplication(HabitantsDatabaseAdaptater gestionHabitantsHadaptater, ProprieteApplication proprieteApplication) {
        this.gestionHabitantsHadaptater = gestionHabitantsHadaptater;
        this.proprieteApplication = proprieteApplication;
        this.gestionMailAdaptateur = new GestionMailAdapter(this.proprieteApplication.getSmtpPort(), this.proprieteApplication.getSmtpHost(), this.proprieteApplication.getSmtpEmetteur());
    }

    @Bean
    public GestionHabitants gesHabitants() { return new GetHabitants(this.gestionHabitantsHadaptater);}

    @Bean
    public PreparerEnvoiMail preparerEnvoiMail() { return new PreparerEnvoiMail(this.gestionMailAdaptateur, this.proprieteApplication.getPathToMailTemplate());}

}
