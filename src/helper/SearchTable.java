package helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
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
	 * The value the search field should be reset to
	 */
	protected String resetSearchTerm;
	/**
	 * The model of the JTable to search in.
	 */
	protected DefaultTableModel model;
	/**
	 * The sorter for this JTable.
	 * Even if you don't want to sort the JTable, you have to create a TableSorter
	 * object to apply the filter.
	 */
	protected TableRowSorter<DefaultTableModel> sorter;
	/**
	 * The filter to apply to the JTable.
	 */
	protected RowFilter<DefaultTableModel, Integer> filter;
	/**
	 * A static list with all search terms per Model (connected to a table)
	 */
	protected static HashMap<DefaultTableModel, String> searchTerms = new HashMap<DefaultTableModel, String>();

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

		resetSearchTerm = this.searchField.getText();

		model = (DefaultTableModel) table.getModel();
		sorter = new TableRowSorter<DefaultTableModel>(model);
		this.table.setRowSorter(sorter);

		// Create the filter
		filter = new RowFilter<DefaultTableModel, Integer>() {

			@Override
			public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
				return SearchTable.shouldIncludeEntry(entry);
			}
		};

		// Add the event handlers
		this.searchField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent evt) {
				searchFieldFocus(evt);
			}
		});

		this.searchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				if (evt.getKeyCode() == 10) {
					searchFieldEnter(evt);
				}
			}
		});

		this.searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				searchButtonClicked(evt);
			}
		});

		this.resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				resetButtonClicked(evt);
			}
		});
	}

	/**
	 * Get the search term for a model
	 * 
	 * @param model The model to get the search term for
	 * @return The search term
	 */
	protected static String getSearchTermForModel(DefaultTableModel model) {
		return SearchTable.searchTerms.get(model);
	}

	/**
	 * The event handler for the gained focus on the search field
	 * 
	 * @param event The FocusEvent object
	 */
	protected void searchFieldFocus(FocusEvent event) {
		// Empty the field if it has the default value
		if (searchField.getText().equals(resetSearchTerm)) {
			searchField.setText(null);
		}
	}

	/**
	 * The event handler for pressing enter in the search field
	 * 
	 * @param event The KeyEvent object
	 */
	protected void searchFieldEnter(KeyEvent event) {
		// Do the search
		updateSearch(searchField.getText());
	}

	/**
	 * The event handler for resetting the search button
	 * 
	 * @param event The ActionEvent object
	 */
	protected void searchButtonClicked(ActionEvent event) {
		// Check if the search term is filled in
		if (searchField.getText().equals(resetSearchTerm)) {
			return;
		}

		// Do the search
		updateSearch(searchField.getText());
	}

	/**
	 * Change the filter for the table
	 * 
	 * @param searchTerm The term to search for
	 */
	protected void updateSearch(String searchTerm) {
		// Add or update the search term
		searchTerms.put(model, searchField.getText());

		// Show the reset button
		resetButton.setVisible(true);

		// Set the row filter
		sorter.setRowFilter(filter);
	}

	/**
	 * The event handler for clicking the reset button
	 * 
	 * @param event The ActionEvent object
	 */
	protected void resetButtonClicked(ActionEvent event) {
		// Reset the tables filter
		searchTerms.put(model, "");
		sorter.setRowFilter(filter);

		// Reset the search field
		searchField.setText(resetSearchTerm);

		// Hide the reset button
		resetButton.setVisible(false);
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

		String searchTerm = SearchTable.getSearchTermForModel(entry.getModel()).toLowerCase();

		for (int i = entry.getValueCount() - 1; i >= 0; i--) {
			if (entry.getStringValue(i).toLowerCase().contains(searchTerm)) {
				return true;
			}
		}

		return false;
	}
}
