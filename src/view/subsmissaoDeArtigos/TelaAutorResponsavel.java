package view.subsmissaoDeArtigos;

import controller.ControllerSubsmissaoDeArtigos;
import model.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class TelaAutorResponsavel extends JFrame implements ActionListener {
    private JLabel lblIndicarAutorResponsavel;
    private JLabel lblTelefone;
    private JTextField txtTelefone;
    private JButton btnSubmeterArtigo;
    private JButton botaoVoltar;
    private JComboBox<String> comboBox;
    private Artigo artigo;
    private ArrayList<Pessoa> pessoas;
    private Area areaSelecionada;
    private Subarea subareaSelecionada;
    private Pessoa pessoaLogada;
    public TelaAutorResponsavel(Artigo artigo, Area areaSelecionada, Pessoa pessoaLogada, Subarea subareaSelecionada) {
        this.pessoaLogada = pessoaLogada;
        this.artigo = artigo;
        this.areaSelecionada = areaSelecionada;
        this.subareaSelecionada = subareaSelecionada;
        this.pessoas = artigo.getPessoas();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Indicar autor responsável");
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        botaoVoltar = new JButton("\u2190");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoVoltar);

        contentPane.add(Box.createVerticalGlue());
        contentPane.add(botaoVoltar);
        contentPane.add(Box.createVerticalGlue());

        lblIndicarAutorResponsavel = new JLabel("Indique o autor responsável");
        contentPane.add(lblIndicarAutorResponsavel);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Selecione um autor");
        for (Pessoa pessoa: pessoas) {
            model.addElement(pessoa.getNome());
        }
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        comboBox = new JComboBox<>(model);
        comboBox.addActionListener(this);
        comboPanel.add(comboBox);
        contentPane.add(comboPanel);

        lblTelefone = new JLabel("Telefone:");
        txtTelefone = new JTextField(20);
        addToPanel(contentPane, lblTelefone, txtTelefone);


        btnSubmeterArtigo = new JButton("Confirmar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSubmeterArtigo);
        contentPane.add(buttonPanel);
        btnSubmeterArtigo.addActionListener(this);
        btnSubmeterArtigo.setEnabled(comboBox.getSelectedIndex() != 0);
        styleButton(btnSubmeterArtigo);


        setContentPane(contentPane);
        setResizable(false);
        setVisible(true);
    }

    private void addToPanel(JPanel panel, JComponent label, JComponent field) {
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanel.add(label);
        flowPanel.add(field);
        panel.add(flowPanel);
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
        if (source.equals(btnSubmeterArtigo)) {
            ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();

            // cadastro na tabela autor_has_artigo
            pessoas = controllerSubsmissaoDeArtigos.cadastrarPessoas(pessoas);

            ArrayList<Autor> autores = new ArrayList<>();

            for (Pessoa pessoa: pessoas) {
                pessoa.getAutor().setIdautor(pessoa.getIdPessoa());
                autores.add(pessoa.getAutor());
            }

            ArrayList<Autor> autoresComId = controllerSubsmissaoDeArtigos.cadastroAutores(autores);

            Artigo artigoComId = controllerSubsmissaoDeArtigos.submeterArtigo(artigo, areaSelecionada, subareaSelecionada);
            for (Autor autorComId : autoresComId) {

                for (Pessoa pessoa : pessoas) {

                    Autor autorResponsavel = pessoa.getAutor();

                    if (autorComId.getCpf().equals(autorResponsavel.getCpf())) {
                        autorComId.seteResponsavelPeloArtigo(autorResponsavel.iseResponsavelPeloArtigo());
                        if (autorResponsavel.iseResponsavelPeloArtigo()) {
                            autorComId.setTelefone(txtTelefone.getText());
                        }
                        break;
                    }
                }
            }

            for (Pessoa pessoa : pessoas) {

                controllerSubsmissaoDeArtigos.cadastroAutorHasArtigo(artigoComId, pessoa);
            }

            new TelaSubmissaoArtigos(pessoaLogada);
            setVisible(false);
            dispose();

        } else if (source.equals(comboBox)) {
            btnSubmeterArtigo.setEnabled(comboBox.getSelectedIndex() != 0);

            String autorSelecionado = Objects.requireNonNull(comboBox.getSelectedItem()).toString();

            for (Pessoa pessoa : pessoas) {
                if (Objects.equals(pessoa.getNome(), autorSelecionado)) {
                    pessoa.getAutor().seteResponsavelPeloArtigo(true);

                } else {
                    pessoa.getAutor().seteResponsavelPeloArtigo(false);
                }
            }
        } else if (source == botaoVoltar) {
             new TelaFormularioArtigos(areaSelecionada, pessoaLogada, subareaSelecionada);
             setVisible(false);
             dispose();
        }
    }

    public JTextField getTxtTelefone() {
        return txtTelefone;
    }

    public void setTxtTelefone(JTextField txtTelefone) {
        this.txtTelefone = txtTelefone;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }


    public Area getAreaSelecionada() {
        return areaSelecionada;
    }

    public void setAreaSelecionada(Area areaSelecionada) {
        this.areaSelecionada = areaSelecionada;
    }
}
