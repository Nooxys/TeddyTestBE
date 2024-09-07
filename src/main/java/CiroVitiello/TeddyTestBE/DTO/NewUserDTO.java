package CiroVitiello.TeddyTestBE.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(@NotEmpty(message = "Inserisci un nome")
                         @Size(min = 2, max = 20, message = "il nome deve essere min 2 e max 20 lettere")
                         String name,
                         @NotEmpty(message = "Inserisci un cognome")
                         @Size(min = 2, max = 20, message ="il cognome deve essere min 2 e max 20 lettere")
                         String surname,
                         @Size(min = 5, max = 75, message = "l'indirizzo deve essere min 5 e max 75 lettere")
                         String address,
                         @Size(min = 3, max = 25, message = "la località deve essere min 3 e max 25 lettere")
                         String location,
                         @Size(min = 3, max = 25, message = "il comune deve essere min 3 e max 25 lettere")
                         String municipality,
                         String province,
                         @NotEmpty(message = "Inserisci una email")
                         @Email(message = "Inserisci una email valida")
                         String email,
                         @Size(max = 300, message = "Le note devono avere una lunghezza massima di 300 lettere")
                         String notes) {
}
