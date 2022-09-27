package sid.example.bankclientservice.dto;

import lombok.Data;

@Data
public class ClientResponseDto {
    private Long code;
    private String name;
    private String email;
}
