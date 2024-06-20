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
import model.portfolios.PortfolioImpl;

public class StockProgramGUIFrame extends JFrame
        implements ActionListener, ItemListener, ListSelectionListener {

  private final Model model;
  private final JPanel listPortfoliosPanel;
  JLabel addedPortfolio;
  private JTextField pfield;
  private JTextField inputPortfolioForStockAmount;
  private JTextField inputPortfolioForValue;
  private JTextField getPortfolioForComposition;
  private JTextField getPortfolioToSave;
  private JTextField getPortfolioToLoad;
  private JTextField tickerField;
  private JTextField inputStockAmount;
  private JTextField dayInput;
  private JTextField monthInput;
  private JTextField yearInput;
  private JTextField dayValueInput;
  private JTextField monthValueInput;
  private JTextField yearValueInput;
  private JTextField dayCompInput;
  private JTextField monthCompInput;
  private JTextField yearCompInput;

  public StockProgramGUIFrame(Model model) {
    super();
    this.model = model;

    setTitle("Stock Program");
    setSize(550, 400);

    JPanel mainPanel = new JPanel();
    // For elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    // Scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel createPortfolioPanel = new JPanel();
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    JPanel portfolioPanel = new JPanel();
    portfolioPanel.setLayout(new BoxLayout(portfolioPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(createPortfolioPanel);


    // The create portfolio section.
    JPanel inputTitlePanel = new JPanel();

    JLabel inputDisplay = new JLabel("Portfolio title here:");
    createPortfolioPanel.add(inputDisplay);

    pfield = new JTextField(10);
    createPortfolioPanel.add(pfield);

    inputTitlePanel.setLayout(new FlowLayout());
    createPortfolioPanel.add(inputTitlePanel);

    JButton createNewPortfolioButton = new JButton("Click to create new portfolio");
    createNewPortfolioButton.setActionCommand("Create-Portfolio");
    createNewPortfolioButton.addActionListener(this);
    inputTitlePanel.add(createNewPortfolioButton);



    // The current list of portfolios
    listPortfoliosPanel = new JPanel();
    listPortfoliosPanel.setBorder(BorderFactory.createTitledBorder("List of Portfolios"));
    addedPortfolio = new JLabel(model.formatPortfolios());
    listPortfoliosPanel.add(addedPortfolio);
    mainPanel.add(listPortfoliosPanel);



    // The buying and selling functionality
    JPanel buyOrSellPanel = new JPanel();
    buyOrSellPanel.setBorder(BorderFactory.createTitledBorder("Buy or Sell Stock"));
    buyOrSellPanel.setLayout(new BoxLayout(buyOrSellPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(buyOrSellPanel);

    // Inputting portfolio title
    JPanel inputPortfolioNameStock = new JPanel();
    inputPortfolioNameStock.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputPortfolioNameStock);

    JLabel inputPortfolioLabel = new JLabel("Portfolio title here:");
    inputPortfolioNameStock.add(inputPortfolioLabel);
    inputPortfolioForStockAmount = new JTextField(10);
    inputPortfolioNameStock.add(inputPortfolioForStockAmount);

    // Inputting stock ability
    JPanel inputTickerPanel = new JPanel();
    inputTickerPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputTickerPanel);


    JLabel tickerDisplay = new JLabel("Stock ticker here:");
    inputTickerPanel.add(tickerDisplay);
    tickerField = new JTextField(4);
    inputTickerPanel.add(tickerField);

    // Adding the input areas for the number of shares
    JPanel inputSharesPanel = new JPanel();
    inputSharesPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputSharesPanel);

    JLabel sharesDisplay = new JLabel("Number of shares:");
    inputSharesPanel.add(sharesDisplay);

    inputStockAmount = new JTextField(10);
    inputSharesPanel.add(inputStockAmount);

    // Adding the input areas for the date
    JPanel inputDatePanel = new JPanel();
    inputDatePanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputDatePanel);

    // The function to determine the date the stock should be bought / sold on

    // First off day
    JLabel dayDisplay = new JLabel("Day:");
    inputDatePanel.add(dayDisplay);
    dayInput = new JTextField(2);
    inputDatePanel.add(dayInput);

    // Then month
    JLabel monthDisplay = new JLabel("Month:");
    inputDatePanel.add(monthDisplay);
    monthInput = new JTextField(2);
    inputDatePanel.add(monthInput);

    // Then year
    JLabel yearDisplay = new JLabel("Year:");
    inputDatePanel.add(yearDisplay);
    yearInput = new JTextField(4);
    inputDatePanel.add(yearInput);

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

    // Adding the input area for the portfolio
    JLabel titleOfPortfolioForValue = new JLabel("Portfolio title here:");
    listOfPortfoliosToQueryValue.add(titleOfPortfolioForValue);
    inputPortfolioForValue = new JTextField(10);
    listOfPortfoliosToQueryValue.add(inputPortfolioForValue);

    // Adding the input areas for the date
    JPanel inputDateValuePanel = new JPanel();
    inputDateValuePanel.setLayout(new FlowLayout());
    valuePanel.add(inputDateValuePanel);

    // Adding day value
    JLabel dayValueDisplay = new JLabel("Day:");
    inputDateValuePanel.add(dayValueDisplay);
    dayValueInput = new JTextField(2);
    inputDateValuePanel.add(dayValueInput);

    // Adding month value
    JLabel monthValueDisplay = new JLabel("Month:");
    inputDateValuePanel.add(monthValueDisplay);
    monthValueInput = new JTextField(2);
    inputDateValuePanel.add(monthValueInput);

    // Adding year value
    JLabel yearValueDisplay = new JLabel("Year:");
    inputDateValuePanel.add(yearValueDisplay);
    yearValueInput = new JTextField(4);
    inputDateValuePanel.add(yearValueInput);

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


    // The querying composition functions
    JPanel compositionPanel = new JPanel();
    compositionPanel.setBorder(BorderFactory.createTitledBorder("Get Composition"));
    compositionPanel.setLayout(new BoxLayout(compositionPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(compositionPanel);

    JPanel compositionPortfolioPanel = new JPanel();
    compositionPortfolioPanel.setLayout(new FlowLayout());
    compositionPanel.add(compositionPortfolioPanel);

    JLabel compositionLabel = new JLabel("Portfolio title here:");
    compositionPortfolioPanel.add(compositionLabel);
    getPortfolioForComposition = new JTextField(10);
    compositionPortfolioPanel.add(getPortfolioForComposition);

    // Adding the input areas for the date
    JPanel inputDateCompPanel = new JPanel();
    inputDateCompPanel.setLayout(new FlowLayout());
    compositionPanel.add(inputDateCompPanel);

    // Adding day value
    JLabel dayCompDisplay = new JLabel("Day:");
    inputDateCompPanel.add(dayCompDisplay);
    dayCompInput = new JTextField(2);
    inputDateCompPanel.add(dayCompInput);

    // Adding month value
    JLabel monthCompDisplay = new JLabel("Month:");
    inputDateCompPanel.add(monthCompDisplay);
    monthCompInput = new JTextField(2);
    inputDateCompPanel.add(monthCompInput);

    // Adding year value
    JLabel yearCompDisplay = new JLabel("Year:");
    inputDateCompPanel.add(yearCompDisplay);
    yearCompInput = new JTextField(4);
    inputDateCompPanel.add(yearCompInput);

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

    // Save portfolio function
    JPanel savePortfolioPanel = new JPanel();
    savePortfolioPanel.setBorder(BorderFactory.createTitledBorder("Save Portfolio"));
    savePortfolioPanel.setLayout(new BoxLayout(savePortfolioPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(savePortfolioPanel);

    // Info for save portfolio
    JPanel savePortfolioInfoPanel = new JPanel();
    savePortfolioInfoPanel.setLayout(new FlowLayout());
    savePortfolioPanel.add(savePortfolioInfoPanel);
    JLabel savePortfolioInfo = new JLabel("Given .txt file will be located in"
            + " the given saved_portfolios folder.");
    savePortfolioInfoPanel.add(savePortfolioInfo);

    // The panel to put the name of the portfolio in
    JPanel savePortfolioLabelPanel = new JPanel();
    savePortfolioLabelPanel.setLayout(new FlowLayout());
    savePortfolioPanel.add(savePortfolioLabelPanel);

    // The label of the portfolio to save
    JLabel savePortfolioLabel = new JLabel("Portfolio title here:");
    savePortfolioLabelPanel.add(savePortfolioLabel);
    getPortfolioToSave = new JTextField(10);
    savePortfolioLabelPanel.add(getPortfolioToSave);

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

    // Putting the info for the save info
    JPanel loadPortfolioInfoPanel = new JPanel();
    loadPortfolioInfoPanel.setLayout(new FlowLayout());
    loadPortfolioPanel.add(loadPortfolioInfoPanel);
    JLabel loadPortfolioInfo = new JLabel("Given portfolio must be in .txt format,"
            + " and be located in the given folder named saved_portfolios");
    loadPortfolioInfoPanel.add(loadPortfolioInfo);

    // The panel to add our label to
    JPanel loadPortfolioLabelPanel = new JPanel();
    loadPortfolioLabelPanel.setLayout(new FlowLayout());
    loadPortfolioPanel.add(loadPortfolioLabelPanel);

    // Adding the loadPortfolioLabelPanel to the panel
    JLabel loadPortfolioLabel = new JLabel("Portfolio title here:");
    loadPortfolioLabelPanel.add(loadPortfolioLabel);
    getPortfolioToLoad = new JTextField(10);
    loadPortfolioLabelPanel.add(getPortfolioToLoad);

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
  public void actionPerformed(ActionEvent arg0) {
    switch (arg0.getActionCommand()) {
      case "Create-Portfolio":
        model.addPortfolio(new PortfolioImpl(pfield.getText()));
        String formattedPortfolios = model.formatPortfolios()
                .replaceAll(System.lineSeparator(), "<br>");
        addedPortfolio.setText("<html><body>" + formattedPortfolios + "</body></html>");
        break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent arg0) {
      // Because there are no check boxes, nothing needs to be here
  }

  @Override
  public void valueChanged(ListSelectionEvent arg0) {

  }
}
