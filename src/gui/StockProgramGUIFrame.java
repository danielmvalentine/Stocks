package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;

public class StockProgramGUIFrame extends JFrame
        implements ActionListener, ItemListener, ListSelectionListener {

  private final Model model;

  public StockProgramGUIFrame(Model model) {
    super();
    this.model = model;

    setTitle("Stock Program");
    setSize(420, 400);

    JPanel mainPanel = new JPanel();
    // For elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    // Scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
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
    JPanel listOfPortfoliosToEdit = new JPanel();
    listOfPortfoliosToEdit.setLayout(new FlowLayout());
    buyOrSellPanel.add(listOfPortfoliosToEdit);
    JLabel portfolioDisplay = new JLabel("Portfolio:");
    listOfPortfoliosToEdit.add(portfolioDisplay);

    String[] portfolios = {"Portfolios here"};
    // TODO add method / add all portfolios

    JComboBox<String> portfolioOptions = new JComboBox<String>();
    //the event listener when an option is selected
    portfolioOptions.setActionCommand("Size options");
    portfolioOptions.addActionListener(this);
    for (int i = 0; i < portfolios.length; i++) {
      portfolioOptions.addItem(portfolios[i]);
    }
    listOfPortfoliosToEdit.add(portfolioOptions);

    // Inputting stock ability
    JPanel inputTickerPanel = new JPanel();
    inputTickerPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputTickerPanel);

    JButton addStockButton = new JButton("Click to input stock ticker");
    addStockButton.setActionCommand("Input");
    addStockButton.addActionListener(this);
    inputTickerPanel.add(addStockButton);

    JLabel tickerDisplay = new JLabel("Stock ticker here");
    inputTickerPanel.add(tickerDisplay);

    // Adding the input areas for the number of shares
    JPanel inputSharesPanel = new JPanel();
    inputSharesPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputSharesPanel);

    // The function to determine the number of shares
    JButton addSharesButton = new JButton("Click to input number of shares");
    addSharesButton.setActionCommand("Input");
    addSharesButton.addActionListener(this);
    inputSharesPanel.add(addSharesButton);

    JLabel sharesDisplay = new JLabel("# shares");
    inputSharesPanel.add(sharesDisplay);

    // Adding the input areas for the date
    JPanel inputDatePanel = new JPanel();
    inputDatePanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputDatePanel);

    // The function to determine the date the stock should be bought / sold on
    JButton addDateButton = new JButton("Click to input the buy/sell date");
    addDateButton.setActionCommand("Input");
    addDateButton.addActionListener(this);
    inputDatePanel.add(addDateButton);

    JLabel dateDisplay = new JLabel("Date (YYYY-MM-DD)");
    inputDatePanel.add(dateDisplay);

    // Adding the input areas for the number of shares
    JPanel buyOrSellButtonsPanel = new JPanel();
    buyOrSellButtonsPanel.setBorder(BorderFactory.createTitledBorder(""));
    buyOrSellButtonsPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(buyOrSellButtonsPanel);

    // The buy and sell buttons
    JButton buyButton = new JButton("Buy");
    buyButton.setActionCommand("Buy");
    buyButton.addActionListener(this);
    buyOrSellButtonsPanel.add(buyButton);

    JButton sellButton = new JButton("Sell");
    sellButton.setActionCommand("Input");
    sellButton.addActionListener(this);
    buyOrSellButtonsPanel.add(sellButton);



    // The querying value functions
    JPanel valuePanel = new JPanel();
    valuePanel.setBorder(BorderFactory.createTitledBorder("Get Value"));
    valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(valuePanel);

    // Adding the list of portfolios available to select
    JPanel listOfPortfoliosToQueryValue = new JPanel();
    listOfPortfoliosToQueryValue.setLayout(new FlowLayout());
    valuePanel.add(listOfPortfoliosToQueryValue);
    JLabel portfolioDisplayQueryValue = new JLabel("Portfolio:");
    listOfPortfoliosToQueryValue.add(portfolioDisplayQueryValue);

    String[] portfoliosValue = {"Portfolios here"};
    // TODO add method / add all portfolios

    JComboBox<String> portfolioOptionsValue = new JComboBox<String>();
    //the event listener when an option is selected
    portfolioOptionsValue.setActionCommand("Size options");
    portfolioOptionsValue.addActionListener(this);
    for (int i = 0; i < portfoliosValue.length; i++) {
      portfolioOptionsValue.addItem(portfoliosValue[i]);
    }
    listOfPortfoliosToQueryValue.add(portfolioOptionsValue);

    // Adding the input areas for the date
    JPanel inputDateValuePanel = new JPanel();
    inputDateValuePanel.setLayout(new FlowLayout());
    valuePanel.add(inputDateValuePanel);

    // The function to determine the date the value should be gotten from
    JButton addDateButtonValue = new JButton("Click to input the desired date");
    addDateButtonValue.setActionCommand("Input");
    addDateButtonValue.addActionListener(this);
    inputDateValuePanel.add(addDateButtonValue);

    JLabel dateValueDisplay = new JLabel("Date (YYYY-MM-DD)");
    inputDateValuePanel.add(dateValueDisplay);

    // Adding the input areas for the value output
    JPanel outputValuePanel = new JPanel();
    outputValuePanel.setBorder(BorderFactory.createTitledBorder(""));
    outputValuePanel.setLayout(new FlowLayout());
    valuePanel.add(outputValuePanel);
    
    // The function to determine the value of the portfolio selected
    JButton getValueButton = new JButton("Click to get the value");
    getValueButton.setActionCommand("Input");
    getValueButton.addActionListener(this);
    outputValuePanel.add(getValueButton);

    JLabel getValueDisplay = new JLabel("Value");
    outputValuePanel.add(getValueDisplay);



    // The querying composition functions
    JPanel compositionPanel = new JPanel();
    compositionPanel.setBorder(BorderFactory.createTitledBorder("Get Composition"));
    compositionPanel.setLayout(new BoxLayout(compositionPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(compositionPanel);

    // Adding the list of portfolios available to select
    JPanel listOfPortfoliosToQueryComp = new JPanel();
    listOfPortfoliosToQueryComp.setLayout(new FlowLayout());
    compositionPanel.add(listOfPortfoliosToQueryComp);
    JLabel portfolioDisplayQueryComp = new JLabel("Portfolio:");
    listOfPortfoliosToQueryComp.add(portfolioDisplayQueryComp);

    String[] portfoliosComp = {"Portfolios here"};
    // TODO add method / add all portfolios

    JComboBox<String> portfolioOptionsComp = new JComboBox<String>();
    //the event listener when an option is selected
    portfolioOptionsComp.setActionCommand("Size options");
    portfolioOptionsComp.addActionListener(this);
    for (int i = 0; i < portfoliosComp.length; i++) {
      portfolioOptionsComp.addItem(portfoliosComp[i]);
    }
    listOfPortfoliosToQueryComp.add(portfolioOptionsComp);

    // Adding the input areas for the date
    JPanel inputDateCompPanel = new JPanel();
    inputDateCompPanel.setLayout(new FlowLayout());
    compositionPanel.add(inputDateCompPanel);

    // The function to determine the date the value should be gotten from
    JButton addDateButtonComp = new JButton("Click to input the desired date");
    addDateButtonComp.setActionCommand("Input");
    addDateButtonComp.addActionListener(this);
    inputDateCompPanel.add(addDateButtonComp);

    JLabel dateCompDisplay = new JLabel("Date (YYYY-MM-DD)");
    inputDateCompPanel.add(dateCompDisplay);

    // Adding the input areas for the value output
    JPanel outputCompPanel = new JPanel();
    buyOrSellButtonsPanel.setBorder(BorderFactory.createTitledBorder(""));
    outputCompPanel.setLayout(new BoxLayout(outputCompPanel, BoxLayout.LINE_AXIS));
    compositionPanel.add(outputCompPanel);

    // The function to determine the value of the portfolio selected
    JButton getCompButton = new JButton("Click to get the composition");
    getCompButton.setActionCommand("Input");
    getCompButton.addActionListener(this);
    outputCompPanel.add(getCompButton);

    JLabel getCompDisplay = new JLabel("Composition:");
    outputCompPanel.add(getCompDisplay);



    // Save portfolio function
    JPanel savePortfolioPanel = new JPanel();
    savePortfolioPanel.setBorder(BorderFactory.createTitledBorder("Save Portfolio"));
    savePortfolioPanel.setLayout(new BoxLayout(savePortfolioPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(savePortfolioPanel);

    // Adding the list of portfolios available to select
    JPanel listOfPortfoliosToSave = new JPanel();
    listOfPortfoliosToSave.setLayout(new FlowLayout());
    savePortfolioPanel.add(listOfPortfoliosToSave);
    JLabel portfolioSaveDisplay = new JLabel("Portfolio:");
    listOfPortfoliosToSave.add(portfolioSaveDisplay);

    String[] portfoliosSave = {"Portfolios here"};
    // TODO add method / add all portfolios

    JComboBox<String> portfolioOptionsSave = new JComboBox<String>();
    //the event listener when an option is selected
    portfolioOptionsSave.setActionCommand("Size options");
    portfolioOptionsSave.addActionListener(this);
    for (int i = 0; i < portfoliosSave.length; i++) {
      portfolioOptionsSave.addItem(portfoliosSave[i]);
    }
    listOfPortfoliosToSave.add(portfolioOptionsSave);

    // Save button
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    savePortfolioPanel.add(fileSavePanel);

    JButton fileSaveButton = new JButton("Save");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    fileSavePanel.add(fileSaveButton);
    JLabel saveDisplay = new JLabel("File path will appear here");
    fileSavePanel.add(saveDisplay);



    // Load portfolio function
    JPanel loadPortfolioPanel = new JPanel();
    loadPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Load Portfolio"));
    loadPortfolioPanel.setLayout(new BoxLayout(loadPortfolioPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(loadPortfolioPanel);

    // Adding the list of portfolios available to select
    JPanel listOfPortfoliosToLoad = new JPanel();
    listOfPortfoliosToLoad.setLayout(new FlowLayout());
    loadPortfolioPanel.add(listOfPortfoliosToLoad);
    JLabel portfolioLoadDisplay = new JLabel("Portfolio:");
    listOfPortfoliosToLoad.add(portfolioLoadDisplay);

    String[] portfoliosLoad = {"Portfolios here"};
    // TODO add method / add all portfolios

    JComboBox<String> portfolioOptionsLoad = new JComboBox<String>();
    //the event listener when an option is selected
    portfolioOptionsLoad.setActionCommand("Size options");
    portfolioOptionsLoad.addActionListener(this);
    for (int i = 0; i < portfoliosLoad.length; i++) {
      portfolioOptionsLoad.addItem(portfoliosLoad[i]);
    }
    listOfPortfoliosToLoad.add(portfolioOptionsLoad);

    // Load button
    JPanel loadPanel = new JPanel();
    loadPanel.setLayout(new FlowLayout());
    loadPortfolioPanel.add(loadPanel);

    JButton loadButton = new JButton("Load");
    loadButton.setActionCommand("Open file");
    loadButton.addActionListener(this);
    loadPanel.add(loadButton);
    JLabel fileOpenDisplay = new JLabel("File path will appear here");
    loadPanel.add(fileOpenDisplay);
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
