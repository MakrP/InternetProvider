package ua.epam.internetprovider.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;

import java.io.ByteArrayOutputStream;

public class TariffPDFGenerator {
    private static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD, BaseColor.BLACK);
    private static final Font PRICE_FONT = new Font(Font.FontFamily.HELVETICA, 15, Font.ITALIC | Font.UNDERLINE, BaseColor.RED);
    private static final Font SERVICE_FONT = new Font(Font.FontFamily.HELVETICA, 25, Font.ITALIC, BaseColor.DARK_GRAY);
    public static ByteArrayOutputStream generateTariffPDF(Tariff tariff,Service service) throws DocumentException {
        Document pdfDocument = new Document();
        ByteArrayOutputStream resultPdf = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(pdfDocument, resultPdf);
        pdfDocument.open();
        Paragraph paragraph = new Paragraph(tariff.getTitle());
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setFont(TITLE_FONT);
        pdfDocument.addTitle("Tariff Pdf");
        pdfDocument.add(paragraph);
        paragraph = new Paragraph("Price: " + tariff.getPrice());
        paragraph.setFont(PRICE_FONT);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        pdfDocument.add(paragraph);
        paragraph = new Paragraph("Service: " + service.getTitle());
        paragraph.setFont(SERVICE_FONT);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        pdfDocument.close();
        return resultPdf;
    }
}
