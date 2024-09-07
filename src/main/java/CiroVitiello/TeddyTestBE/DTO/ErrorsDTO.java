package CiroVitiello.TeddyTestBE.DTO;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateTimeStamp) {
}