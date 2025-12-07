package ifsc.edu.designpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PADRÕES IMPLEMENTADOS:
 * 1. Criacional - SINGLETON: HospitalManager
 * 2. Criacional - BUILDER: Paciente.Builder
 * 3. Estrutural - ADAPTER: TipoAtendimentoAdapter e suas implementações
 * 4. Comportamental - OBSERVER: GerenciadorAtendimento e observadores
 */
@SpringBootApplication
public class DesignPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignPatternsApplication.class, args);
        MenuPrincipal.run();
    }
}

