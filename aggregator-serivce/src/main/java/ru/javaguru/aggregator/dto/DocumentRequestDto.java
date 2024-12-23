package ru.javaguru.aggregator.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
public class DocumentRequestDto {
    UUID reqId;
    UUID uuid;
    DocType type;
}
