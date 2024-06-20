package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import model.portfolios.PortfolioImpl;
import model.portfolios.portfoliofunctions.AddStockTo;
import model.portfolios.portfoliofunctions.SellStockFrom;
import view.PortfolioView;

public class StockProgramGUIFrame extends JFrame
        implements ActionListener, ItemListener, ListSelectionListener {

  private final Model model;
  private final Appendable ap;
  private final PortfolioView view;

  private final JPanel listPortfoliosPanel;
  private JLabel addedPortfolio;
  private JTextField pfield;
  private JLabel inputPortfolioLabel;
  private JTextField inputPortfolioForStockAmount;
  private JTextField inputPortfolioForValue;
  private JLabel titleOfPortfolioForValue;
  private JTextField getPortfolioForComposition;
  private JLabel compositionLabel;
  private JTextField getPortfolioToSave;
  private JLabel savePortfolioLabel;
  private JTextField getPortfolioToLoad;
  private JLabel tickerDisplay;
  private JTextField tickerField;
  private JLabel sharesDisplay;
  private JTextField inputStockAmount;
  private JTextField dayInput;
  private JLabel dayDisplay;
  private JTextField monthInput;
  private JLabel monthDisplay;
  private JTextField yearInput;
  private JLabel yearDisplay;
  private JLabel dayValueDisplay;
  private JTextField dayValueInput;
  private JTextField monthValueInput;
  private JTextField yearValueInput;
  private JLabel returnedValue;
  private JLabel dayCompDisplay;
  private JTextField dayCompInput;
  private JTextField monthCompInput;
  private JTextField yearCompInput;
  private JLabel composition;

  public StockProgramGUIFrame(Model model) {
    super();
    this.model = model;
    this.ap = new StringBuilder();
    // Don't care about the views output, but used to utilize certain functions
    this.view = new PortfolioView(this.ap);

    setTitle("Stock Program");
    setSize(600, 400);

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

    inputPortfolioLabel = new JLabel("Portfolio title here:");
    inputPortfolioNameStock.add(inputPortfolioLabel);
    inputPortfolioForStockAmount = new JTextField(10);
    inputPortfolioNameStock.add(inputPortfolioForStockAmount);

    // Inputting stock ability
    JPanel inputTickerPanel = new JPanel();
    inputTickerPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputTickerPanel);


    tickerDisplay = new JLabel("Stock ticker here:");
    inputTickerPanel.add(tickerDisplay);
    tickerField = new JTextField(4);
    inputTickerPanel.add(tickerField);

    // Adding the input areas for the number of shares
    JPanel inputSharesPanel = new JPanel();
    inputSharesPanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputSharesPanel);

    sharesDisplay = new JLabel("Number of shares:");
    inputSharesPanel.add(sharesDisplay);

    inputStockAmount = new JTextField(10);
    inputSharesPanel.add(inputStockAmount);

    // Adding the input areas for the date
    JPanel inputDatePanel = new JPanel();
    inputDatePanel.setLayout(new FlowLayout());
    buyOrSellPanel.add(inputDatePanel);

    // The function to determine the date the stock should be bought / sold on

    // First off, day
    dayDisplay = new JLabel("Day:");
    inputDatePanel.add(dayDisplay);
    dayInput = new JTextField(2);
    inputDatePanel.add(dayInput);

    // Then month
    monthDisplay = new JLabel("Month:");
    inputDatePanel.add(monthDisplay);
    monthInput = new JTextField(2);
    inputDatePanel.add(monthInput);

    // Then year
    yearDisplay = new JLabel("Year:");
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
    sellButton.setActionCommand("Sell");
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
    titleOfPortfolioForValue = new JLabel("Portfolio title here:");
    listOfPortfoliosToQueryValue.add(titleOfPortfolioForValue);
    inputPortfolioForValue = new JTextField(10);
    listOfPortfoliosToQueryValue.add(inputPortfolioForValue);

    // Adding the input areas for the date
    JPanel inputDateValuePanel = new JPanel();
    inputDateValuePanel.setLayout(new FlowLayout());
    valuePanel.add(inputDateValuePanel);

    // Adding day value
    dayValueDisplay = new JLabel("Day:");
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
    getValueButton.setActionCommand("Value");
    getValueButton.addActionListener(this);
    outputValuePanel.add(getValueButton);

    // The label to show the value after it's been calculated
    returnedValue = new JLabel("Value");
    outputValuePanel.add(returnedValue);



    // The querying composition functions
    JPanel compositionPanel = new JPanel();
    compositionPanel.setBorder(BorderFactory.createTitledBorder("Get Composition"));
    compositionPanel.setLayout(new BoxLayout(compositionPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(compositionPanel);

    JPanel compositionPortfolioPanel = new JPanel();
    compositionPortfolioPanel.setLayout(new FlowLayout());
    compositionPanel.add(compositionPortfolioPanel);

    // Input for the desired portfolio
    compositionLabel = new JLabel("Portfolio title here:");
    compositionPortfolioPanel.add(compositionLabel);
    getPortfolioForComposition = new JTextField(10);
    compositionPortfolioPanel.add(getPortfolioForComposition);

    // Adding the input areas for the date
    JPanel inputDateCompPanel = new JPanel();
    inputDateCompPanel.setLayout(new FlowLayout());
    compositionPanel.add(inputDateCompPanel);

    // Adding day value
    dayCompDisplay = new JLabel("Day:");
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
    outputCompPanel.setLayout(new BoxLayout(outputCompPanel, BoxLayout.PAGE_AXIS));
    compositionPanel.add(outputCompPanel);

    // The function to determine the value of the portfolio selected
    JButton getCompButton = new JButton("Click to get the composition");
    getCompButton.setActionCommand("Composition");
    getCompButton.addActionListener(this);
    outputCompPanel.add(getCompButton);

    // The output for the formatting of the stock
    composition = new JLabel("Composition");
    outputCompPanel.add(composition);



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
    savePortfolioLabel = new JLabel("Portfolio title here:");
    savePortfolioLabelPanel.add(savePortfolioLabel);
    getPortfolioToSave = new JTextField(10);
    savePortfolioLabelPanel.add(getPortfolioToSave);

    // Save button
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    savePortfolioPanel.add(fileSavePanel);
    JButton fileSaveButton = new JButton("Save");
    fileSaveButton.setActionCommand("Save-File");
    fileSaveButton.addActionListener(this);
    fileSavePanel.add(fileSaveButton);

    // Load portfolio function
    JPanel loadPortfolioPanel = new JPanel();
    loadPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Load Portfolio"));
    loadPortfolioPanel.setLayout(new BoxLayout(loadPortfolioPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(loadPortfolioPanel);

    // Putting the info for the save info
    JPanel loadPortfolioInfoPanel = new JPanel();
    loadPortfolioInfoPanel.setLayout(new FlowLayout());
    loadPortfolioPanel.add(loadPortfolioInfoPanel);
    JLabel loadPortfolioInfo = new JLabel("<html>Given portfolio must be in .txt format,"
            + " and be located <br>in the given folder named saved_portfolios.</html>");
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
    JLabel loadGuide= new JLabel("Ex: Example.txt");
    loadPortfolioLabelPanel.add(loadGuide);

    // Load button
    JPanel loadPanel = new JPanel();
    loadPanel.setLayout(new FlowLayout());
    loadPortfolioPanel.add(loadPanel);

    JButton loadButton = new JButton("Load");
    loadButton.setActionCommand("Load-File");
    loadButton.addActionListener(this);
    loadPanel.add(loadButton);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    LocalDate date;

    switch (arg0.getActionCommand()) {
      case "Create-Portfolio":
        model.addPortfolio(new PortfolioImpl(pfield.getText()));
        resetListPortfolios();
        break;

      case "Buy":
        // Checking that the portfolio is valid
        if (checkValidPortfolio(inputPortfolioForStockAmount, inputPortfolioLabel)) {
          // Checking that the ticker is valid
          if (checkValidTicker(tickerField, tickerDisplay)) {
            // Checking that the number of shares added is valid
            if (checkValidShares(inputStockAmount, sharesDisplay)) {
              // Checking that the given date is valid
              if (checkValidDate(dayDisplay, dayInput,
                      monthInput, yearInput)) {
                date = toDate(dayInput, monthInput, yearInput);
                // Will add stock if all the above is true
                new AddStockTo(model, view,
                        inputPortfolioForStockAmount.getText(),
                        tickerField.getText(),
                        Double.parseDouble(inputStockAmount.getText()), date).execute();
                resetListPortfolios();
              }
            }
          }
        }
        break;

      case "Sell":
        // Checking that the portfolio is valid
        if (checkValidPortfolio(inputPortfolioForStockAmount, inputPortfolioLabel)) {
          // Checking that the ticker is valid
          if (checkValidTicker(tickerField, tickerDisplay)) {
            // Checking that the number of shares added is valid
            if (checkValidShares(inputStockAmount, sharesDisplay)) {
              // Checking that the given date is valid
              if (checkValidDate(dayDisplay, dayInput,
                      monthInput, yearInput)) {
                date = toDate(dayInput, monthInput, yearInput);
                // Will add stock if all the above is true
                new SellStockFrom(model, view,
                        inputPortfolioForStockAmount.getText(),
                        tickerField.getText(),
                        Double.parseDouble(inputStockAmount.getText()), date).execute();
                resetListPortfolios();
              }
            }
          }
        }
        break;

      case "Value":
        if (checkValidPortfolio(inputPortfolioForValue, titleOfPortfolioForValue)) {
          if (checkValidDate(dayValueDisplay, dayValueInput, monthValueInput, yearValueInput)) {
            returnedValue.setText("$" + this.model.getPortfolio(inputPortfolioForValue.getText())
                    .getPortfolioValue(toDate(dayValueInput, monthValueInput, yearValueInput)));
          }
        }
        break;

      case "Composition":
        if (checkValidPortfolio(getPortfolioForComposition, compositionLabel)) {
          if (checkValidDate(dayCompDisplay, dayCompInput, monthCompInput, yearCompInput)) {
            String formattedStocks = this.model.getPortfolio(getPortfolioForComposition.getText())
                    .formatStockOnDate(toDate(dayCompInput, monthCompInput, yearCompInput))
                    .replaceAll(System.lineSeparator(), "<br>");
            composition.setText("<html><body>" + formattedStocks + "</body></html>");
          }
        }
        break;

      case "Save-File":
        try {
          if (checkValidPortfolio(getPortfolioToSave, savePortfolioLabel)) {
            model.getPortfolio(getPortfolioToSave.getText()).savePortfolio();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;

      case "Load-File":
        try {
          if (!getPortfolioToLoad.getText().isEmpty()) {
            model.getFromTxt(getPortfolioToLoad.getText().substring(0,
                            getPortfolioToLoad.getText().length() - 4),
                    "saved_portfolios/" + getPortfolioToLoad.getText());
            resetListPortfolios();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
    }
  }

  private void resetListPortfolios() {
    String formattedPortfolios = model.formatPortfolios()
            .replaceAll(System.lineSeparator(), "<br>");
    addedPortfolio.setText("<html><body>" + formattedPortfolios + "</body></html>");
  }

  @Override
  public void itemStateChanged(ItemEvent arg0) {
      // Because there are no check boxes, nothing needs to be here
  }

  @Override
  public void valueChanged(ListSelectionEvent arg0) {

  }

  // Checks that the entered portfolio exists in the model
  private boolean checkValidPortfolio(JTextField input, JLabel label) {
    if (model.getPortfolio(input.getText()) == null) {
      label.setForeground(Color.RED);
      label.setText("Invalid portfolio: ");
      return false;
    } else {
      label.setForeground(Color.BLACK);
      label.setText("Portfolio title here:");
      return true;
    }
  }

  private boolean checkValidTicker(JTextField input, JLabel label) {
    if (input.getText().length() != 4) {
      label.setForeground(Color.RED);
      label.setText("Invalid Stock Ticker: ");
      return false;
    } else {
      label.setForeground(Color.BLACK);
      label.setText("Stock ticker here: ");
      return true;
    }
  }

  private boolean checkValidShares(JTextField input, JLabel label) {
    if ((input.getText().isEmpty()) || (Double.parseDouble(input.getText()) < 0)) {
      label.setForeground(Color.RED);
      label.setText("Invalid Number of Shares: ");
      return false;
    } else {
      label.setForeground(Color.BLACK);
      label.setText("Number of shares: ");
      return true;
    }
  }


  private boolean checkValidDate(JLabel dayLabel, JTextField dayInput,
                                 JTextField monthInput, JTextField yearInput) {

    if (dayInput.getText().isEmpty()
            || monthInput.getText().isEmpty()
            || yearInput.getText().isEmpty()) {
      dayLabel.setText("<html><font color='red'>Invalid Date    </font> Day: </html>");
      return false;
    }

    LocalDate date;

    try {
      int year = Integer.parseInt(yearInput.getText());
      int month = Integer.parseInt(monthInput.getText());
      int day = Integer.parseInt(dayInput.getText());

      date = LocalDate.of(year, month, day);
      dayLabel.setForeground(Color.BLACK);
      dayLabel.setText("Day: ");
      return true;

    } catch (DateTimeException e) {
      dayLabel.setText("<html><font color='red'>Invalid Date    </font> Day: </html>");
      return false;
    }
  }

  private LocalDate toDate(JTextField dayInput, JTextField monthInput, JTextField yearInput) {
    return LocalDate.of(Integer.parseInt(yearInput.getText()),
            Integer.parseInt(monthInput.getText()),
            Integer.parseInt(dayInput.getText()));
  }

}
