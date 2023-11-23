package com.digitalholics.iotheraphy.profile.domain.model.command;

public record CreateDiagnosisCoomand(Long PhysiotherapistId, Long PatientId, String text) {
}
