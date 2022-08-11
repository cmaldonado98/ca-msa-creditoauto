package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.assignment.AssignmentDto;
import com.bancopichincha.credito.automotriz.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/assignment")
@AllArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createAssignment(@Valid @RequestBody AssignmentDto assignment){
        assignment.setIdAssignment(null);
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.createAssignment(assignment));
    }

    @PutMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> updateAssignment(@Valid @RequestBody AssignmentDto assignment) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.updateAssignment(assignment));
    }

    @DeleteMapping("/{id}")
    @Produces("application/json")
    @Transactional
    public ResponseEntity<CommonResponseDto> deleteAssignment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.deleteAssignment(id));
    }
}
