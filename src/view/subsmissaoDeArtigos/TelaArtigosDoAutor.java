package view.subsmissaoDeArtigos;

import controller.ControllerSubsmissaoDeArtigos;
import model.entities.Artigo;
import model.entities.Autor;
import model.entities.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaArtigosDoAutor extends JFrame implements ActionListener {
    private Artigo[] artigos;
    private JLabel lblTitulo;

    private JLabel tituloPagina;

    private JButton botaoSobrescrever;
    private JPanel contentPane;
    private  Pessoa pessoaLogada;
    public TelaArtigosDoAutor(ArrayList<Artigo> artigosDoAutor, Pessoa pessoaLogada) {
        this.pessoaLogada = pessoaLogada;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sobrescrever artigos");
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);


        tituloPagina = new JLabel("Lista de artigos que você pode editar");
        tituloPagina.setFont(new Font("Inter", Font.PLAIN, 24));
        contentPane.add(tituloPagina);

        for (Artigo artigo: artigosDoAutor){
            lblTitulo = new JLabel("Título: " + artigo.getTitulo());
            lblTitulo.setFont(new Font("Inter", Font.PLAIN, 14));
            botaoSobrescrever = new JButton("Sobrescrever");
            botaoSobrescrever.setActionCommand(String.valueOf(artigo.getIdArtigo()));
            botaoSobrescrever.addActionListener(this);
            addToPanelWithLabel(contentPane, lblTitulo, botaoSobrescrever);
            styleButton(botaoSobrescrever);
        }

        setResizable(false);
        setVisible(true);
    }
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }
    private void addToPanelWithLabel(JPanel contentPane, JComponent label, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        if (field != null) {
            panel.add(field);
        }
        contentPane.add(panel);
    }

    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            int artigoId = Integer.parseInt(button.getActionCommand());
            new TelaSobrescreverArtigo(artigoId, pessoaLogada);
            setVisible(false);
            dispose();
        }

    }

}
