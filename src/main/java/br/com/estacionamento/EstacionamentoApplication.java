package br.com.estacionamento;

import br.com.estacionamento.model.*;
import br.com.estacionamento.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstacionamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstacionamentoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ClienteService clienteService, VeiculoService veiculoService, FuncionarioService funcionarioService, TicketService ticketService, EstacionamentoService estacionamentoService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n=== SISTEMA DE ESTACIONAMENTO ===");
                System.out.println("1 - Cadastrar Cliente");
                System.out.println("2 - Cadastrar Veículo para Cliente");
                System.out.println("3 - Cadastrar Funcionário");
                System.out.println("4 - Criar Ticket");
                System.out.println("5 - Cadastrar Estacionamento");
                System.out.println("6 - Listar Meus Veículos");
                System.out.println("7 - Listar Meus Tickets");
                System.out.println("8 - Listar Todos os Tickets");
                System.out.println("9 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarCliente(scanner, clienteService);
                        break;
                    case 2:
                        cadastrarVeiculoParaCliente(scanner, clienteService, veiculoService);
                        break;
                    case 3:
                        cadastarFuncionario(scanner, funcionarioService);
                        break;
                    case 4:
                        criarTicket(scanner, veiculoService, clienteService, funcionarioService, ticketService, estacionamentoService);
                        break;
                    case 5:
                        cadastrarEstacionamento(scanner, estacionamentoService);
                        break;
                    case 6:
                        listarMeusVeiculos(scanner, clienteService);
                        break;
                    case 7:
                        listarMeusTickets(scanner, ticketService);
                        break;
                    case 8:
                        listarTodosTickets(scanner, ticketService, funcionarioService);
                        break;
                    case 9:
                        running = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            scanner.close();
            System.exit(0);
        };
    }

    private List<Ticket> listarTodosTickets(Scanner scanner, TicketService ticketService, FuncionarioService funcionarioService) {
        System.out.println("\n--- LISTA DE TODOS OS TICKETS ---");

        System.out.println("Digite CPF do funcionário");
        var cpfFuncionario = scanner.nextLine();
        var funcionario = funcionarioService.buscarFuncionarioPorCpf(cpfFuncionario);

        if (funcionario == null) {
            System.out.println("Você não tem permissão para acessar esta funcionalidade.");
            return List.of();
        }

        try {
            List<Ticket> tickets = ticketService.buscarTodosTickets();
            if (tickets.isEmpty()) {
                System.out.println("Nenhum ticket encontrado.");
            } else {
                tickets.forEach(ticket -> System.out.println("Ticket " + "Veículo: " + ticket.getVeiculo().getPlaca() + ", Valor: " + ticket.getValor()));
            }
            return tickets;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return List.of();
        }
    }

    private List<Ticket> listarMeusTickets(Scanner scanner, TicketService ticketService) {
        System.out.println("\n--- LISTA DE TICKETS ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            List<Ticket> tickets = ticketService.buscarTicketPorCpf(cpf);
            if (tickets.isEmpty()) {
                System.out.println("Nenhum ticket encontrado para o cliente com CPF " + cpf);
            } else {
                tickets.forEach(ticket -> System.out.println("Ticket " + "Veículo: " + ticket.getVeiculo().getPlaca() + ", Valor: " + ticket.getValor()));
            }
            return tickets;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return List.of();
        }
    }

    private List<Veiculo> listarMeusVeiculos(Scanner scanner, ClienteService clienteService) {
        System.out.println("\n--- LISTA DE VEÍCULOS ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            var cliente = clienteService.buscarClientePorCpf(cpf);
            List<Veiculo> veiculos = cliente.getVeiculos();
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo cadastrado para o cliente " + cliente.getName());
            } else {
                veiculos.forEach(veiculo -> System.out.println("Placa: " + veiculo.getPlaca() + ", Modelo: " + veiculo.getModelo()));
            }
            return veiculos;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return List.of();
        }
    }

    private void cadastrarEstacionamento(Scanner scanner, EstacionamentoService estacionamentoService) {
        System.out.println("\n--- CADASTRO DE ESTACIONAMENTO ---");
        var estacionamento = new Estacionamento();

        System.out.print("Nome: ");
        estacionamento.setNome(scanner.nextLine());

        System.out.println("Vaga: ");
        estacionamento.setVaga(scanner.nextLine());

        System.out.println("Andar: ");
        estacionamento.setAndar(scanner.nextLine());

        System.out.print("Bloco: ");
        estacionamento.setBloco(scanner.nextLine());

        estacionamento.setDisponivel(true);

        estacionamentoService.cadastrarEstacionamento(estacionamento);
        System.out.println("Estacionamento cadastrado com sucesso!");
    }

    private static void cadastrarCliente(Scanner scanner, ClienteService clienteService) {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        Cliente cliente = new Cliente();

        System.out.print("CPF: ");
        cliente.setCpf(scanner.nextLine());

        System.out.print("Nome: ");
        cliente.setName(scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.setTelefone(scanner.nextLine());

        clienteService.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarVeiculoParaCliente(Scanner scanner, ClienteService clienteService, VeiculoService veiculoService) {
        System.out.println("\n--- CADASTRO DE VEÍCULO ---");

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            var cliente = clienteService.buscarClientePorCpf(cpf);

            var veiculo = new Veiculo();

            System.out.print("Placa: ");
            veiculo.setPlaca(scanner.nextLine());

            System.out.print("Modelo: ");
            veiculo.setModelo(scanner.nextLine());

            System.out.print("Marca: ");
            veiculo.setMarca(scanner.nextLine());

            System.out.print("Cor: ");
            veiculo.setCor(scanner.nextLine());

            veiculo.setCliente(cliente);
            veiculoService.cadastrarVeiculo(veiculo);

            System.out.println("Veículo cadastrado com sucesso para o cliente " + cliente.getName());
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void  cadastarFuncionario(Scanner scanner, FuncionarioService funcionarioService) {
        System.out.println("\n--- CADASTRO DE FUNCIONÁRIO ---");
        var funcionario = new Funcionario();

        System.out.print("CPF: ");
        funcionario.setCpf(scanner.nextLine());

        System.out.print("Nome: ");
        funcionario.setName(scanner.nextLine());

        System.out.println("Telefone: ");
        funcionario.setTelefone(scanner.nextLine());

        funcionarioService.cadastrarFuncionario(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void criarTicket(Scanner scanner, VeiculoService veiculoService, ClienteService clienteService, FuncionarioService funcionarioService, TicketService ticketService, EstacionamentoService estacionamentoService) {
        System.out.println("\n--- CRIAÇÃO DE TICKET ---");

        System.out.println("Digite o CPF do funcionário: ");
        String cpfFuncionario = scanner.nextLine();
        var funcionario = funcionarioService.buscarFuncionarioPorCpf(cpfFuncionario);

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        var cliente = clienteService.buscarClientePorCpf(cpf);

        var veiculos = veiculoService.buscarVeiculoPorPlaca(cliente.getVeiculos().getFirst().getPlaca());

        System.out.println("Nome do Estacionamento: ");
        var estacionamento = estacionamentoService.buscarEstacionamentoPorNome(scanner.nextLine());

        System.out.println("Valor: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        System.out.println("Quantas horas o cliente vai ficar? ");
        int quantidadeHoras = Integer.parseInt(scanner.nextLine());

        LocalDateTime horaEntrada = LocalDateTime.now();
        LocalDateTime horaSaida = horaEntrada.plusHours(quantidadeHoras);

        var ticket = Ticket.builder()
                .funcionario(funcionario)
                .cliente(cliente)
                .veiculo(veiculos)
                .horaEntrada(horaEntrada)
                .estacionamento(estacionamento)
                .valor(valor)
                .horaSaida(horaSaida)
                .build();

        ticketService.abrirTicket(ticket);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("Ticket criado com sucesso!");
        System.out.println("Hora de entrada: " + horaEntrada.format(formatter));
        System.out.println("Hora prevista de saída: " + horaSaida.format(formatter));
    }

}
