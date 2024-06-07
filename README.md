<h2>Stock Program</h2>
<h4>Created by: Danny Valentine and Baer Istok</h4>
<p>
  This program utilises a text-based interface to allow a user to examine the gain or loss of a 
  stock over a specified period, examine the x-day moving average of a stock for a specified date 
  and a specified value of x, determine which days are x-day crossovers for a specified stock over 
  a specified date range and a specified value of x, and create one or more portfolios, add or 
  remove stock to a portfolio, view the contents of a portfolio, and find the value of that 
  portfolio on a specific date.
</p>
<p>
  The structure of the code is based on the model, view, controller format, with both the main 
  functionality and the portfolio functionality each having their own controllers, models, and 
  views.
</p>
<br>
<p>
  <b>The main functions and the portfolio functions are separated into two menus with the main 
  functions being:</b>
  <p>
     <b>1 or stock-price-shift</b> - Takes in a stock's ticker symbol and both the starting and 
    ending date to be analyzed. Will print the gain or loss of a stock over the period of time 
    given.
  </p>
  <p>
    <b>2 or xday-moving-average</b> - Takes in a stock's ticker symbol, a date to begin the 
    analysis on, and the number of days (x) before the date given to perform the analysis on. Will 
    print the x-day moving average of the given stock based on the given user inputs.
  </p>
  <p>
    <b>3 or xday-crossovers</b> - Takes in a stock's ticker symbol, both the starting and ending 
    date to be analyzed, and the number of days (x) for the x-day moving average part of the 
    analysis. Will print the number of x-day crossovers given the user inputs.
  </p>
  <p>
    <b>4 or portfolio</b> - Opens the portfolio functions menu.
  </p>
  <p>
    <b>Q or Quit</b> - Quits the program.
  </p>
</p>
<br>
<p>
  <b>The portfolio functions are: </b>
  <p>
    <b>1 or create-portfolio</b> - Takes in a title for the new portfolio. Won't create a portfolio
    if the name provided is already a portfolio.
  </p>
  <p>
    <b>2 or list-portfolio</b> - Lists all created portfolios.
  </p>
  <p>
    <b>3 or add-stock-to</b> - Takes in the title of a created portfolio, the stock to be added to 
    the portfolio, and the number of shares to be added. Adds the given stock to the given
    portfolio.
  </p>
    <p>
    <b>4 or remove-portfolio</b> - Takes in the title of a created portfolio and the stock to be 
      removed. Removes the given stock from the given portfolio.
  </p>
    <p>
    <b>5 or examine-portfolio</b> - Takes in the title of a created portfolio and prints the stocks
      and the shares of the stocks in the given portfolio.
  </p>
    <p>
    <b>6 or get-value-of</b> - Takes in the title of a created portfolio and a date to calculate
      the value on. Will calculate and print the value of the given portfolio on the given date.
  </p>
    <p>
    <b>B or Back</b> - Returns to the main menu of the program.
  </p>
</p>
