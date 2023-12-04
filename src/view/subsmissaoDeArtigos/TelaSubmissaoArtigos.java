package view.subsmissaoDeArtigos;

import controller.ControllerRevisaoDeArtigos;
import controller.ControllerSubsmissaoDeArtigos;
import model.entities.*;
import view.MenuLogado;
import view.revisaoPorPares.TelaRevisaoDeArtigos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaSubmissaoArtigos extends JFrame implements ActionListener {
    private JComboBox<String> comboBoxAreas;
    private JPanel contentPane;
    private JButton buttonSobrescreverArtigo;
    private JButton buttonSubmeterArtigo;

    ArrayList<Area> areas;
    private String nomeAreaSelecionada;

    private Area areaSelecionada;

    private Pessoa pessoaLogada;

    private JComboBox<String> comboBoxSubareas;

    private ArrayList<Subarea> subareas;

    private JButton botaoVoltar;

    public TelaSubmissaoArtigos(Pessoa pessoaLogada) {
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
        contentPane.add(Box.createVerticalGlue());;

        // Inicializar ComboBox de Áreas
        comboBoxAreas = new JComboBox<>();
        comboBoxAreas.addItem("Selecione uma área");
        comboBoxAreas.addActionListener(this);
        add(createComboBoxPanel(comboBoxAreas));


        // Inicializar ComboBox de Subáreas
        comboBoxSubareas = new JComboBox<>();
        comboBoxSubareas.addItem("Selecione uma subárea");
        comboBoxSubareas.addActionListener(this);
        add(createComboBoxPanel(comboBoxSubareas));

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonSubmeterArtigo = new JButton("Submeter Artigo");
        buttonSubmeterArtigo.addActionListener(this);
        styleButton(buttonSubmeterArtigo);
        buttonSubmeterArtigo.setEnabled(false);
        buttonPanel2.add(buttonSubmeterArtigo);
        contentPane.add(buttonPanel2);

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonSobrescreverArtigo = new JButton("Sobrescrever Artigo");
        buttonSobrescreverArtigo.addActionListener(this);
        styleButton(buttonSobrescreverArtigo);
        buttonPanel1.add(buttonSobrescreverArtigo);
        contentPane.add(buttonPanel1);



        setResizable(false);
        setVisible(true);

        // Carregar áreas iniciais
        loadAreas();
    }

    private JPanel createComboBoxPanel(JComboBox<String> comboBox) {
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        comboPanel.add(comboBox);
        return comboPanel;
    }


    private void loadAreas() {
        ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
        areas = controllerSubsmissaoDeArtigos.carregarAreas();

        for (Area area : areas) {
            comboBoxAreas.addItem(area.getNome());
        }
    }

    private void loadSubareas(Area selectedArea) {
        if (selectedArea != null) {
            ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
            subareas = controllerSubsmissaoDeArtigos.getSubAreas(selectedArea);

            comboBoxSubareas.removeAllItems();
            comboBoxSubareas.addItem("Selecione uma subárea");

            for (Subarea subarea : subareas) {
                comboBoxSubareas.addItem(subarea.getNome());
            }
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#1d1d1d")); // Cor de fundo do botão
        button.setForeground(Color.white); // Cor do texto do botão
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
    }

    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source.equals(comboBoxAreas)) {
            buttonSubmeterArtigo.setEnabled(comboBoxAreas.getSelectedIndex() != 0);

            if (comboBoxAreas.getSelectedIndex() > 0) {
                Area selectedArea = areas.get(comboBoxAreas.getSelectedIndex() - 1);
                loadSubareas(selectedArea);
            } else {
                comboBoxSubareas.removeAllItems();
                comboBoxSubareas.addItem("Selecione uma subárea");
            }
        } else if (source.equals(comboBoxSubareas)) {
            buttonSubmeterArtigo.setEnabled(comboBoxSubareas.getSelectedIndex() != 0);
        } else if (source.equals(buttonSubmeterArtigo)) {
            int selectedAreaIndex = comboBoxAreas.getSelectedIndex();
            int selectedSubareaIndex = comboBoxSubareas.getSelectedIndex();

            if (selectedAreaIndex > 0 && selectedSubareaIndex > 0) {
                Area areaSelecionada = areas.get(selectedAreaIndex - 1);
                Subarea subareaSelecionada = subareas.get(selectedSubareaIndex - 1);

                new TelaFormularioArtigos(areaSelecionada, pessoaLogada, subareaSelecionada);
                setVisible(false);
                dispose();
            }
        } else if (source.equals(buttonSobrescreverArtigo)) {
            ArrayList<Artigo> artigosDoAutor = new ArrayList<Artigo>();
            ControllerSubsmissaoDeArtigos controllerSubsmissaoDeArtigos = new ControllerSubsmissaoDeArtigos();
            artigosDoAutor = controllerSubsmissaoDeArtigos.getArtigosDoAutor(pessoaLogada);

            new TelaArtigosDoAutor(artigosDoAutor, pessoaLogada);
            setVisible(false);
            dispose();
        }
        else if (source == botaoVoltar) {

             new MenuLogado(pessoaLogada);
             setVisible(false);
             dispose();
        }
    }

}
