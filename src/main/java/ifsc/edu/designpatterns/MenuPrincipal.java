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
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SISTEMA DE GERENCIAMENTO DE HOSPITAL");
        System.out.println("=".repeat(80));
        System.out.println("\n1. Cadastrar Paciente (Padrão: BUILDER)");
        System.out.println("2. Cadastrar Médico (Padrão: SINGLETON)");
        System.out.println("3. Realizar Atendimento (Padrão: ADAPTER)");
        System.out.println("4. Enviar Notificações (Padrão: OBSERVER)");
        System.out.println("5. Listar Pacientes");
        System.out.println("6. Listar Médicos");
        System.out.println("7. Estatísticas do Hospital");
        System.out.println("8. Sair");
        System.out.print("\nEscolha uma opção: ");
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
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
        }
    }

    private static void cadastrarPaciente() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("CADASTRAR PACIENTE (Padrão Builder)");
        System.out.println("-".repeat(80));

        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine().trim();

            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine().trim();

            Paciente paciente = new Paciente.Builder()
                    .nome(nome)
                    .cpf(cpf)
                    .idade(idade)
                    .email(email)
                    .telefone(telefone)
                    .build();

            hospital.adicionarPaciente(paciente);
            System.out.println("Paciente cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro ao processar dados! Verifique a entrada.");
        }
    }

    private static void cadastrarMedico() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("CADASTRAR MÉDICO (Padrão Singleton)");
        System.out.println("-".repeat(80));

        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();

            System.out.print("CRM: ");
            String crm = scanner.nextLine().trim();

            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine().trim();

            Medico medico = new Medico(nome, crm, especialidade);
            hospital.adicionarMedico(medico);
            System.out.println("Médico cadastrado com sucesso!");
            System.out.println("Gerenciado pelo HospitalManager(Padrão Singleton)");

        } catch (Exception e) {
            System.out.println("Erro ao processar dados!");
        }
    }

    private static void realizarAtendimento() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("REALIZAR ATENDIMENTO (Padrão Adapter)");
        System.out.println("-".repeat(80));

        try {
            System.out.println("\nTipo de Atendimento:");
            System.out.println("1. Consulta");
            System.out.println("2. Emergência");
            System.out.print("Escolha: ");

            int tipo = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nome do Paciente: ");
            String paciente = scanner.nextLine().trim();

            System.out.print("Descrição do Atendimento: ");
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
                default -> System.out.println("Tipo de atendimento inválido!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private static void enviarNotificacoes() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("ENVIAR NOTIFICAÇÕES (Padrão Observer)");
        System.out.println("-".repeat(80));

        try {
            System.out.println("\nTipo de Atendimento:");
            System.out.println("1. Consulta");
            System.out.println("2. Emergência");
            System.out.print("Escolha: ");

            int tipo = Integer.parseInt(scanner.nextLine().trim());
            TipoAtendimento tipoAtendimento = switch (tipo) {
                case 1 -> TipoAtendimento.CONSULTA;
                case 2 -> TipoAtendimento.EMERGENCIA;
                default -> null;
            };

            if (tipoAtendimento == null) {
                System.out.println("Tipo inválido!");
                return;
            }

            System.out.print("Nome do Paciente: ");
            String paciente = scanner.nextLine().trim();

            System.out.print("Mensagem: ");
            String mensagem = scanner.nextLine().trim();

            gerenciador.notificarObservadores(tipoAtendimento, paciente, mensagem);
            System.out.println("Notificações enviadas para todos os canais!");

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private static void listarPacientes() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("PACIENTES CADASTRADOS");
        System.out.println("-".repeat(80));

        if (hospital.listarPacientes().isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            hospital.listarPacientes().forEach(p -> {
                System.out.println("\n• " + p.getNome());
                System.out.println("  CPF: " + p.getCpf());
                System.out.println("  Idade: " + p.getIdade() + " anos");
                System.out.println("  Email: " + p.getEmail());
                System.out.println("  Telefone: " + p.getTelefone());
            });
        }
    }

    private static void listarMedicos() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("MÉDICOS CADASTRADOS");
        System.out.println("-".repeat(80));

        if (hospital.listarMedicos().isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
        } else {
            hospital.listarMedicos().forEach(m -> {
                System.out.println("\n• " + m.getNome());
                System.out.println("  CRM: " + m.getCrm());
                System.out.println("  Especialidade: " + m.getEspecialidade());
            });
        }
    }

    private static void exibirEstatisticas() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ESTATÍSTICAS DO HOSPITAL");
        System.out.println("=".repeat(80));
        System.out.println("Total de Pacientes: " + hospital.getTotalPacientes());
        System.out.println("Total de Médicos: " + hospital.getTotalMedicos());
        System.out.println("Total de Observadores Ativos: " + gerenciador.getTotalObservadores());
        System.out.println("=".repeat(80));
    }

    private static void inicializarObservadores() {
        gerenciador.registrarObservador(new NotificadorEmail());
        gerenciador.registrarObservador(new NotificadorSMS());
        log.info("Observadores inicializados com sucesso");
    }

    private static void finalizarAplicacao() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Obrigado por usar o Sistema de Gerenciamento de Hospital!");
        System.out.println("=".repeat(80) + "\n");
        scanner.close();
    }
}

