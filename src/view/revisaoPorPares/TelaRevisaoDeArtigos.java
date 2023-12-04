package view.revisaoPorPares;

import controller.ControllerRevisaoDeArtigos;
import controller.ControllerSubsmissaoDeArtigos;
import model.entities.Area;
import model.entities.Artigo;
import model.entities.Pessoa;
import model.entities.Subarea;
import view.subsmissaoDeArtigos.TelaSobrescreverArtigo;
import view.subsmissaoDeArtigos.TelaSubmissaoArtigos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaRevisaoDeArtigos extends JFrame implements ActionListener{
    private JLabel lblTituloArtigo;
    private JPanel contentPane;
    private JButton botaoAvaliar;
    private Pessoa pessoaLogada;
    private JLabel lblNenhumArtigoEncontrado;
    private JButton botaoVoltar;
    private Area area;

    private Subarea subarea;

    public TelaRevisaoDeArtigos(Area area, Subarea subarea, Pessoa pessoaLogada) {
        this.area = area;
        this.subarea = subarea;
        this.pessoaLogada = pessoaLogada;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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

        ControllerRevisaoDeArtigos controllerRevisaoDeArtigos = new ControllerRevisaoDeArtigos();
        ArrayList<Artigo> artigos = controllerRevisaoDeArtigos.getArtigosParaRevisar(pessoaLogada, area, subarea);

        if(artigos.isEmpty()){
            lblNenhumArtigoEncontrado = new JLabel("Nenhum artigo encontrado");
            lblNenhumArtigoEncontrado.setFont(new Font("Inter", Font.BOLD, 20));
            contentPane.add(lblNenhumArtigoEncontrado);

        } else {

            for(Artigo artigo: artigos){
                lblTituloArtigo = new JLabel("Título do Artigo: " + artigo.getTitulo());
                botaoAvaliar = new JButton("Avaliar");
                styleButton(botaoAvaliar);
                botaoAvaliar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Artigo artigoDoBotao = artigo;

                        new TelaAvaliarArtigo(artigoDoBotao, pessoaLogada, area, subarea);
                        setVisible(false);
                        dispose();
                    }
                });
                addToPanelWithLabel(contentPane, lblTituloArtigo, botaoAvaliar);
            }
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

    private void addToPanelWithLabel(JPanel contentPane, JComponent label, JComponent button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        if (button != null) {
            panel.add(button);
        }
        contentPane.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == botaoVoltar) {
             new TelaEscolherAreaAvaliacao(pessoaLogada);
             setVisible(false);
             dispose();
        }
    }
}
