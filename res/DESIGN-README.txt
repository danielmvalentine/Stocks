Stock Program Design

Created by: Daniel Valentine and Baer Istok

The majority of our design choices for this assignmed arose from the simple fact that we were locked
into using Java Swing for our GUI. This led us to the idea of making one central panel with separate
smaller panels within it so that we didn't have to open or repaint panels as often.

Every main method we were instructed to write has its own unique panel, which in turn has individual
panels within them. Most methods that require an intake require the intake to be written out. We
felt that although this was a more difficult option for the user, it was only mildly difficult for
them at worst, and made the jobs of the developers worlds simpler.

Our finished product only repaints one panel, the panel that shows which portfolios exist at any
given time, which is ideal both for runtime and for coding difficulty.