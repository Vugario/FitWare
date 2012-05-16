package helper;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Daan
 */
public class SearchTable {

	/**
	 * The JTable to search in.
	 */
	protected JTable table;
	/**
	 * The input field to type the search term in.
	 */
	protected JTextField searchField;
	/**
	 * The search button
	 */
	protected JButton searchButton;
	/**
	 * The reset button
	 */
	protected JButton resetButton;
	/**
	 * The model of the JTable to search in.
	 */
	protected DefaultTableModel model;
	/**
	 * The filter to apply to the JTable.
	 */
	protected RowFilter<DefaultTableModel, Integer> filter;
	/**
	 * The sorter for this JTable.
	 * Even if you don't want to sort the JTable, you have to create a TableSorter
	 * object to apply the filter.
	 */
	protected TableRowSorter<DefaultTableModel> sorter;

	/**
	 * The constructor for this object.
	 * 
	 * @param table The JTable to search in
	 * @param searchField The JTextField where the search term is filled in
	 * @param searchButton The JButton used to trigger the search action
	 * @param resetButton The JButton used to reset the search action
	 */
	public SearchTable(JTable table, JTextField searchField, JButton searchButton, JButton resetButton) {

		this.table = table;
		this.searchField = searchField;
		this.searchButton = searchButton;
		this.resetButton = resetButton;

		this.model = (DefaultTableModel) table.getModel();
		this.filter = new RowFilter<DefaultTableModel, Integer>() {

			@Override
			public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
				return SearchTable.shouldIncludeEntry(entry);
			}
		};
		this.sorter = new TableRowSorter<DefaultTableModel>(this.model);
		this.sorter.setRowFilter(this.filter);

	}

	/**
	 * This method does the actual searching.
	 * Returns true if the specified entry should be shown; returns false if the
	 * entry should be hidden.
	 * 
	 * @see RowFilter
	 * 
	 * @param entry a non-null object that wraps the underlying object from the model
	 * @return true if the entry should be shown
	 */
	public static boolean shouldIncludeEntry(RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
		/*
		Invoices invoicePage = (Invoices) Main.getApplication().getPage("invoices");
		String searchterm = invoicePage.getSearchTerm();
		for (int i = entry.getValueCount() - 1; i >= 0; i--) {
			if (entry.getStringValue(i).toLowerCase().contains(searchterm)) {
				// The value starts with "a", include it
				return true;
			}
		}
		// None of the columns start with "a"; return false so that this
		// entry is not shown
		return false;
		*/
		
		return true;
	}
}
