package pe.com.bn.msds.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestDocumentPdf {

	public static void main(String[] args)  throws FileNotFoundException, DocumentException {
		
		// create document
        Document document = new Document(PageSize.A4, 30, 10, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HeaderFooter04.pdf"));

        // add header and footer
//        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//        writer.setPageEvent(event);
//
//        // write to document
//        document.open();
//        document.add(new Paragraph("Adding a header to PDF Document using iText."));
//        document.newPage();
//        document.add(new Paragraph("Adding a footer to PDF Document using iText."));
//        document.close();
        
	}

}
