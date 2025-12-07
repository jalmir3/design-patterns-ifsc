package ifsc.edu.designpatterns;

import ifsc.edu.designpatterns.behavioral.GerenciadorAtendimento;
import ifsc.edu.designpatterns.behavioral.NotificadorEmail;
import ifsc.edu.designpatterns.behavioral.NotificadorSMS;
import ifsc.edu.designpatterns.behavioral.TipoAtendimento;
import ifsc.edu.designpatterns.creational.HospitalManager;
import ifsc.edu.designpatterns.domain.Medico;
import ifsc.edu.designpatterns.domain.Paciente;
import ifsc.edu.designpatterns.structural.AtendimentoConsultaAdapter;
import ifsc.edu.designpatterns.structural.AtendimentoEmergenciaAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class MenuPrincipal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalManager hospital = HospitalManager.getInstance();
    private static final GerenciadorAtendimento gerenciador = new GerenciadorAtendimento();
    private static boolean sair = false;

    public static void run() {
        inicializarObservadores();

        while (!sair) {
            exibirMenuPrincipal();
            processarOpcao();
        }

        finalizarAplicacao();
    }

    private static void exibirMenuPrincipal() {
        log.info("\n{}", "=".repeat(80));
        log.info("SISTEMA DE GERENCIAMENTO DE HOSPITAL");
        log.info("Padrões de Projeto: Singleton, Builder, Adapter, Observer");
        log.info("=".repeat(80));
        log.info("\n1. Cadastrar Paciente (Padrão: BUILDER)");
        log.info("2. Cadastrar Médico (Padrão: SINGLETON)");
        log.info("3. Realizar Atendimento (Padrão: ADAPTER)");
        log.info("4. Enviar Notificações (Padrão: OBSERVER)");
        log.info("5. Listar Pacientes");
        log.info("6. Listar Médicos");
        log.info("7. Estatísticas do Hospital");
        log.info("8. Sair");
        log.info("\nEscolha uma opção: ");
    }

    private static void processarOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> cadastrarMedico();
                case 3 -> realizarAtendimento();
                case 4 -> enviarNotificacoes();
                case 5 -> listarPacientes();
                case 6 -> listarMedicos();
                case 7 -> exibirEstatisticas();
                case 8 -> sair = true;
                default -> log.warn("Opção inválida! Tente novamente.");
            }
        } catch (NumberFormatException e) {
            log.error("Entrada inválida! Digite um número.");
        }
    }

    private static void cadastrarPaciente() {
        log.info("\n{}", "-".repeat(80));
        log.info("CADASTRAR PACIENTE (Padrão Builder)");
        log.info("-".repeat(80));

        try {
            log.info("Nome: ");
            String nome = scanner.nextLine().trim();

            log.info("CPF: ");
            String cpf = scanner.nextLine().trim();

            log.info("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine().trim());

            log.info("Email: ");
            String email = scanner.nextLine().trim();

            log.info("Telefone: ");
            String telefone = scanner.nextLine().trim();

            Paciente paciente = new Paciente.Builder()
                    .nome(nome)
                    .cpf(cpf)
                    .idade(idade)
                    .email(email)
                    .telefone(telefone)
                    .build();

            hospital.adicionarPaciente(paciente);
            log.info("Paciente cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            log.error("Erro ao processar dados! Verifique a entrada.");
        }
    }

    private static void cadastrarMedico() {
        log.info("\n{}", "-".repeat(80));
        log.info("CADASTRAR MÉDICO (Padrão Singleton)");
        log.info("-".repeat(80));

        try {
            log.info("Nome: ");
            String nome = scanner.nextLine().trim();

            log.info("CRM: ");
            String crm = scanner.nextLine().trim();

            log.info("Especialidade: ");
            String especialidade = scanner.nextLine().trim();

            Medico medico = new Medico(nome, crm, especialidade);
            hospital.adicionarMedico(medico);
            log.info("Médico cadastrado com sucesso!");
            log.info("Gerenciado pelo HospitalManager(Padrão Singleton).");

        } catch (Exception e) {
            log.error("Erro ao processar dados!");
        }
    }

    private static void realizarAtendimento() {
        log.info("\n{}", "-".repeat(80));
        log.info("REALIZAR ATENDIMENTO (Padrão Adapter)");
        log.info("-".repeat(80));

        try {
            log.info("\nTipo de Atendimento:");
            log.info("1. Consulta");
            log.info("2. Exame");
            log.info("3. Emergência");
            log.info("4. Cirurgia");

            int tipo = Integer.parseInt(scanner.nextLine().trim());

            log.info("Nome do Paciente: ");
            String paciente = scanner.nextLine().trim();

            log.info("Descrição do Atendimento: ");
            String descricao = scanner.nextLine().trim();

            switch (tipo) {
                case 1 -> {
                    AtendimentoConsultaAdapter adapter = new AtendimentoConsultaAdapter();
                    adapter.realizarAtendimento(paciente, descricao);
                }
                case 2 -> {
                    AtendimentoEmergenciaAdapter adapter = new AtendimentoEmergenciaAdapter();
                    adapter.realizarAtendimento(paciente, descricao);
                }
                default -> log.warn("Tipo de atendimento inválido!");
            }

        } catch (NumberFormatException e) {
            log.error("Entrada inválida!");
        }
    }

    private static void enviarNotificacoes() {
        log.info("\n{}", "-".repeat(80));
        log.info("Enviar notificações (Padrão Observer)");
        log.info("-".repeat(80));

        try {
            log.info("\nTipo de Atendimento:");
            log.info("1. Consulta");
            log.info("2. Emergencia");

            int tipo = Integer.parseInt(scanner.nextLine().trim());
            TipoAtendimento tipoAtendimento = switch (tipo) {
                case 1 -> TipoAtendimento.CONSULTA;
                case 2 -> TipoAtendimento.EMERGENCIA;
                default -> null;
            };

            if (tipoAtendimento == null) {
                log.warn("Tipo invalido!");
                return;
            }

            log.info("Nome do Paciente: ");
            String paciente = scanner.nextLine().trim();

            log.info("Mensagem: ");
            String mensagem = scanner.nextLine().trim();

            gerenciador.notificarObservadores(tipoAtendimento, paciente, mensagem);
            log.info("Notificações enviadas para todos os canais!");

        } catch (NumberFormatException e) {
            log.error("Entrada inválida!");
        }
    }

    private static void listarPacientes() {
        log.info("\n{}", "-".repeat(80));
        log.info(" PACIENTES CADASTRADOS");
        log.info("-".repeat(80));

        if (hospital.listarPacientes().isEmpty()) {
            log.info("Nenhum paciente cadastrado.");
        } else {
            hospital.listarPacientes().forEach(p -> {
                log.info("\n• {}", p.getNome());
                log.info("  CPF: {}", p.getCpf());
                log.info("  Idade: {} anos", p.getIdade());
                log.info("  Email: {}", p.getEmail());
                log.info("  Telefone: {}", p.getTelefone());
            });
        }
    }

    private static void listarMedicos() {
        log.info("\n{}", "-".repeat(80));
        log.info("  MÉDICOS CADASTRADOS");
        log.info("-".repeat(80));

        if (hospital.listarMedicos().isEmpty()) {
            log.info("Nenhum médico cadastrado.");
        } else {
            hospital.listarMedicos().forEach(m -> {
                log.info("\n• " + m.getNome());
                log.info("  CRM: " + m.getCrm());
                log.info("  Especialidade: " + m.getEspecialidade());
            });
        }
    }

    private static void exibirEstatisticas() {
        log.info("\n{}", "=".repeat(80));
        log.info("ESTATÍSTICAS DO HOSPITAL");
        log.info("=".repeat(80));
        log.info("Total de Pacientes: {}", hospital.getTotalPacientes());
        log.info("Total de Médicos: {}", hospital.getTotalMedicos());
        log.info("Total de Observadores Ativos: {}", gerenciador.getTotalObservadores());
        log.info("=".repeat(80));
    }

    private static void inicializarObservadores() {
        gerenciador.registrarObservador(new NotificadorEmail());
        gerenciador.registrarObservador(new NotificadorSMS());
    }

    private static void finalizarAplicacao() {
        log.info("\n{}", "=".repeat(80));
        log.info("Agradecemos por utilizar o nosso serviço(Jalmir,Hanelly e Henrique!");
        log.info("{}\n", "=".repeat(80));
        scanner.close();
    }
}

