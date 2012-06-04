package helper;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFPage;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import main.Application;
import view.popups.ErrorPopup;

/**
 * Create a pdf file for an invoice
 * 
 * @author Daan
 */
public class PdfCreator {

	/**
	 * The temporary pdf file
	 */
	protected File tempfile;

	/**
	 * Create a pdf file for an invoice and save the pdf in the database
	 * 
	 * @param content The content that will be put in the pdf file
	 */
	public PdfCreator(JComponent content) {

		try {
			// Create a PDF Document
			PDFDocument document = new PDFDocument();

			// Create a page
			PDFPage page = document.createPage(null);

			// And add the page to the document
			document.addPage(page);

			// Print the Invoice template on the page
			Graphics2D g2d = page.createGraphics();
			content.print(g2d);

			// Create temp file
			tempfile = File.createTempFile("temp-invoice", ".pdf");

			// Delete temp file when program exits.
			tempfile.deleteOnExit();

			// Write to temp file
			document.saveDocument(tempfile.getAbsolutePath());

		} catch (IOException ex) {
			ExceptionHandler.handle(ex, ExceptionHandler.TYPE_SYSTEM_ERROR);
		}
	}

	/**
	 * Open the newly created PDF file
	 */
	public void openFile() {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(tempfile);
			} catch (IOException ex) {
				// no application registered for PDFs
				Application.getInstance().showPopup(new ErrorPopup(
						"There is no programm registered to open PDF's"));
			}
		}
	}
	
	/**
	 * Get the newly created PDF file
	 * 
	 * @return File The newly created file
	 */
	public File getFile() {
		return tempfile;
	}
}
