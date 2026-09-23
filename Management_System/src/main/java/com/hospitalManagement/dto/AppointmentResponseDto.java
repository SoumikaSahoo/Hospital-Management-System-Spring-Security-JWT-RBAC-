package com.hospitalManagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private com.hospitalManagement.dto.DoctorResponseDto doctor;
//    private PatientResponseDto patient;
}
