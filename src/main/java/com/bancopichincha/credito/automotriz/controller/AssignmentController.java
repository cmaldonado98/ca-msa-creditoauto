package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.assignment.AssignmentDto;
import com.bancopichincha.credito.automotriz.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/assignment")
@AllArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createAssignment(@RequestBody AssignmentDto assignment){
        assignment.setIdAssignment(null);
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.createAssignment(assignment));
    }
}
