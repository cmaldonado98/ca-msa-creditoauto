package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.assignment.AssignmentDto;
import com.bancopichincha.credito.automotriz.model.entities.AssignmentEntity;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import com.bancopichincha.credito.automotriz.model.entities.ClientEntity;
import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import com.bancopichincha.credito.automotriz.repository.AssignmentRepository;
import com.bancopichincha.credito.automotriz.repository.CarYardRepository;
import com.bancopichincha.credito.automotriz.repository.ClientRepository;
import com.bancopichincha.credito.automotriz.service.AssignmentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;

@Service
@Log4j2
@AllArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final ClientRepository clientRepository;

    private final CarYardRepository carYardRepository;

    @Override
    @Transactional
    public CommonResponseDto createAssignment(AssignmentDto assignment) {
        log.info(String.format("Creating assignment with clientId: %s and carYardId: %s", assignment.getClientId().toString(), assignment.getCarYardId().toString()));
        ClientEntity clientEntity = clientRepository.findById(assignment.getClientId())
                .orElseThrow(() -> {
                    log.error(String.format("Client not found by id: %s", assignment.getClientId()));
                    return new ApplicationException(ResponseStatusCode.CLIENT_DOES_NOT_EXISTS);
                });

        CarYardEntity carYardEntity = carYardRepository.findById(assignment.getCarYardId())
                .orElseThrow(() -> {
                    log.error(String.format("Client not found by id: %s", assignment.getCarYardId()));
                    return new ApplicationException(ResponseStatusCode.CAR_YARD_DOES_NOT_EXISTS);
                });

        AssignmentEntity newAssignment = new AssignmentEntity();
        newAssignment.setDateAssignment(LocalDateTime.now());
        newAssignment.setClient(clientEntity);
        newAssignment.setCarYardEntity(carYardEntity);

        newAssignment = assignmentRepository.save(newAssignment);

        return CommonResponseDto.builder()
                .code(OK.getCode())
                .message(OK.getMessage())
                .response(String.format("ID: %s", newAssignment.getIdAssignment()))
                .build();
    }

    @Override
    public CommonResponseDto updateAssignment(AssignmentDto assignment){
        log.info(String.format("Updating assignment with clientId: %s and carYardId: %s", assignment.getClientId().toString(), assignment.getCarYardId().toString()));
        ClientEntity clientEntity = clientRepository.findById(assignment.getClientId())
                .orElseThrow(() -> {
                    log.error(String.format("Client not found by id: %s", assignment.getClientId().toString()));
                    return new ApplicationException(ResponseStatusCode.CLIENT_DOES_NOT_EXISTS);
                });

        CarYardEntity carYardEntity = carYardRepository.findById(assignment.getCarYardId())
                .orElseThrow(() -> {
                    log.error(String.format("Client not found by id: %s", assignment.getCarYardId().toString()));
                    return new ApplicationException(ResponseStatusCode.CAR_YARD_DOES_NOT_EXISTS);
                });

        AssignmentEntity assignmentEntity = assignmentRepository.findById(assignment.getIdAssignment())
                .orElseThrow(() -> {
                    log.error(String.format("Assignment not found by id: %s", assignment.getIdAssignment().toString()));
                    return new ApplicationException(ResponseStatusCode.ASSIGNMENT_DOES_NOT_EXISTS);
                });

        assignmentEntity.setDateAssignment(LocalDateTime.now());
        assignmentEntity.setClient(clientEntity);
        assignmentEntity.setCarYardEntity(carYardEntity);

        assignmentRepository.save(assignmentEntity);

        return CommonResponseDto.build(OK);
    }

    @Override
    public CommonResponseDto deleteAssignment(Long id) {
        log.info(String.format("Deleting assignment with id: %s", id.toString()));

        AssignmentEntity assignmentEntity = assignmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("Assignment not found by id: %s", id.toString()));
                    return new ApplicationException(ResponseStatusCode.ASSIGNMENT_DOES_NOT_EXISTS);
                });
        assignmentRepository.delete(assignmentEntity);
        return CommonResponseDto.build(OK);
    }
}
