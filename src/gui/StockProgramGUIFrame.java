package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StockProgramGUIFrame extends JFrame
        implements ActionListener, ItemListener, ListSelectionListener {

  private final JPanel mainPanel;
  private final JScrollPane mainScrollPane;
  //private final JLabel inputDisplay;


  public StockProgramGUIFrame() {
    super();
    setTitle("Stock Program");
    setSize(400, 400);

    mainPanel = new JPanel();
    // For elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    // Scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel createPortfolioPanel = new JPanel();
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    mainPanel.add(createPortfolioPanel);

    // The create portfolio button
    JPanel inputTitlePanel = new JPanel();
    inputTitlePanel.setLayout(new FlowLayout());
    createPortfolioPanel.add(inputTitlePanel);

    JButton createNewPortfolioButton = new JButton("Click to create new portfolio");
    createNewPortfolioButton.setActionCommand("Input");
    createNewPortfolioButton.addActionListener(this);
    inputTitlePanel.add(createNewPortfolioButton);

    JLabel inputDisplay = new JLabel("Portfolio title here");
    inputTitlePanel.add(inputDisplay);

    // The current list of portfolios
    //TODO I don't really know if this can/should happen but like just wanted to throw it out there
    JPanel listPortfoliosPanel = new JPanel();
    listPortfoliosPanel.setBorder(BorderFactory.createTitledBorder("List of Portfolios"));
    mainPanel.add(listPortfoliosPanel);



    // The buying and selling functionality
    JPanel buyOrSellPanel = new JPanel();
    buyOrSellPanel.setBorder(BorderFactory.createTitledBorder("Buy or Sell Stock"));
    buyOrSellPanel.setLayout(new BoxLayout(buyOrSellPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(buyOrSellPanel);

    // Adding the list of portfolios available to select
    JPanel listOfPortfolios = new JPanel();
    listOfPortfolios.setLayout(new FlowLayout());
    buyOrSellPanel.add(listOfPortfolios);
    JLabel portfolioDisplay = new JLabel("Portfolio:");
    listOfPortfolios.add(portfolioDisplay);

    String[] portfolios = {"Portfolios here"};
    // TODO add method / add all portfolios

    JComboBox<String> portfolioOptions = new JComboBox<String>();
    //the event listener when an option is selected
    portfolioOptions.setActionCommand("Size options");
    portfolioOptions.addActionListener(this);
    for (int i = 0; i < portfolios.length; i++) {
      portfolioOptions.addItem(portfolios[i]);
    }
    listOfPortfolios.add(portfolioOptions);


    // Inputting stock ability
    JPanel inputTickerPanel = new JPanel();
    inputTickerPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputTickerPanel);

    JButton addStockButton = new JButton("Click to create new portfolio");
    addStockButton.setActionCommand("Input");
    addStockButton.addActionListener(this);
    inputTickerPanel.add(addStockButton);

    JLabel tickerDisplay = new JLabel("Stock ticker here");
    inputTickerPanel.add(tickerDisplay);


    buyOrSellPanel.add(portfolioOptions);


  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {

  }
}
