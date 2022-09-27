package sid.example.bankclientservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sid.example.bankclientservice.entities.Client;
import sid.example.bankclientservice.repository.ClientRepository;

@SpringBootApplication

public class BankClientServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankClientServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ClientRepository clientRepository){
		return args -> {
			for(int i=0;i<5;i++){
				Client client = Client.builder()
						.email("client"+i+1+"@gmail.com")
						.name("name"+i+1)
						.build();
				clientRepository.save(client);
			}
		};
	}
}
