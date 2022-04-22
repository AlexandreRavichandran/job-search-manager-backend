package com.jobsearchmanager.jobsearchmanager;

import com.github.javafaker.Faker;
import com.jobsearchmanager.jobsearchmanager.domain.*;
import com.jobsearchmanager.jobsearchmanager.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;

@SpringBootApplication
public class JobsearchmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsearchmanagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(
            AppUserRepository appUserRepository,
            ActivityRepository activityRepository,
            ApplicationRepository applicationRepository,
            DiscussionRepository discussionRepository,
            NoteRepository noteRepository) {

        return args -> {

            List<Boolean> booleans = new ArrayList<>();
            booleans.add(true);
            booleans.add(false);

            List<DiscussionEnum> discussionTypes = new ArrayList<>();
            discussionTypes.add(DiscussionEnum.MAIL_DISCUSSION);
            discussionTypes.add(DiscussionEnum.PHONE_MEETING);
            discussionTypes.add(DiscussionEnum.PHYSICAL_MEETING);
            discussionTypes.add(DiscussionEnum.VISIO_MEETING);

            List<StatusEnum> applicationStatus = new ArrayList<>();
            applicationStatus.add(StatusEnum.GOING_TO_APPLY);

            List<ResultEnum> applicationResults = new ArrayList<>();
            applicationResults.add(ResultEnum.NO_RESPONSE);
            applicationResults.add(ResultEnum.FAILED);
            applicationResults.add(ResultEnum.SUCCEED);

            Faker faker = new Faker(Locale.FRANCE);

            for (int i = 0; i < 3; i++) {
                AppUser appUser = new AppUser(
                        null,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        null,
                        faker.internet().emailAddress(),
                        this.passwordEncoder().encode("demo"),
                        new ArrayList<>(),
                        new ArrayList<>()
                );

                appUser.setUsername(appUser.getFirstName().toLowerCase() + "." + appUser.getLastName().toLowerCase());
                appUserRepository.save(appUser);

                for (int j = 0; j < 2; j++) {
                    Application application = new Application(
                            null,
                            faker.job().title(),
                            faker.lorem().paragraph(2),
                            applicationStatus.get(faker.number().numberBetween(0, applicationStatus.size())),
                            faker.internet().url(),
                            booleans.get(faker.number().numberBetween(0,booleans.size())),
                            applicationResults.get(faker.number().numberBetween(0, applicationResults.size())),
                            new Date(),
                            faker.company().name(),
                            faker.address().fullAddress(),
                            faker.internet().emailAddress(),
                            faker.phoneNumber().phoneNumber(),
                            booleans.get(faker.number().numberBetween(0,booleans.size())),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            appUser
                    );

                    applicationRepository.save(application);

                    for (int k = 0; k < 4; k++) {
                        Activity activity = new Activity(
                                null,
                                applicationStatus.get(faker.number().numberBetween(0, applicationStatus.size())),
                                applicationResults.get(faker.number().numberBetween(0, applicationResults.size())),
                                new Date(),
                                appUser,
                                application
                        );

                        activityRepository.save(activity);
                    }

                    for (int k = 0; k < 4; k++) {
                        Discussion discussion = new Discussion(
                                null,
                                "XX/XX/XXXX",
                                discussionTypes.get(faker.number().numberBetween(0, discussionTypes.size())),
                                faker.lorem().paragraph(2),
                                new Date(),
                                application
                        );

                        discussionRepository.save(discussion);
                    }

                    for (int k = 0; k < 4; k++) {
                        Note note = new Note(
                                null,
                                new Date(),
                                faker.lorem().paragraph(2),
                                new Date(),
                                application
                        );

                        noteRepository.save(note);
                    }

                }

            }


        };
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
