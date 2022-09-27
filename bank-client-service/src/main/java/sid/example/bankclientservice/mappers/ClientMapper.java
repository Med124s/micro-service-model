package sid.example.bankclientservice.mappers;
import org.mapstruct.Mapper;
import sid.example.bankclientservice.dto.ClientRequestDto;
import sid.example.bankclientservice.dto.ClientResponseDto;
import sid.example.bankclientservice.entities.Client;


@Mapper(componentModel = "spring")
//Mapper Object with entities using pattern Dto with framework mapStruct
public interface ClientMapper {
    ClientResponseDto toClientResponseDto(Client client);
    Client toClient(ClientRequestDto clientRequestDto);
}
