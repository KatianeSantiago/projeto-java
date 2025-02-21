package src;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    Produto novoProduto = new Produto(nome, preco, quantidade);
                    dao.criarProduto(novoProduto);
                    break;

                case 2:
                    List<Produto> produtos = dao.listarProdutos();
                    for (Produto p : produtos) {
                        System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() +
                                " | Preço: " + p.getPreco() + " | Quantidade: " + p.getQuantidade());
                    }
                    break;

                case 3:
                    System.out.print("ID do produto a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();

                    Produto produtoAtualizado = new Produto(idAtualizar, novoNome, novoPreco, novaQuantidade);
                    dao.atualizarProduto(produtoAtualizado);
                    break;

                case 4:
                    System.out.print("ID do produto a excluir: ");
                    int idExcluir = scanner.nextInt();
                    dao.excluirProduto(idExcluir);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
