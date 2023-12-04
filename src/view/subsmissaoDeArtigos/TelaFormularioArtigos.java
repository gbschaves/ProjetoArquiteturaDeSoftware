package view.subsmissaoDeArtigos;

import model.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaFormularioArtigos extends JFrame implements ActionListener {
    private JLabel lblTituloArtigo;
    private JTextField txtTitulo;
    private JLabel lblAutores;
    private JLabel lblNomes;
    private JLabel lblCpf;
    private JTextField txtNomeAutor;
    private JTextField txtCpfAutor;
    private JButton botaoAddAutor;
    private JLabel lblResumo;
    private JTextArea txtResumo;
    private JLabel lblPalavrasChave;
    private JTextField txtPalavrasChave;
    private JLabel lblEnvolveHumanos;
    private JRadioButton radioSim;
    private JRadioButton radioNao;
    private JButton botaoAvancar;
    private JLabel lblNumeroPlataformaBrasil;
    private JTextField txtNumeroPlataformaBrasil;
    private boolean artigoEnvolveSeresHumanos = false;
    private Area areaselecionada;
    private Subarea subareaSelecionada;
    private JPanel contentPane;
    private Pessoa pessoaLogada;
    private JButton botaoVoltar;
    private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

    public TelaFormularioArtigos(Area areaselecionada, Pessoa pessoaLogada, Subarea subareaSelecionada) {
        this.pessoaLogada = pessoaLogada;
        this.areaselecionada = areaselecionada;
        this.subareaSelecionada = subareaSelecionada;
        pessoas.add(pessoaLogada);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Formulário de artigos");
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        botaoVoltar = new JButton("\u2190");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoVoltar);

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoVoltar);
        contentPane.add(Box.createVerticalGlue());

        lblTituloArtigo = new JLabel("Título do Artigo:");
        txtTitulo = new JTextField(20);
        addToPanelWithLabel(contentPane, lblTituloArtigo, txtTitulo);

        lblAutores = new JLabel("Autores:");

        lblNomes = new JLabel("Nome:");
        txtNomeAutor = new JTextField(20);

        lblCpf = new JLabel("Cpf:");
        txtCpfAutor = new JTextField(20);

        botaoAddAutor = new JButton("Adicionar Autor");
        botaoAddAutor.addActionListener(this);
        styleButton(botaoAddAutor);
        addToPainelCriarAutor(contentPane, lblNomes, txtNomeAutor, lblCpf, txtCpfAutor, botaoAddAutor);

        lblResumo = new JLabel("Resumo:");
        txtResumo = new JTextArea(5, 20);
        addToPanelWithLabel(contentPane, lblResumo, txtResumo);

        lblPalavrasChave = new JLabel("Palavras-chave:");
        txtPalavrasChave = new JTextField(20);
        addToPanelWithLabel(contentPane, lblPalavrasChave, txtPalavrasChave);

        lblEnvolveHumanos = new JLabel("Envolve seres humanos?");
        radioSim = new JRadioButton("Sim");
        radioNao = new JRadioButton("Não");
        lblNumeroPlataformaBrasil = new JLabel("Número da Plataforma Brasil:");
        txtNumeroPlataformaBrasil = new JTextField(20);
        botaoAvancar = new JButton("Avançar");
        botaoAvancar.addActionListener(this);
        styleButton(botaoAvancar);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup group = new ButtonGroup();
        group.add(radioSim);
        group.add(radioNao);
        radioPanel.add(lblEnvolveHumanos);
        radioPanel.add(radioSim);
        radioPanel.add(radioNao);
        radioSim.addActionListener(this);
        radioNao.addActionListener(this);
        contentPane.add(radioPanel);
        addToPanelWithLabel(contentPane, lblNumeroPlataformaBrasil, txtNumeroPlataformaBrasil);
        lblNumeroPlataformaBrasil.setVisible(false);
        txtNumeroPlataformaBrasil.setVisible(false);
        addToPanel(contentPane, botaoAvancar);

        setResizable(false);
        setVisible(true);
    }

    private void addToPanelWithLabel(JPanel contentPane, JComponent label, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        if (field != null) {
            panel.add(field);
        }
        contentPane.add(panel);
    }

    private void addToPanel(JPanel contentPane, JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (field != null) {
            panel.add(field);
        }
        contentPane.add(panel);
    }

    private void addToPainelCriarAutor(JPanel contentPane, JComponent label, JComponent field, JComponent label2, JComponent field2, JComponent button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(field);
        panel.add(label2);
        panel.add(field2);
        panel.add(button);
        contentPane.add(panel);
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
        if (source.equals(botaoAddAutor)) {
            Autor autor = new Autor();
            Pessoa pessoa = new Pessoa();
            autor.setCpf(txtCpfAutor.getText());
            pessoa.setNome(txtNomeAutor.getText());
            pessoa.setAutor(autor);
            pessoas.add(pessoa);
            txtNomeAutor.setText("");
            txtCpfAutor.setText("");
        } else if (source.equals(radioNao)) {
            artigoEnvolveSeresHumanos = false;
            lblNumeroPlataformaBrasil.setVisible(false);
            txtNumeroPlataformaBrasil.setVisible(false);
        } else if (source.equals(radioSim)) {
            artigoEnvolveSeresHumanos = true;
            lblNumeroPlataformaBrasil.setVisible(true);
            txtNumeroPlataformaBrasil.setVisible(true);
        } else if (source.equals(botaoAvancar)) {
            Artigo artigo = new Artigo(txtTitulo.getText(), pessoas, txtResumo.getText(), txtPalavrasChave.getText(), artigoEnvolveSeresHumanos, txtNumeroPlataformaBrasil.getText());
            new TelaAutorResponsavel(artigo, areaselecionada, pessoaLogada, subareaSelecionada);
            setVisible(false);
            dispose();
        } else if (source == botaoVoltar) {
             new TelaSubmissaoArtigos(pessoaLogada);
             setVisible(false);
             dispose();
        }
    }
}
