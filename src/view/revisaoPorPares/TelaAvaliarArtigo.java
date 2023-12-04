package view.revisaoPorPares;

import controller.ControllerRevisaoDeArtigos;
import model.entities.*;
import view.MenuLogado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAvaliarArtigo extends JFrame implements ActionListener {

    private JPanel contentPane;

    private JLabel lblTituloArtigo;
    private JLabel txtTitulo;

    private JLabel lblResumo;
    private JTextArea txtResumo;

    private JLabel lblPalavrasChave;
    private JLabel txtPalavrasChave;

    private JLabel lblNota;
    private JSpinner spinnerNota;

    private JLabel lblComentarios;
    private JTextArea txtComentarios;

    private JCheckBox chkPreservarAnonimato;

    private JButton btnAvaliar;

    private Artigo artigo;

    private Pessoa pessoaLogada;

    private JButton botaoVoltar;
    private Area area;
    private Subarea subarea;
    public TelaAvaliarArtigo(Artigo artigo, Pessoa pessoaLogada, Area area, Subarea subarea) {
        this.area = area;
        this.subarea = subarea;

        this.artigo = artigo;
        this.pessoaLogada = pessoaLogada;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setTitle("Avaliação de Artigo");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(0, 2, 10, 10));




        lblTituloArtigo = new JLabel("Título do Artigo:");
        txtTitulo = new JLabel(artigo.getTitulo());
        addToPanelWithLabel(contentPane, lblTituloArtigo, txtTitulo);

        lblResumo = new JLabel("Resumo:");
        txtResumo = new JTextArea(artigo.getResumo());
        txtResumo.setEditable(false);
        txtResumo.setLineWrap(true);
        JScrollPane resumoScrollPane = new JScrollPane(txtResumo);
        addToPanelWithLabel(contentPane, lblResumo, resumoScrollPane);

        lblPalavrasChave = new JLabel("Palavras-chave:");
        txtPalavrasChave = new JLabel(artigo.getPalavrasChave());
        addToPanelWithLabel(contentPane, lblPalavrasChave, txtPalavrasChave);

        lblNota = new JLabel("Nota:");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 10, 1);
        spinnerNota = new JSpinner(spinnerModel);
        addToPanelWithLabel(contentPane, lblNota, spinnerNota);

        lblComentarios = new JLabel("Comentários do Revisor:");
        txtComentarios = new JTextArea();
        txtComentarios.setLineWrap(true);
        JScrollPane comentariosScrollPane = new JScrollPane(txtComentarios);
        addToPanelWithLabel(contentPane, lblComentarios, comentariosScrollPane);

        chkPreservarAnonimato = new JCheckBox("Preservar Anonimato do Revisor");
        addToPanel(contentPane, new JPanel()); // placeholder for alignment
        addToPanel(contentPane, chkPreservarAnonimato);

        botaoVoltar = new JButton("\u2190");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoVoltar);


        btnAvaliar = new JButton("Avaliar Artigo");
        btnAvaliar.addActionListener(this);
        styleButton(btnAvaliar);

        addToPanelWithLabel(contentPane, botaoVoltar, btnAvaliar);

        setContentPane(contentPane);

        setResizable(false);
        setVisible(true);
    }

    private void addToPanelWithLabel(JPanel panel, JComponent label, JComponent field) {
        panel.add(label);
        panel.add(field);
    }


    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }
    private void addToPanel(JPanel panel, JComponent component) {
        panel.add(component);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAvaliar) {
            int nota = (int) spinnerNota.getValue();
            String comentario = txtComentarios.getText();
            boolean preservarAnonimato = chkPreservarAnonimato.isSelected();

            Avaliacao avaliacao = new Avaliacao(nota, comentario);

            ControllerRevisaoDeArtigos controllerRevisaoDeArtigos = new ControllerRevisaoDeArtigos();

            controllerRevisaoDeArtigos.avaliarArtigo(pessoaLogada, artigo, avaliacao);

            new MenuLogado(pessoaLogada);
            setVisible(false);
            dispose();
        } else if (e.getSource() == botaoVoltar) {
            new TelaRevisaoDeArtigos(area, subarea, pessoaLogada);
            setVisible(false);
            dispose();
        }
    }


}


