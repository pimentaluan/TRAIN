package gui;

import core.Dicionario;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Toolkit;

public class TelaDicionario extends JFrame {
    private JTextField textField;
    private JButton buttonTraduzir, buttonBuscar;
    private JLabel label;
    private JLabel label_1;
    private JComboBox comboBox;
    private JLabel label_3;
    private JLabel label_4;
    private JTextArea textArea;
    private JLabel label_2;
    private JLabel label_5;

    private Dicionario dicionario;

    public TelaDicionario() {
    	setForeground(new Color(192, 192, 192));
    	setIconImage(Toolkit.getDefaultToolkit().getImage(TelaDicionario.class.getResource("/imagens/icon.jpeg")));
        getContentPane().setBackground(new Color(0, 128, 255));
        setBackground(new Color(192, 192, 192));
        setTitle("TRAIN");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBounds(87, 130, 204, 27);
        getContentPane().add(textField);
        textField.setColumns(10);

        buttonTraduzir = new JButton("Traduzir");
        buttonTraduzir.setForeground(new Color(255, 255, 255));
        buttonTraduzir.setBackground(new Color(0, 0, 0));
        buttonTraduzir.setFont(new Font("Dubai", Font.BOLD, 12));
        buttonTraduzir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                traduzirPalavra();
            }
        });
        buttonTraduzir.setBounds(87, 200, 100, 23);
        getContentPane().add(buttonTraduzir);

        buttonBuscar = new JButton("Buscar");
        buttonBuscar.setForeground(new Color(255, 255, 255));
        buttonBuscar.setBackground(new Color(0, 0, 0));
        buttonBuscar.setFont(new Font("Dubai", Font.BOLD, 12));
        buttonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPalavra();
            }
        });
        buttonBuscar.setBounds(200, 200, 100, 23);
        getContentPane().add(buttonBuscar);

        label = new JLabel("Insira a palavra");
        label.setFont(new Font("Dubai", Font.BOLD, 14));
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(87, 115, 150, 14);
        getContentPane().add(label);

        label_1 = new JLabel("Resultado:");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Dubai", Font.BOLD, 14));
        label_1.setBounds(87, 250, 150, 14);
        getContentPane().add(label_1);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Ingles", "Espanhol", "Alemao"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(88, 29, 100, 22);
        comboBox.setSelectedIndex(0);
        getContentPane().add(comboBox);

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
        textArea.setBounds(87, 275, 300, 50);
        textArea.setEditable(false);
        getContentPane().add(textArea);

        label_2 = new JLabel("Ingles");
        label_2.setBounds(157, 79, 59, 14);
        getContentPane().add(label_2);

        label_5 = new JLabel();
        label_5.setBounds(87, 59, 59, 46);
        getContentPane().add(label_5);

        try {
            dicionario = new Dicionario("Ingles");
            atualizarBandeira("Ingles");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o dicionário: " + e.getMessage());
        }
    }

    private void atualizarIdioma() {
        String idiomaSelecionado = comboBox.getSelectedItem().toString();
        label_2.setText(idiomaSelecionado);
        try {
            dicionario.setIdioma(idiomaSelecionado);
            atualizarBandeira(idiomaSelecionado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao mudar o idioma: " + e.getMessage());
        }
    }

    private void atualizarBandeira(String idioma) {
        String nomeArquivo = idioma.toLowerCase() + ".jpeg";

        try {
            ImageIcon bandeiraOriginal = new ImageIcon(getClass().getResource("/imagens/" + nomeArquivo));

            Image imagemRedimensionada = bandeiraOriginal.getImage().getScaledInstance(label_5.getWidth(), label_5.getHeight(), Image.SCALE_SMOOTH);
            label_5.setIcon(new ImageIcon(imagemRedimensionada));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a bandeira: " + nomeArquivo);
            e.printStackTrace();
        }
    }


    private void traduzirPalavra() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma palavra para traduzir.");
            return;
        }

        ArrayList<String> traducoes = dicionario.traduzirParaPortugues(termo);
        if (traducoes.isEmpty()) {
            traducoes = dicionario.traduzirParaIdioma(termo);
        }

        if (traducoes.isEmpty()) {
            textArea.setText("Nenhuma tradução encontrada.");
        } else {
            textArea.setText("Tradução: " + String.join(", ", traducoes));
        }
    }

    private void buscarPalavra() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma palavra para buscar.");
            return;
        }

        ArrayList<String> resultadosIdioma = dicionario.localizarPalavraIdioma(termo);

        ArrayList<String> resultadosPortugues = dicionario.localizarPalavraPortugues(termo);

        StringBuilder sugestoes = new StringBuilder();

        sugestoes.append("Sugestões em " + dicionario.getIdiomaCorrente() + ": " );
        if (resultadosIdioma.isEmpty()) {
            sugestoes.append("Nenhuma palavra encontrada.\n");
        } else {
            sugestoes.append(String.join(", ", resultadosIdioma)).append("\n");
        }

        sugestoes.append("Sugestões em Portugues: ");
        if (resultadosPortugues.isEmpty()) {
            sugestoes.append("Nenhuma palavra encontrada.");
        } else {
            sugestoes.append(String.join(", ", resultadosPortugues));
        }

        textArea.setText(sugestoes.toString());
    }


    public static void main(String[] args) {
        TelaDicionario tela = new TelaDicionario();
        tela.setVisible(true);
    }
}
