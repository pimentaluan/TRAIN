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
    private JLabel label_6; 

    private Dicionario dicionario;
    private JButton buttonBuscar_1;
    private JButton buttonTraduzir_1;

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
        textField.setBounds(87, 130, 332, 27);
        getContentPane().add(textField);
        textField.setColumns(10);

        buttonTraduzir = new JButton("Traduzir para português");
        buttonTraduzir.setForeground(new Color(255, 255, 255));
        buttonTraduzir.setBackground(new Color(0, 0, 0));
        buttonTraduzir.setFont(new Font("Dubai", Font.BOLD, 12));
        buttonTraduzir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                traduzirPalavraPPortugues();
            }
        });
        buttonTraduzir.setBounds(85, 201, 164, 23);
        getContentPane().add(buttonTraduzir);

        buttonBuscar = new JButton("Buscar em português");
        buttonBuscar.setForeground(new Color(255, 255, 255));
        buttonBuscar.setBackground(new Color(0, 0, 0));
        buttonBuscar.setFont(new Font("Dubai", Font.BOLD, 12));
        buttonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPalavraEmPortugues();
            }
        });
        buttonBuscar.setBounds(85, 172, 164, 23);
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
        textArea.setBounds(87, 275, 341, 50);
        textArea.setEditable(false);
        getContentPane().add(textArea);

        label_2 = new JLabel("Ingles");
        label_2.setBounds(167, 78, 59, 14);
        getContentPane().add(label_2);

        label_5 = new JLabel();
        label_5.setBounds(87, 59, 73, 46);
        getContentPane().add(label_5);
        
        label_6 = new JLabel(); // aqui é o label para msg do sistema
        label_6.setForeground(Color.WHITE);
        label_6.setFont(new Font("Dubai", Font.PLAIN, 12));
        label_6.setBounds(87, 335, 341, 20); // colocando posição abaixo da textArea
        getContentPane().add(label_6);

        buttonBuscar_1 = new JButton("Buscar em " + comboBox.getSelectedItem().toString());
        buttonBuscar_1.setForeground(Color.WHITE);
        buttonBuscar_1.setFont(new Font("Dubai", Font.BOLD, 12));
        buttonBuscar_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPalavraNoIdioma();
            }
        });
        buttonBuscar_1.setBackground(Color.BLACK);
        buttonBuscar_1.setBounds(256, 171, 164, 23);
        getContentPane().add(buttonBuscar_1);
        
        buttonTraduzir_1 = new JButton("Traduzir para " + comboBox.getSelectedItem().toString());
        buttonTraduzir_1.setForeground(Color.WHITE);
        buttonTraduzir_1.setFont(new Font("Dubai", Font.BOLD, 12));
        buttonTraduzir_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                traduzirPalavraPIdioma();
            }
        });
        buttonTraduzir_1.setBackground(Color.BLACK);
        buttonTraduzir_1.setBounds(256, 201, 164, 23);
        getContentPane().add(buttonTraduzir_1);

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

        buttonBuscar_1.setText("Buscar em " + idiomaSelecionado);
        buttonTraduzir_1.setText("Traduzir para " + idiomaSelecionado);

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

    private void traduzirPalavraPPortugues() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
            label_6.setText("Por favor, insira uma palavra para traduzir para português.");
            textArea.setText(""); 
            return;
        }

        ArrayList<String> traducoes = dicionario.traduzirParaPortugues(termo);
        
        if (traducoes.isEmpty()) {
            textArea.setText(""); 
            label_6.setText("Nenhuma tradução para português encontrada.");
        } else {
        	textArea.setText("Tradução para português: " + String.join(", ", traducoes) +
                    "\nTotal de resultados: " + traducoes.size());            
        	label_6.setText(""); 
        }
    }
    
    private void traduzirPalavraPIdioma() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
        	label_6.setText("Por favor, insira uma palavra para traduzir para " + comboBox.getSelectedItem().toString());
            return;
        }

        ArrayList<String> traducoes = dicionario.traduzirParaIdioma(termo);
        
        if (traducoes.isEmpty()) {
        	
        	textArea.setText("");
            label_6.setText("Nenhuma tradução em " + comboBox.getSelectedItem().toString() + " encontrada.");
        } else {
        	textArea.setText("Tradução para " + comboBox.getSelectedItem().toString() + ": " + String.join(", ", traducoes) +
                    "\nTotal de resultados: " + traducoes.size());
        }
    }



    private void buscarPalavraEmPortugues() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
            label_6.setText("Por favor, insira uma palavra para buscar em português.");
            textArea.setText("");
            return;
        }

        ArrayList<String> resultadosPortugues = dicionario.localizarPalavraPortugues(termo);

        if (resultadosPortugues.isEmpty()) {
            label_6.setText("Nenhuma palavra encontrada em português.");
            textArea.setText("");
        } else {
            StringBuilder sugestoes = new StringBuilder();
            sugestoes.append("Sugestões em Português: ");
            sugestoes.append(String.join(", ", resultadosPortugues));
            sugestoes.append("\nTotal de resultados: " + resultadosPortugues.size());
            textArea.setText(sugestoes.toString());

            label_6.setText("");
            textArea.setText(sugestoes.toString());
        }
    }
    
    private void buscarPalavraNoIdioma() {
        String termo = textField.getText().trim();
        if (termo.isEmpty()) {
            label_6.setText("Por favor, insira uma palavra para buscar no " + comboBox.getSelectedItem().toString());
            
            textArea.setText("");
            return;
        }

        ArrayList<String> resultadosIdioma = dicionario.localizarPalavraIdioma(termo);

        if (resultadosIdioma.isEmpty()) {
            label_6.setText("Nenhuma palavra encontrada em " + comboBox.getSelectedItem().toString() + ".");
            textArea.setText("");
        } else {
            StringBuilder sugestoes = new StringBuilder();
            sugestoes.append("Sugestões em " + comboBox.getSelectedItem().toString() + ": ");
            sugestoes.append(String.join(", ", resultadosIdioma));
            sugestoes.append("\nTotal de resultados: " + resultadosIdioma.size());
            textArea.setText(sugestoes.toString());
            label_6.setText(""); 
            textArea.setText(sugestoes.toString());
        }
    }





    public static void main(String[] args) {
        TelaDicionario tela = new TelaDicionario();
        tela.setVisible(true);
    }
}
