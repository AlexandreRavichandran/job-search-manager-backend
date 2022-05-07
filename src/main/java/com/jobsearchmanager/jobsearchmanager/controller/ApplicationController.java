package com.jobsearchmanager.jobsearchmanager.controller;

import com.jobsearchmanager.jobsearchmanager.converter.StatusStringToEnumConverter;
import com.jobsearchmanager.jobsearchmanager.domain.AppUser;
import com.jobsearchmanager.jobsearchmanager.domain.Application;
import com.jobsearchmanager.jobsearchmanager.dto.ApplicationDTO;
import com.jobsearchmanager.jobsearchmanager.dto.ApplicationImportationDTO;
import com.jobsearchmanager.jobsearchmanager.repository.AppUserRepository;
import com.jobsearchmanager.jobsearchmanager.service.ApplicationServiceImpl;
import com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.ThirdpartyAPISelector;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController extends AbstractController{

    @Autowired
    ApplicationServiceImpl applicationService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ThirdpartyAPISelector apiSelector;

    @Autowired
    StatusStringToEnumConverter stringToEnumConverter;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/status/{status}/archived/{archived}")
    public ResponseEntity<Collection<ApplicationDTO>> getByStatus(@PathVariable("status") String status,
                                                                  @PathVariable("archived") String archived) {
        AppUser currentUser = this.getCurrentLoggedUser();
        return new ResponseEntity<>(
                this.applicationService.browseByStatusAndArchived(
                                currentUser.getId(),
                                this.stringToEnumConverter.convert(status),
                                archived)
                        .stream()
                        .map(application -> this.modelMapper.map(application, ApplicationDTO.class))
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @PostMapping("/importation")
    public ResponseEntity<ApplicationDTO> importByLink(@RequestBody ApplicationImportationDTO applicationImportation) {
        try {
            return new ResponseEntity<>(
                    this.modelMapper.map(this.apiSelector.guessServiceByLink(applicationImportation.getLink()),
                            ApplicationDTO.class),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> read(@PathVariable("applicationId") Long applicationId) {

        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.read(applicationId),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> edit(@RequestBody ApplicationDTO applicationDTO) {
        Application application = this.modelMapper.map(applicationDTO, Application.class);
        application.setRelatedUser(this.getCurrentLoggedUser());
        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.edit(application),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> add(@RequestBody ApplicationDTO applicationDTO) {
        Application application = this.modelMapper.map(applicationDTO, Application.class);
        application.setRelatedUser(this.getCurrentLoggedUser());
        application.setStatus(this.stringToEnumConverter.convert(applicationDTO.getStatus()));
        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.add(application),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> delete(@PathVariable("applicationId")  Long applicationId) {

        return new ResponseEntity<>(this.modelMapper.map(this.applicationService.delete(applicationId),
                ApplicationDTO.class),
                HttpStatus.OK
        );
    }


}
