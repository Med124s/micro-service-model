package sid.example.bankclientservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sid.example.bankclientservice.dto.ClientRequestDto;
import sid.example.bankclientservice.dto.ClientResponseDto;
import sid.example.bankclientservice.entities.Client;
import sid.example.bankclientservice.mappers.ClientMapper;
import sid.example.bankclientservice.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ClientServiceImp implements IClientService {
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    @Override
    public ClientResponseDto addClient(ClientRequestDto clientRequestDto) {
        Client client = clientMapper.toClient(clientRequestDto);
        Client saveClient = clientRepository.save(client);
        ClientResponseDto clientResponseDto = clientMapper.toClientResponseDto(saveClient);
        return clientResponseDto;
    }

    @Override
    public ClientResponseDto updateClient(Long id,ClientRequestDto clientRequestDto) {
       Client client = clientRepository.findById(id).orElseThrow();
           if(clientRequestDto.getEmail()!=null) client.setEmail(clientRequestDto.getEmail());
           if(clientRequestDto.getName()!=null) client.setName(clientRequestDto.getName());
        Client updatedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = clientMapper.toClientResponseDto(updatedClient);

        return clientResponseDto;
    }

    @Override
    public ClientResponseDto getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Client %s Not Found",id)));
        return clientMapper.toClientResponseDto(client);
    }

    @Override
    public boolean deleteClient(Long id) {
        if(clientRepository.findById(id).isPresent()){
            clientRepository.deleteById(id);
             return true;
        }
        return false;
    }

    @Override
    public List<ClientResponseDto> getClients() {
        List<Client>clients = clientRepository.findAll();
        List<ClientResponseDto>clientsResponseDto=
        clients.stream().map(cl->
             clientMapper.toClientResponseDto(cl)
        ).collect(Collectors.toList());
        return clientsResponseDto;
    }
}
