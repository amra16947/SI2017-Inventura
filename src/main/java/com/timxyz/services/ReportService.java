package com.timxyz.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.timxyz.models.Audit;
import com.timxyz.models.AuditItem;
import com.timxyz.models.Category;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReportService {

    private final String AUDIT_REPORT_FILENAME = "audit-report";
    private final String REPORT_PDF_EXTENSION = ".pdf";
    private final String DOCUMENT_CREATOR = "WS1001";
    private final String AUDIT_DOCUMENT_TITLE = "IZVJESTAJ: Inventura ";
    private final int AUDIT_TABLE_COLUMN_NUMBER = 10;
    private final String[] auditHeaders = {"SIFRA", "NAZIV SREDSTAVA", "JMJ", "POTVRDJENO PRISUSTVO",
        "POTVRDJEN INV BROJ", "STATUS", "DIO PODGRUPE", "PODGRUPA", "GRUPA", "NAPOMENA"};

    @Autowired
    private AuditService auditService;

    public String auditReportFilePath(long id) throws ServiceException {
        Audit audit = auditService.get(id);
        String filename = generateAuditPDFReport(audit);

        return filename;
    }

    // privremeno generisanje datoteke
    private String generateAuditPDFReport(Audit audit) throws ServiceException{
        try {
            String auditReportFilename = AUDIT_REPORT_FILENAME + Math.random() + REPORT_PDF_EXTENSION;

            OutputStream file = new FileOutputStream(new File(auditReportFilename));
            Document document = new Document(PageSize.A3.rotate());

            PdfWriter.getInstance(document, file);
            document.open();
            initializeDocument(document);
            addGeneralInfo(document, audit);

            PdfPTable pdfTable = new PdfPTable(AUDIT_TABLE_COLUMN_NUMBER);
            setAuditTableHeaders(pdfTable);

            for(AuditItem auditItem : audit.getAuditItems()) {
                addAuditItemToTable(pdfTable, auditItem);
            }

            document.add(pdfTable);

            document.close();
            file.close();

            return auditReportFilename;
        }
        catch (Exception e) {
            throw new ServiceException("Došlo je do greške prilikom kreiranja PDF dokumenta.");
        }
    }

    private void addGeneralInfo(Document document, Audit audit) throws DocumentException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");
        Date now = new Date();
        String dateString = dateFormat.format(now);

        Paragraph paragraph = new Paragraph(dateString);
        paragraph.add(new Paragraph(AUDIT_DOCUMENT_TITLE + audit.getName(),
                new Font(Font.FontFamily.HELVETICA, 18,
                Font.BOLD)));

        if (audit.getLocation().getParent() != null) {
            paragraph.add(new Paragraph("Organizaciona jedinica: " + audit.getLocation().getParent().getName()));
        }

        paragraph.add(new Paragraph("Lokacija: " + audit.getLocation().getName()));
        paragraph.add(new Paragraph("Odgovorna osoba: " + audit.getAccount().getFullName()));

        paragraph.add(new Paragraph(" "));

        document.add(paragraph);
    }

    private void initializeDocument (Document document) throws DocumentException {
        document.addTitle(AUDIT_DOCUMENT_TITLE);
        document.addCreationDate();
        document.addCreator(DOCUMENT_CREATOR);
    }

    private void setAuditTableHeaders(PdfPTable table) {
        for(int i = 0; i < AUDIT_TABLE_COLUMN_NUMBER; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(auditHeaders[i]));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        table.setHeaderRows(1);
    }

    private void addAuditItemToTable(PdfPTable table, AuditItem auditItem) {

        // SIFRA
        PdfPCell cell = new PdfPCell(new Phrase(auditItem.getItem().getSkuNumber()));
        table.addCell(cell);

        // NAZIV STAVKE
        cell = new PdfPCell(new Phrase(auditItem.getItem().getName()));
        table.addCell(cell);

        // MJERNA JEDINICA
        cell = new PdfPCell(new Phrase(auditItem.getItem().getUnitOfMeasurement()));
        table.addCell(cell);

        // POTVRDJENO PRISUSTVO STAVKE: DA ILI NE
        if (auditItem.getPresent()) {
            cell = new PdfPCell(new Phrase("DA"));
            cell.setBackgroundColor(BaseColor.GREEN);

        }
        else {
            cell = new PdfPCell(new Phrase("NE"));
            cell.setBackgroundColor(BaseColor.RED);
        }

        table.addCell(cell);

        // POTVRDJEN BROJ: DA ILI NE
        if(auditItem.getSkuCorrect()) {
            cell = new PdfPCell(new Phrase("DA"));
            cell.setBackgroundColor(BaseColor.GREEN);
        }
        else {
            cell = new PdfPCell(new Phrase("NE"));
            cell.setBackgroundColor(BaseColor.RED);
        }

        table.addCell(cell);

        // STATUS STAVKE
        cell = new PdfPCell(new Phrase(auditItem.getStatus().getName()));
        table.addCell(cell);

        // KATEGORIJA STAVKE - NPR MONITOR
        cell = new PdfPCell(new Phrase(auditItem.getItem().getCategory().getName()));
        table.addCell(cell);

        // RODITELJSKA KATEGORIJA - NPR RACUNARSKA OPREMA
        Category parentCategory = auditItem.getItem().getCategory().getParent();
        Category grandparentCategory = null;

        if (parentCategory != null) {
            grandparentCategory = parentCategory.getParent();
            cell = new PdfPCell(new Phrase(parentCategory.getName()));
        }
        else {
            cell = new PdfPCell(new Phrase("/"));
        }

        table.addCell(cell);

        // RODITELJSKA KATEGORIJA OD RODITELJSKE KATEGORIJE - NPR RACUNARSKA I MREZNA OPREMA
        if (grandparentCategory != null) {
            cell = new PdfPCell(new Phrase(grandparentCategory.getName()));
        }
        else {
            cell = new PdfPCell(new Phrase("/"));
        }

        table.addCell(cell);

        // NAPOMENA
        cell = new PdfPCell(new Phrase(auditItem.getNote()));
        table.addCell(cell);


    }

    // asinhrono brisanje datoteke nakon sto obradjen zahtjev
    public void deleteReportFile(String filepath) {
        File file = new File(filepath);
        if (file != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    file.delete();
                }
            }).start();
        }
    }
}
