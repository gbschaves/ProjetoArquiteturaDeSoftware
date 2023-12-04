package view;

import controller.ControllerSubsmissaoDeArtigos;
import model.entities.Autor;
import model.entities.Pessoa;
import model.entities.Revisor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroAutor  extends JFrame implements ActionListener {
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblTelefone;
    private JTextField txtTelefone;

    private JLabel eBrasileiro;

    private JRadioButton radioSim;
    private JRadioButton radioNao;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JLabel lblRne;
    private JTextField txtRne;

    private JLabel lblLattes;
    private JTextField txtLattes;
    private JLabel lblInsituicao;
    private JTextField txtInstituicao;
    private JLabel lblResearchId;
    private JTextField txtResearchId;
    private JLabel lblCadastro;
    private JTextField txtCadastro;
    private JLabel lblSenha;
    private JPasswordField txtSenha;
    private JPanel contentPane;
    private JButton botaoCadastrar;

    private JButton botaoVoltar;

    private Pessoa pessoa;

    private boolean nacionalidadeBrasileira = false;
    public TelaCadastroAutor()  {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro");
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);


        botaoVoltar = new JButton("\u2190");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoVoltar);

        // Adiciona o botão de voltar ao painel
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoVoltar);
        contentPane.add(Box.createVerticalGlue());

        lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        addToPanelWithLabel(contentPane, lblNome, txtNome);

        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        addToPanelWithLabel(contentPane, lblEmail, txtEmail);

        lblTelefone = new JLabel("Telefone:");
        txtTelefone = new JTextField(20);
        addToPanelWithLabel(contentPane, lblTelefone, txtTelefone);

        eBrasileiro = new JLabel("Nacionalidade brasileira?");
        radioSim = new JRadioButton("Sim");
        radioNao = new JRadioButton("Não");
        lblCpf = new JLabel("CPF:");
        txtCpf = new JTextField(20);
        lblRne = new JLabel("RNE:");
        txtRne = new JTextField(20);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup group = new ButtonGroup();
        group.add(radioSim);
        group.add(radioNao);
        radioPanel.add(eBrasileiro);
        radioPanel.add(radioSim);
        radioPanel.add(radioNao);
        radioSim.addActionListener(this);
        radioNao.addActionListener(this);
        contentPane.add(radioPanel);
        addToPanelWithLabel(contentPane, lblCpf, txtCpf);
        addToPanelWithLabel(contentPane, lblRne, txtRne);
        lblCpf.setVisible(false);
        txtCpf.setVisible(false);
        lblRne.setVisible(false);
        txtRne.setVisible(false);

        lblLattes = new JLabel("Lattes:");
        txtLattes = new JTextField(20);
        addToPanelWithLabel(contentPane, lblLattes, txtLattes);

        lblInsituicao = new JLabel("Instituição que trabalha:");
        txtInstituicao = new JTextField(20);
        addToPanelWithLabel(contentPane, lblInsituicao, txtInstituicao);

        lblResearchId = new JLabel("Research id:");
        txtResearchId = new JTextField(20);
        addToPanelWithLabel(contentPane, lblResearchId, txtResearchId);

        lblCadastro = new JLabel("Cadastro:");
        txtCadastro = new JTextField(20);
        addToPanelWithLabel(contentPane, lblCadastro, txtCadastro);

        lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(20);
        addToPanelWithLabel(contentPane, lblSenha, txtSenha);


        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this);
        styleButton(botaoCadastrar);
        contentPane.add(botaoCadastrar);



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

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }



    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == botaoCadastrar) {
            Autor autor = new Autor(txtCpf.getText());
            Revisor revisor = new Revisor(txtTelefone.getText(), txtCpf.getText(), txtRne.getText(), txtLattes.getText(), txtInstituicao.getText(), txtResearchId.getText());
           pessoa = new Pessoa(txtNome.getText(), txtEmail.getText(), txtCadastro.getText(), String.valueOf(txtSenha.getPassword()));

            ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
            pessoa = controllerSubsmissaoDeArtigos.cadastroPessoa(pessoa);
            pessoa.setAutor(autor);
            pessoa.setRevisor(revisor);

            controllerSubsmissaoDeArtigos.cadastroAutor(pessoa);
            controllerSubsmissaoDeArtigos.cadastroRevisor(pessoa);

            new TelaLoginAutor();
            setVisible(false);
            dispose();
        } else if (source == botaoVoltar) {
             new Menu();
             setVisible(false);
             dispose();
        } else if (source.equals(radioNao)) {
            nacionalidadeBrasileira = false;
            lblRne.setVisible(true);
            txtRne.setVisible(true);
            lblCpf.setVisible(false);
            txtCpf.setVisible(false);
            txtCpf.setText(null);
        } else if (source.equals(radioSim)) {
            nacionalidadeBrasileira = true;
            lblRne.setVisible(false);
            txtRne.setVisible(false);
            lblCpf.setVisible(true);
            txtCpf.setVisible(true);
            txtRne.setText(null);
        }
    }
}
