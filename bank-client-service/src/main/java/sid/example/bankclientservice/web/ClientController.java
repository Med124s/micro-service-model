package sid.example.bankclientservice.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sid.example.bankclientservice.dto.ClientRequestDto;
import sid.example.bankclientservice.dto.ClientResponseDto;
import sid.example.bankclientservice.service.ClientServiceImp;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api")
public class ClientController {
    private ClientServiceImp clientServiceImp;
    @GetMapping("clients")
    public List<ClientResponseDto> clients(){
        return clientServiceImp.getClients();
    }
    @GetMapping("client/{id}")
    public ClientResponseDto getClientById(@PathVariable(name="id")Long id){
        return clientServiceImp.getClientById(id);
    }
    @PutMapping("updateClient/{id}")
    public ClientResponseDto updateClient(@PathVariable(name="id")Long id,@RequestBody() ClientRequestDto clientRequestDto){
        return clientServiceImp.updateClient( id,clientRequestDto);
    }
    @DeleteMapping("deleteClient/{id}")
    public boolean deleteAccount(@PathVariable(name="id") Long id){
        return clientServiceImp.deleteClient(id);
    }
    @PostMapping("saveClient")
    public ClientResponseDto save(@RequestBody() ClientRequestDto clientRequestDto){
        return clientServiceImp.addClient(clientRequestDto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>exceptionHandler(Exception e){
        log.error(e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
