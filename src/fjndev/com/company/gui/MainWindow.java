package fjndev.com.company.gui;

import fjndev.com.company.book.Book;
import fjndev.com.company.dao.book.BookDao;
import fjndev.com.company.dao.book.BookDaoJDBC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class MainWindow extends Window implements ActionListener
{

    private JMenu mainMenu;
    private JMenu aboutMenu;
    private JMenuItem newRegistry;
    private JMenuItem updateRegistry;
    private JMenuItem about;
    private JMenuBar mainTopMenuBar;
    private BookDao bookDao;
    private DefaultTableModel defaultTableModel;
    private JPanel jPanelTable;
    private JScrollPane scrollPane;
    private JTable jTableBook;
    private JFrame window;

    public MainWindow()
    {
        title = "Janela Principal";
        setWindow();
        drawMenu();
        drawListBooks();
        drawWindow();
    }

    private void drawMenu()
    {
        mainMenu = new JMenu("Menu");
        aboutMenu = new JMenu("About");

        newRegistry = new JMenuItem("Adicionar Livro");
        newRegistry.addActionListener(this);
        updateRegistry = new JMenuItem("Salvar");
        updateRegistry.addActionListener(this);
        about = new JMenuItem("Sobre");
        about.addActionListener(this);

        mainMenu.add(newRegistry);
        mainMenu.add(updateRegistry);
        aboutMenu.add(about);

        mainTopMenuBar = new JMenuBar();
        setJMenuBar(mainTopMenuBar);

        mainTopMenuBar.add(mainMenu);
        mainTopMenuBar.add(aboutMenu);

    }

    private void save()
    {
        Vector bookVector;

        bookVector = defaultTableModel.getDataVector();

        for(int i = 0;i < bookVector.size();i++)
        {
            try {
                Vector vector = (Vector)bookVector.get(i);
                Integer id = (Integer)vector.get(0);
                String title = (String)vector.get(1);
                String author = (String)vector.get(2);
                String genre = (String)vector.get(3);
                Integer year = Integer.parseInt((String)vector.get(4));
                String description = (String)vector.get(5);
                bookDao.update(new Book(id, title, year, description, author, genre));
            }catch (ClassCastException cce)
            {
                //System.out.println("Nao consegui solucionar erro: " + cce.getMessage());
            }
        }
    }

    private void drawListBooks()
    {
        jPanelTable = new JPanel();
        jPanelTable.setLayout(new GridLayout());
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("id");
        defaultTableModel.addColumn("Titulo");
        defaultTableModel.addColumn("Autor");
        defaultTableModel.addColumn("Gênero");
        defaultTableModel.addColumn("Ano");
        defaultTableModel.addColumn("Descrição");
        jTableBook = new JTable(defaultTableModel);

        scrollPane = new JScrollPane(jTableBook);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        bookDao = new BookDaoJDBC();
        List<Book> bookList = bookDao.all();

        for (int i = 0; i < bookList.size(); i++)
            defaultTableModel.addRow(new Object[]{bookList.get(i).getId(), bookList.get(i).getTitle(), bookList.get(i).getAuthor(),
                    bookList.get(i).getGenre(), bookList.get(i).getYear(), bookList.get(i).getDescription()});

        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (e.getSource() == newRegistry)
            {
                window = new NewBookWindow(this);
            }

            if(e.getSource() == updateRegistry)
            {
                save();
            }

            if(e.getSource() == about)
            {
                JOptionPane.showMessageDialog(this, "Development by \nHerisson Silva\nVersion 0.1");
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
