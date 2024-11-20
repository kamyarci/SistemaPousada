package br.edu.fag;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FormularioHospedagem extends JFrame {
    private final JTextField campoNome;
    private final JTextField campoDias;

    public FormularioHospedagem() {
        setTitle("Controle de Hospedagem");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);


        JLabel nomeLabel = new JLabel("Nome do Hóspede:");
        nomeLabel.setBounds(10, 10, 150, 25);
        add(nomeLabel);

        campoNome = new JTextField();
        campoNome.setBounds(150, 10, 120, 25);
        add(campoNome);

        JLabel quartoLabel = new JLabel("Tipo de Quarto:");
        quartoLabel.setBounds(10, 40, 150, 25);
        add(quartoLabel);

        JButton escolherQuartoButton = new JButton("Escolha");
        escolherQuartoButton.setBounds(150, 40, 120, 25);
        add(escolherQuartoButton);

        JLabel diasLabel = new JLabel("Número de Dias:");
        diasLabel.setBounds(10, 70, 150, 25);
        add(diasLabel);

        campoDias = new JTextField();
        campoDias.setBounds(150, 70, 120, 25);
        add(campoDias);

        JButton botaoRegistrar = new JButton("Registrar");
        botaoRegistrar.setBounds(10, 110, 260, 25);
        add(botaoRegistrar);

        escolherQuartoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherTipoQuarto();
            }
        });

        botaoRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarHospedagem();
            }
        });
    }

    private String tipoQuartoSelecionado = "";

    private void escolherTipoQuarto() {
        String[] opcoes = {"Standard", "Suite"};
        int escolha = JOptionPane.showOptionDialog(this, "Escolha o tipo de quarto:",
                "Tipo de Quarto", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opcoes, opcoes[0]);

        if (escolha == 0) {
            tipoQuartoSelecionado = "Standard";
        } else if (escolha == 1) {
            tipoQuartoSelecionado = "Suite";
        } else {
            tipoQuartoSelecionado = "";
        }
    }

    private void registrarHospedagem() {
        try {
            String nome = campoNome.getText();
            int numeroDias = Integer.parseInt(campoDias.getText());

            TipoHospedagem hospedagem;
            if (tipoQuartoSelecionado.equalsIgnoreCase("Suite")) {
                hospedagem = new Suite(nome, tipoQuartoSelecionado, numeroDias);
            } else if (tipoQuartoSelecionado.equalsIgnoreCase("Standard")) {
                hospedagem = new QuartoStandard(nome, tipoQuartoSelecionado, numeroDias);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, escolha um tipo de quarto.");
                return;
            }
            double valorTotal = hospedagem.calcularValor();
            salvarDados(nome, tipoQuartoSelecionado, numeroDias, valorTotal);
            JOptionPane.showMessageDialog(this, "Hospedagem registrada com sucesso! Valor total: R$" + valorTotal);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para os dias.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados.");
        }
    }

    private void salvarDados(String nome, String tipoQuarto, int numeroDias, double valorTotal) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hospedagens.txt", true))) {
            writer.write("Nome: " + nome + ", Tipo de Quarto: " + tipoQuarto + ", Dias: " + numeroDias + ", Valor Total: R$" + valorTotal);
            writer.newLine();
        }
    }

}