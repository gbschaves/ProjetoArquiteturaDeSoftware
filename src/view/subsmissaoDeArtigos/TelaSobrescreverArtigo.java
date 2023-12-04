package view.subsmissaoDeArtigos;

import controller.ControllerSubsmissaoDeArtigos;
import model.entities.Artigo;
import model.entities.Pessoa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSobrescreverArtigo extends JFrame implements ActionListener  {
    private JLabel lblTituloArtigo;
    private JTextField txtTitulo;
    private JLabel lblResumo;
    private JTextArea txtResumo;
    private JLabel lblPalavrasChave;
    private JTextField txtPalavrasChave;
    private JLabel lblEnvolveHumanos;
    private JRadioButton radioSim;
    private JRadioButton radioNao;
    private JButton botaoSobrescrever;
    private JLabel lblNumeroPlataformaBrasil;
    private JTextField txtNumeroPlataformaBrasil;
    private boolean artigoEnvolveSeresHumanos;
    private Artigo artigo;
    private JPanel contentPane;
    private Pessoa pessoaLogada;
    private Integer artigoId;
    public TelaSobrescreverArtigo(Integer artigoId, Pessoa pessoaLogada) {
        this.artigoId = artigoId;
        this.pessoaLogada = pessoaLogada;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
        artigo = controllerSubsmissaoDeArtigos.getArtigoPeloId(artigoId);


        lblTituloArtigo = new JLabel("Título do Artigo:");
        txtTitulo = new JTextField(20);
        txtTitulo.setText(artigo.getTitulo());
        addToPanelWithLabel(contentPane, lblTituloArtigo, txtTitulo);
        lblResumo = new JLabel("Resumo:");
        txtResumo = new JTextArea(5, 20);
        txtResumo.setText(artigo.getResumo());
        addToPanelWithLabel(contentPane, lblResumo, txtResumo);

        lblPalavrasChave = new JLabel("Palavras-chave:");
        txtPalavrasChave = new JTextField(20);
        txtPalavrasChave.setText(artigo.getPalavrasChave());
        addToPanelWithLabel(contentPane, lblPalavrasChave, txtPalavrasChave);

        lblEnvolveHumanos = new JLabel("Envolve seres humanos?");
        radioSim = new JRadioButton("Sim");
        radioNao = new JRadioButton("Não");
        lblNumeroPlataformaBrasil = new JLabel("Número da Plataforma Brasil:");
        txtNumeroPlataformaBrasil = new JTextField(20);
        txtNumeroPlataformaBrasil.setText(artigo.getProcessoPlataformaBrasil());
        botaoSobrescrever = new JButton("Sobrescrever");
        botaoSobrescrever.addActionListener(this);
        styleButton(botaoSobrescrever);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup group = new ButtonGroup();
        group.add(radioSim);
        group.add(radioNao);
        radioPanel.add(lblEnvolveHumanos);
        radioPanel.add(radioSim);
        radioPanel.add(radioNao);
        radioSim.setSelected(artigo.isEnvolveHumano());
        radioNao.setSelected(!artigo.isEnvolveHumano());
        radioSim.addActionListener(this);
        radioNao.addActionListener(this);
        contentPane.add(radioPanel);
        addToPanelWithLabel(contentPane, lblNumeroPlataformaBrasil, txtNumeroPlataformaBrasil);
        lblNumeroPlataformaBrasil.setVisible(artigo.isEnvolveHumano());
        txtNumeroPlataformaBrasil.setVisible(artigo.isEnvolveHumano());
        addToPanel(contentPane, botaoSobrescrever);

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

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
         if (source.equals(radioNao)) {
            artigo.setEnvolveHumano(false);
             txtNumeroPlataformaBrasil.setText("");
            lblNumeroPlataformaBrasil.setVisible(false);
            txtNumeroPlataformaBrasil.setVisible(false);
        } else if (source.equals(radioSim)) {
            artigo.setEnvolveHumano(true);
            lblNumeroPlataformaBrasil.setVisible(true);
            txtNumeroPlataformaBrasil.setVisible(true);
        } else if (source.equals(botaoSobrescrever)) {
            Artigo artigoSobrescrito = new Artigo(artigo.getIdArtigo(), txtTitulo.getText(), txtResumo.getText(), txtPalavrasChave.getText(), radioSim.isSelected(), txtNumeroPlataformaBrasil.getText());
            ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
            controllerSubsmissaoDeArtigos.sobrescreveArtigo(artigoSobrescrito);
            new TelaSubmissaoArtigos(pessoaLogada);
            setVisible(false);
            dispose();
        }
    }
}