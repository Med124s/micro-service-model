package sid.example.bankclientservice.service;
import sid.example.bankclientservice.dto.ClientRequestDto;
import sid.example.bankclientservice.dto.ClientResponseDto;

import java.util.List;

public interface IClientService {
    ClientResponseDto addClient(ClientRequestDto clientRequestDto);
    ClientResponseDto updateClient(Long code,ClientRequestDto clientRequestDto);
    ClientResponseDto getClientById(Long code);
    boolean deleteClient(Long code);
    List<ClientResponseDto> getClients();
}
