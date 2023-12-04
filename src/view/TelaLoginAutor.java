package view;

import controller.ControllerSubsmissaoDeArtigos;
import model.entities.Pessoa;
import model.error.ExcecaoLoginIncorreto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginAutor extends JFrame implements ActionListener {
    private JLabel lblLogin;
    private JTextField txtLogin;
    private JLabel lblSenha;
    private JPasswordField txtSenha;
    private JPanel contentPane;
    private JButton botaoLogar;
    private JLabel lblContaInvalida;

    private JButton botaoVoltar;

    public TelaLoginAutor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.decode("#f4f4f4")); // Cor de fundo

        setContentPane(contentPane);

        // Adiciona um botão de voltar
        botaoVoltar = new JButton("\u2190");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoVoltar);

        // Adiciona o botão de voltar ao painel
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoVoltar);
        contentPane.add(Box.createVerticalGlue());

        lblLogin = new JLabel("Login:");
        txtLogin = new JTextField(20);
        addToPanelWithLabel(contentPane, lblLogin, txtLogin);

        lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(20);
        addToPanelWithLabel(contentPane, lblSenha, txtSenha);

        lblContaInvalida = new JLabel("");
        lblContaInvalida.setForeground(Color.red); // Cor do texto de aviso
        lblContaInvalida.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToPanel(contentPane, lblContaInvalida);
        lblContaInvalida.setVisible(false);

        botaoLogar = new JButton("Logar");
        botaoLogar.addActionListener(this);
        botaoLogar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoLogar);

        // Adiciona os componentes ao painel com espaçamento
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoLogar);
        contentPane.add(Box.createVerticalGlue());

        setVisible(true);
    }

    private void addToPanelWithLabel(JPanel contentPane, JComponent label, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.decode("#f4f4f4")); // Cor de fundo do painel
        panel.add(label);
        if (field != null) {
            panel.add(field);
        }
        contentPane.add(panel);
    }

    private void addToPanel(JPanel contentPane, JComponent label) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.decode("#f4f4f4")); // Cor de fundo do painel
        if (label != null) {
            panel.add(label);
        }
        contentPane.add(panel);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();


        if (source == botaoLogar) {
            try {

                ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
                Pessoa pessoa = controllerSubsmissaoDeArtigos.login(txtLogin.getText(), String.valueOf(txtSenha.getPassword()));

                if (pessoa.getNome() != null) {
                    new MenuLogado(pessoa);
                    setVisible(false);
                    dispose();
                }
            } catch (ExcecaoLoginIncorreto e) {
                lblContaInvalida.setText(e.getMessage());
                lblContaInvalida.setVisible(true);
                txtLogin.setText("");
                txtSenha.setText("");
            }
        } else if (source == botaoVoltar) {
             new Menu();
             setVisible(false);
             dispose();
        }
    }


}
