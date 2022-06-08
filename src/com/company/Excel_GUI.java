package com.company;

import javax.swing.*;
import java.awt.*;

import javax.swing.JOptionPane;

public class Excel_GUI extends JFrame {


    private final Parser parser = new Parser();
    private Calculations calc = new Calculations();

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JTextField jTextField1;
    private JTextField jTextField2;


    public Excel_GUI() {

        jButton1 = new JButton("Ссылка на файл Excel");
        jButton2 = new JButton("Путь к файлу с вычислениями");
        jButton3 = new JButton("Выход");
        jTextField1 = new JTextField("");
        jTextField2 = new JTextField("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.addActionListener(e -> {
            try {
                parser.reader(jTextField1.getText());
                calc.calc(parser);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Неверный путь к файлу", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        });
        jButton2.addActionListener(e -> {
            try {
                calc.writer(jTextField2.getText(), calc);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Неверный экспорт", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }


        });

        jButton3.addActionListener(e -> System.exit(0));

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(jButton1);
        container.add(jTextField1);
        container.add(jButton2);
        container.add(jTextField2);
        container.add(jButton3);
        this.setContentPane(container);
        pack();
    }


}