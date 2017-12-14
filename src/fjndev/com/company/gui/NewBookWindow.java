package fjndev.com.company.gui;

import fjndev.com.company.book.Book;
import fjndev.com.company.dao.book.BookDao;
import fjndev.com.company.dao.book.BookDaoJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewBookWindow extends Window implements ActionListener
{
    private JPanel jPanelMain;
    private JTextField jTxtTitle;
    private JTextField jTxtAuthor;
    private JTextField jTxtGenre;
    private JTextField jTxtYear;
    private JTextField jTxtDescription;
    private JButton jButtonSave;
    private JButton jButtonCancel;
    private BookDao book;

    public NewBookWindow(MainWindow window)
    {
        title = "Cadastro de Livro";
        close = DISPOSE_ON_CLOSE;
        width = 400;
        height = 200;
        position_x = 200;
        position_y  = 190;
        setLocationRelativeTo(window);
        setWindow();
        drawFormulario();
        drawWindow();
    }

    private void drawFormulario()
    {
        GridLayout layout = new GridLayout(6,2);
        jPanelMain = new JPanel();
        jPanelMain.setLayout(layout);

        jTxtTitle = new JTextField("", 25);
        jTxtAuthor = new JTextField("", 25);
        jTxtDescription = new JTextField("",25);
        jTxtYear = new JTextField("",25);
        jTxtGenre = new JTextField("",25);

        jButtonSave = new JButton("Salvar");
        jButtonSave.addActionListener(this);
        jButtonCancel = new JButton("Cancelar");
        jButtonCancel.addActionListener(this);

        jPanelMain.add(new Label("Title"));
        jPanelMain.add(jTxtTitle);
        jPanelMain.add(new Label("Author"));
        jPanelMain.add(jTxtAuthor);
        jPanelMain.add(new Label("Descrição"));
        jPanelMain.add(jTxtDescription);
        jPanelMain.add(new Label("Ano"));
        jPanelMain.add(jTxtYear);
        jPanelMain.add(new Label("Genero"));
        jPanelMain.add(jTxtGenre);

        jPanelMain.add(jButtonSave);
        jPanelMain.add(jButtonCancel);

        add(jPanelMain);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (e.getSource() == jButtonSave)
            {
                book = new BookDaoJDBC();
                boolean ok = book.insert(new Book(jTxtTitle.getText(),Integer.parseInt(jTxtYear.getText()),
                        jTxtDescription.getText(), jTxtAuthor.getText(), jTxtGenre.getText()));
                if (ok)
                    dispose();
            }

            if (e.getSource() == jButtonCancel)
                dispose();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
