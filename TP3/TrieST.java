package trie;

import java.util.HashMap;
import java.util.LinkedList;

/*
TrieST<Valor>: Define uma classe genérica, 
onde Valor representa o tipo associado a cada palavra 
(exemplo: Integer, String).*/
public class TrieST<Valor> { 
    private static class No {
        private Object valor;
        /*mapa associando caracteres (Character) a seus nós filhos. 
        Cada chave representa uma letra do alfabeto e aponta para o próximo nó7*/
        private HashMap<Character, No> proximos = new HashMap<>();
    }

    private final No raiz = new No();

    // Inserir uma palavra na Trie
    public void inserir(String chave, Valor valor) {
        No atual = raiz;
        for (char caractere : chave.toCharArray()) {
            atual.proximos.putIfAbsent(caractere, new No());
            atual = atual.proximos.get(caractere);
        }
        atual.valor = valor;
    }

    // Buscar uma palavra na Trie
    public Valor buscar(String chave) {
        No atual = raiz;
        for (char caractere : chave.toCharArray()) {
            if (!atual.proximos.containsKey(caractere)) {
                return null;
            }
            atual = atual.proximos.get(caractere);
        }
        return (Valor) atual.valor;
    }

    // Verificar se a Trie contém uma palavra
    public boolean contem(String chave) {
        return buscar(chave) != null;
    }

    // Obter todas as palavras com um prefixo
    public Iterable<String> chavesComPrefixo(String prefixo) {
        LinkedList<String> resultados = new LinkedList<>();
        No atual = raiz;
        for (char caractere : prefixo.toCharArray()) {
            if (!atual.proximos.containsKey(caractere)) {
                return resultados; // Retorna vazio se o prefixo não existir
            }
            atual = atual.proximos.get(caractere);
        }
        coletar(atual, new StringBuilder(prefixo), resultados);
        return resultados;
    }

    // Método auxiliar para coletar palavras
    private void coletar(No no, StringBuilder prefixo, LinkedList<String> resultados) {
        if (no == null) return;
        if (no.valor != null) resultados.add(prefixo.toString());
        for (char caractere : no.proximos.keySet()) {
            coletar(no.proximos.get(caractere), prefixo.append(caractere), resultados);
            prefixo.deleteCharAt(prefixo.length() - 1);
        }
    }
}
