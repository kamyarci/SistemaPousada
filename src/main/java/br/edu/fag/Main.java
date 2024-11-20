package br.edu.fag;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioHospedagem form = new FormularioHospedagem();
            form.setVisible(true);
        });
    }
}