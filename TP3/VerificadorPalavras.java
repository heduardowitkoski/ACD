package trie;

import java.io.*;
import java.util.Scanner;

public class VerificadorPalavras {
    private TrieST<Integer> dicionario;

    public VerificadorPalavras() {
        dicionario = new TrieST<>();
    }

    // Carregar palavras de um arquivo para a Trie
    public void carregarDicionario(String caminhoArquivo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String palavra;
            int indice = 0;
            while ((palavra = leitor.readLine()) != null) {
                dicionario.inserir(palavra.trim().toLowerCase(), indice++);
            }
            System.out.println("Dicionário carregado com sucesso.");
        } catch (IOException erro) {
            System.err.println("Erro ao carregar o dicionário: " + erro.getMessage());
        }
    }

    // Verificar se uma palavra existe no dicionário
    public boolean verificarPalavra(String palavra) {
        return dicionario.contem(palavra.toLowerCase());
    }

    // Sugerir palavras com o mesmo prefixo
    public Iterable<String> sugerirPalavras(String prefixo) {
        return dicionario.chavesComPrefixo(prefixo.toLowerCase());
    }

    public static void main(String[] args) {
        VerificadorPalavras verificador = new VerificadorPalavras();

        // Carregar um dicionário de exemplo (ajuste o caminho para um arquivo válido)
        verificador.carregarDicionario("/home/heduardo/Área de Trabalho/acdtrabalho3/dictionary.txt");

        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.print("Digite uma palavra para verificar (ou 'sair' para encerrar): ");
            String palavraInserida = entrada.nextLine().trim();

            if (palavraInserida.equalsIgnoreCase("sair")) {
                break;
            }

            if (verificador.verificarPalavra(palavraInserida)) {
                System.out.println("A palavra '" + palavraInserida + "' está correta.");
            } else {
                System.out.println("A palavra '" + palavraInserida + "' não está no dicionário.");

                System.out.println("Sugestões:");
                for (String sugestao : verificador.sugerirPalavras(palavraInserida)) {
                    System.out.println("  - " + sugestao);
                }
            }
        }

        entrada.close();
    }
}
