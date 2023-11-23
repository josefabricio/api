package com.digitalholics.iotheraphy.profile.domain.model.command;

public record UpdateDiagnosisCommand(Long patientId, String text) {
}
