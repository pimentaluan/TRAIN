package gui;

import core.Dicionario;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaDicionario extends JFrame {
    private JTextField textField;
    private JButton button;
    private JLabel label;
    private JLabel label_1;
    private JComboBox comboBox;
    private JLabel label_3;
    private JLabel label_4;
    private JTextArea textArea;
    private JLabel label_2;
    private JLabel label_5; // Para exibir a bandeira

    private Dicionario dicionario; // Instância da classe Dicionario

    public TelaDicionario() {
        getContentPane().setBackground(new Color(0, 128, 255));
        setBackground(new Color(192, 192, 192));
        setTitle("TRAIN");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Campo de entrada de texto
        textField = new JTextField();
        textField.setBounds(87, 130, 204, 27);
        getContentPane().add(textField);
        textField.setColumns(10);

        // Botão de tradução
        button = new JButton("Traduzir");
        button.setForeground(new Color(255, 255, 255));
        button.setBackground(new Color(0, 0, 0));
        button.setFont(new Font("Dubai", Font.BOLD, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                traduzirPalavra();
            }
        });
        button.setBounds(137, 200, 100, 23);
        getContentPane().add(button);

        // Rótulos e informações adicionais
        label = new JLabel("Insira a palavra");
        label.setFont(new Font("Dubai", Font.BOLD, 14));
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(87, 115, 150, 14);
        getContentPane().add(label);

        label_1 = new JLabel("Tradução:");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Dubai", Font.BOLD, 14));
        label_1.setBounds(87, 250, 150, 14);
        getContentPane().add(label_1);

        // ComboBox para escolha do idioma
        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Inglês", "Espanhol", "Alemão"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(88, 29, 100, 22);
        comboBox.setSelectedIndex(0);
        getContentPane().add(comboBox);

        // Listener para mudança de idioma
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarIdioma();
            }
        });

        label_3 = new JLabel("Escolha o idioma");
        label_3.setForeground(Color.WHITE);
        label_3.setFont(new Font("Dubai", Font.BOLD, 14));
        label_3.setBounds(87, 11, 150, 14);
        getContentPane().add(label_3);

        textArea = new JTextArea();
        textArea.setBounds(87, 275, 204, 22);
        textArea.setEditable(false);
        getContentPane().add(textArea);

        label_2 = new JLabel("Inglês");
        label_2.setBounds(157, 79, 59, 14);
        getContentPane().add(label_2);

        // Label para exibir a bandeira
        label_5 = new JLabel();
        label_5.setBounds(87, 59, 59, 46); // Define a posição e tamanho da label
        getContentPane().add(label_5);

        // Inicializa o dicionário com o idioma padrão
        try {
            dicionario = new Dicionario("Inglês");
            atualizarBandeira("Inglês"); // Exibe a bandeira inicial
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o dicionário: " + e.getMessage());
        }
    }

    // Atualiza o idioma com base no ComboBox
    private void atualizarIdioma() {
        String idiomaSelecionado = comboBox.getSelectedItem().toString();
        label_2.setText(idiomaSelecionado);
        try {
            dicionario.setIdioma(idiomaSelecionado);
            atualizarBandeira(idiomaSelecionado); // Atualiza a bandeira com o idioma selecionado
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao mudar o idioma: " + e.getMessage());
        }
    }

    // Atualiza a bandeira exibida na label_5
    private void atualizarBandeira(String idioma) {
        // Ajusta o nome do arquivo para evitar problemas com acentos
        String nomeArquivo = idioma.toLowerCase().replace("ê", "e") + ".jpeg";

        // Caminho completo da imagem
        String caminhoImagem = "src/imagens/" + nomeArquivo;

        try {
            // Carrega e redimensiona a imagem
            ImageIcon bandeiraOriginal = new ImageIcon(caminhoImagem);
            Image imagemRedimensionada = bandeiraOriginal.getImage().getScaledInstance(label_5.getWidth(), label_5.getHeight(), Image.SCALE_SMOOTH);
            label_5.setIcon(new ImageIcon(imagemRedimensionada));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a bandeira: " + caminhoImagem);
        }
    }

    // Traduz a palavra digitada
    private void traduzirPalavra() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma palavra para traduzir.");
            return;
        }

        // Tenta traduzir automaticamente, determinando o idioma da palavra
        ArrayList<String> traducoes = dicionario.traduzirParaPortugues(termo);
        if (traducoes.isEmpty()) {
            // Caso não encontre no idioma corrente, tenta traduzir do português para o idioma
            traducoes = dicionario.traduzirParaIdioma(termo);
        }

        if (traducoes.isEmpty()) {
            textArea.setText("Nenhuma tradução encontrada.");
        } else {
            textArea.setText(String.join(", ", traducoes));
        }
    }

    public static void main(String[] args) {
        TelaDicionario tela = new TelaDicionario();
        tela.setVisible(true);
    }
}
