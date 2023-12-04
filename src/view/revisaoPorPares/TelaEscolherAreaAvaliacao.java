package view.revisaoPorPares;

import controller.ControllerRevisaoDeArtigos;
import controller.ControllerSubsmissaoDeArtigos;
import model.entities.Area;
import model.entities.Artigo;
import model.entities.Pessoa;
import model.entities.Subarea;
import view.MenuLogado;
import view.subsmissaoDeArtigos.TelaArtigosDoAutor;
import view.subsmissaoDeArtigos.TelaFormularioArtigos;
import view.subsmissaoDeArtigos.TelaSubmissaoArtigos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class TelaEscolherAreaAvaliacao extends JFrame implements ActionListener {
    private JComboBox<String> comboBoxAreas;
    private JComboBox<String> comboBoxSubareas;
    private JButton botaoConfirmar;
    private Pessoa pessoaLogada;

    private ArrayList<Area> areas;
    private ArrayList<Subarea> subareas;
    private JButton botaoVoltar;

    public TelaEscolherAreaAvaliacao(Pessoa pessoaLogada) {
        this.pessoaLogada = pessoaLogada;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        botaoVoltar = new JButton("\u2190");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(botaoVoltar);

        // Adiciona o botão de voltar ao painel
        add(Box.createVerticalGlue());
        add(botaoVoltar);
        add(Box.createVerticalGlue());

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

        // Botão Confirmar
        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(this);
        botaoConfirmar.setEnabled(false);
        styleButton(botaoConfirmar);
        add(createButtonPanel(botaoConfirmar));

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

    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(button);
        return buttonPanel;
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
            ControllerRevisaoDeArtigos controllerRevisaoDeArtigos = new ControllerRevisaoDeArtigos();
            subareas = controllerRevisaoDeArtigos.getSubAreas(selectedArea);

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


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(comboBoxAreas)) {
            botaoConfirmar.setEnabled(comboBoxAreas.getSelectedIndex() != 0);

            if (comboBoxAreas.getSelectedIndex() > 0) {
                Area selectedArea = areas.get(comboBoxAreas.getSelectedIndex() - 1);
                loadSubareas(selectedArea);
            } else {
                comboBoxSubareas.removeAllItems();
                comboBoxSubareas.addItem("Selecione uma subárea");
            }
        } else if (source.equals(comboBoxSubareas)) {
            botaoConfirmar.setEnabled(comboBoxSubareas.getSelectedIndex() != 0);
        } else if (source.equals(botaoConfirmar)) {
            int selectedAreaIndex = comboBoxAreas.getSelectedIndex();
            int selectedSubareaIndex = comboBoxSubareas.getSelectedIndex();

            if (selectedAreaIndex > 0 && selectedSubareaIndex > 0) {
                Area selectedArea = areas.get(selectedAreaIndex - 1);
                Subarea selectedSubarea = subareas.get(selectedSubareaIndex - 1);

                new TelaRevisaoDeArtigos(selectedArea, selectedSubarea, pessoaLogada);
                setVisible(false);
                dispose();
            }
        } else if (source == botaoVoltar) {
            new MenuLogado(pessoaLogada);
            setVisible(false);
            dispose();
        }
    }

}