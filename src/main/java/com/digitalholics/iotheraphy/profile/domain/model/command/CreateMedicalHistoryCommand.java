package com.digitalholics.iotheraphy.profile.domain.model.command;

public record CreateMedicalHistoryCommand(Long patientId, Long physiotherapistId,
                                          String pathological, String nonPathological) {
}
