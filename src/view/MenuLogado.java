package view;

import model.entities.Pessoa;
import view.revisaoPorPares.TelaEscolherAreaAvaliacao;
import view.subsmissaoDeArtigos.TelaSubmissaoArtigos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLogado extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JButton botaoRevisar;
    private JButton botaoSubmissao;
    private Pessoa pessoaLogada;

    public MenuLogado(Pessoa pessoa) {
        this.pessoaLogada = pessoa;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Logado");
        setSize(800, 500);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.decode("#f4f4f4")); // Cor de fundo

        setContentPane(contentPane);

        botaoSubmissao = new JButton("Submeter Artigos");
        botaoSubmissao.addActionListener(this);
        botaoSubmissao.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoSubmissao);

        botaoRevisar = new JButton("Revisar Artigos");
        botaoRevisar.addActionListener(this);
        botaoRevisar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoRevisar);

        // Adiciona os botões ao painel com espaçamento
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoSubmissao);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre os botões
        contentPane.add(botaoRevisar);
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
        if (source.equals(botaoSubmissao)) {
            new TelaSubmissaoArtigos(pessoaLogada);
            setVisible(false);
            dispose();
        } else {
            new TelaEscolherAreaAvaliacao(pessoaLogada);
            setVisible(false);
            dispose();
        }
    }


}
