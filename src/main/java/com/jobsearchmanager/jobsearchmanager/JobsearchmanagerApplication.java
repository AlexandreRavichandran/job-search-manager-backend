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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

            List<DiscussionEnum> discussionTypes = new ArrayList<>();
            discussionTypes.add(DiscussionEnum.MAIL_DISCUSSION);
            discussionTypes.add(DiscussionEnum.PHONE_MEETING);
            discussionTypes.add(DiscussionEnum.PHYSICAL_MEETING);
            discussionTypes.add(DiscussionEnum.VISIO_MEETING);

            List<StatusEnum> applicationStatus = new ArrayList<>();
            applicationStatus.add(StatusEnum.GOING_TO_APPLY);
            applicationStatus.add(StatusEnum.APPLIED);
            applicationStatus.add(StatusEnum.RELAUNCH);
            applicationStatus.add(StatusEnum.HAVE_A_MEETING);

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
                        new ArrayList<>()
                );

                appUser.setUsername(appUser.getFirstName().toLowerCase() + "." + appUser.getLastName().toLowerCase());
                appUserRepository.save(appUser);

                for (int j = 0; j < 10; j++) {
                    Application application = new Application(
                            null,
                            faker.job().title(),
                            faker.lorem().paragraph(2),
                            applicationStatus.get(faker.number().numberBetween(0, applicationStatus.size())),
                            faker.internet().url(),
                            false,
                            applicationResults.get(faker.number().numberBetween(0, applicationResults.size())),
                            new Date(),
                            faker.company().name(),
                            faker.address().fullAddress(),
                            faker.internet().emailAddress(),
                            faker.phoneNumber().phoneNumber(),
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
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
