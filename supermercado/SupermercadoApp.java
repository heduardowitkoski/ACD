// Classe Produto
public class Produto {
    private String descricao;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String descricao, double preco, int quantidadeEstoque) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= quantidadeEstoque) {
            quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }
}

// Classe ItemPedido
public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }
}

// Classe Pedido
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemPedido::calcularSubtotal).sum();
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }
}

// Classe Cliente
public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}

// Classe Principal
import java.util.Scanner;

public class SupermercadoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Produto[] produtos = {
            new Produto("ARROZ", 5.50, 100),
            new Produto("FEIJÃO", 7.20, 80),
            new Produto("FARINHA", 4.30, 50),
            new Produto("LEITE", 3.00, 200)
        };

        Pedido pedidoAtual = null;

        while (true) {
            System.out.println("Menu de Opções:");
            System.out.println("1 - Novo pedido");
            System.out.println("2 - Realizar pagamento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.nextLine();

                    Cliente cliente = new Cliente(nome, cpf);
                    pedidoAtual = new Pedido(cliente);

                    while (true) {
                        System.out.println("Produtos disponíveis:");
                        for (int i = 0; i < produtos.length; i++) {
                            System.out.printf("%d - %s (R$%.2f, Estoque: %d)%n",
                                    i + 1, produtos[i].getDescricao(), produtos[i].getPreco(), produtos[i].getQuantidadeEstoque());
                        }

                        System.out.print("Escolha um produto pelo número (ou 0 para finalizar): ");
                        int escolhaProduto = scanner.nextInt();

                        if (escolhaProduto == 0) break;

                        if (escolhaProduto < 1 || escolhaProduto > produtos.length) {
                            System.out.println("Produto inválido.");
                            continue;
                        }

                        Produto produtoEscolhido = produtos[escolhaProduto - 1];

                        System.out.print("Digite a quantidade desejada: ");
                        int quantidade = scanner.nextInt();

                        if (produtoEscolhido.reduzirEstoque(quantidade)) {
                            pedidoAtual.adicionarItem(new ItemPedido(produtoEscolhido, quantidade));
                            System.out.println("Item adicionado ao pedido.");
                        } else {
                            System.out.println("Quantidade insuficiente no estoque.");
                        }
                    }
                    break;

                case 2:
                    if (pedidoAtual == null) {
                        System.out.println("Nenhum pedido foi iniciado.");
                        break;
                    }

                    System.out.printf("Cliente: %s (CPF: %s)%n", pedidoAtual.getCliente().getNome(), pedidoAtual.getCliente().getCpf());
                    System.out.println("Itens do pedido:");
                    for (ItemPedido item : pedidoAtual.getItens()) {
                        System.out.printf("- %s: %d x R$%.2f = R$%.2f%n",
                                item.getProduto().getDescricao(),
                                item.getQuantidade(),
                                item.getProduto().getPreco(),
                                item.calcularSubtotal());
                    }

                    double total = pedidoAtual.calcularTotal();
                    System.out.printf("Total do pedido: R$%.2f%n", total);

                    System.out.print("Forma de pagamento (DINHEIRO, CHEQUE, CARTÃO): ");
                    String formaPagamento = scanner.next();
                    System.out.printf("Pagamento realizado com %s. Obrigado pela compra!%n", formaPagamento);

                    pedidoAtual = null; // Finalizar o pedido atual
                    break;

                case 0:
                    System.out.println("Saindo da aplicação...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
