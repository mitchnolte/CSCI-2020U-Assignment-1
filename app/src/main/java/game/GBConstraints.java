package game;

import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * Convenience class to make creating GridBagConstraints objects easier.
 * @see java.awt.GridBagConstraints GridBagConstraints
 */
public class GBConstraints extends GridBagConstraints {

  /**
   * Creates a GridBagConstraints object with fewer parameters than otherwise necessary.
   * @param gridx x value of grid.
   * @param gridy y value of grid.
   * @param gridwidth number of columns.
   * @param gridheight number of rows.
   * 
   * @see java.awt.GridBagConstraints#GridBagConstraints(int, int, int, int, double, double, int,
   *                                                     int, Insets, int, int) GridBagConstraints Constructor
   */
  public GBConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
    
    // Invokes GridBagConstraints constructor with mostly default values
    super(gridx, gridy, gridwidth, gridheight, 0, 0, GridBagConstraints.CENTER, 
          GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
  }

  /**
   * Creates a GridBagConstraints object with fewer parameters than otherwise necessary.
   * @param gridx x value of grid.
   * @param gridy y value of grid.
   * 
   * @see java.awt.GridBagConstraints#GridBagConstraints(int, int, int, int, double, double, int,
   *                                                     int, Insets, int, int) GridBagConstraints Constructor
   */
  public GBConstraints(int gridx, int gridy) {
    this(gridx, gridy, 1, 1);
  }
}