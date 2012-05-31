/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.PropertyException;
import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.PieChartDataSet;
import org.jCharts.nonAxisChart.PieChart2D;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.PieChart2DProperties;



public class DashboardTest extends JFrame
{
  private JPanel panel;
    private PieChart2DProperties pieChart2DProperties;
    private LegendProperties legendProperties;
    private ChartProperties chartProperties;


  public DashboardTest() throws ChartDataException, PropertyException
  {
    initComponents();
  }


  private void initComponents() throws ChartDataException,PropertyException
  {
    this.setSize( 500, 500 );
    this.panel = new JPanel( true );
    this.panel.setSize( 500, 500 );
    this.getContentPane().add( this.panel );

    this.pieChart2DProperties = new PieChart2DProperties();
    this.legendProperties= new LegendProperties();
    this.chartProperties= new ChartProperties();

    this.setVisible( true );


    addWindowListener( new java.awt.event.WindowAdapter()
    {
      public void windowClosing( WindowEvent windowEvent )
      {
        exitForm( windowEvent );
      }
    }
    );
  }


  /************************************************************************
	*
	* @param graphics
	***********************************************************************/
  public void paint( Graphics graphics )
  {
    try {
      String[] labels = {"BMW", "Audi", "Lexus"};
      String title = "Cars that Own";
      Paint[] paints = {Color.blue, Color.gray, Color.red};
      double[] data = {50d, 30d, 20d};
      PieChartDataSet pieChartDataSet = new PieChartDataSet( title, data,
                               labels, paints, this.pieChart2DProperties );
      Dimension dimension= this.panel.getSize();
      PieChart2D pieChart2D = new PieChart2D( pieChartDataSet,
                                              this.legendProperties,
                                              this.chartProperties,
                                              (int) dimension.getWidth(),
                                              (int) dimension.getHeight() );

       //***** BEGIN SWING SPECIFIC CODE *******************************
       pieChart2D.setGraphics2D( (Graphics2D) this.panel.getGraphics() );
            try {
                pieChart2D.render();
                //***** END SWING SPECIFIC CODE *********************************
            } catch (org.jCharts.properties.PropertyException ex) {
                Logger.getLogger(DashboardTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    catch( ChartDataException chartDataException ) {
      chartDataException.printStackTrace();
    }
    catch( Exception propertyException ) {
      propertyException.printStackTrace();
    }
  }


  private void exitForm( WindowEvent windowEvent )
  {
    System.exit( 0 );
  }


  public static void main( String args[] ) throws ChartDataException,
                                                        PropertyException
  {
    new DashboardTest();
  }
}
