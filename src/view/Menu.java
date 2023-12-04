package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JButton botaoLogin;
    private JButton botaoCadastro;

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro");
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        botaoCadastro = new JButton("Cadastre-se");
        botaoCadastro.addActionListener(this);
        botaoCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCadastro.setMaximumSize(new Dimension(200, 50));
        styleButton(botaoCadastro);

        botaoLogin = new JButton("Login");
        botaoLogin.addActionListener(this);
        botaoLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoLogin.setMaximumSize(new Dimension(200, 50));
        styleButton(botaoLogin);

        // Adiciona os botões ao painel com espaçamento
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoCadastro);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre os botões
        contentPane.add(botaoLogin);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(botaoCadastro)) {
            new TelaCadastroAutor();
            setVisible(false);
            dispose();
        } else {
            new TelaLoginAutor();
            setVisible(false);
            dispose();
        }
    }

}

