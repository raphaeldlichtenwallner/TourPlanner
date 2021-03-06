package businesslayer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFCreator{

    public void CreatePdfForSingleTour(TourItem tour, ObservableList<TourLog> tourLogs) {
        Document document = new Document();
        String name = tour.getName();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("PDF/" + name + ".pdf"));
            document.open();

            Paragraph tourName = new Paragraph("Tour: " + name);
            tourName.setAlignment(Element.ALIGN_CENTER);
            document.add(tourName);

            Image image = Image.getInstance("Images/" + name + ".jpg");
            image.scaleAbsolute(200f, 150f);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);

            Paragraph logs = new Paragraph("\nLogs");
            logs.setAlignment(Element.ALIGN_CENTER);
            document.add(logs);

            for (TourLog tourLog : tourLogs) {
                document.add(new Paragraph("Date: " + tourLog.getDate()));
                document.add(new Paragraph("Report: " + tourLog.getReport()));
                document.add(new Paragraph("Distance: " + tourLog.getDistance()));
                document.add(new Paragraph("Time: " + tourLog.getTime()));
                document.add(new Paragraph("Rating: " + tourLog.getRating()));
                document.add(new Paragraph("Weather: " + tourLog.getWeather()));
                document.add(new Paragraph("Speed: " + tourLog.getSpeed()));
                document.add(new Paragraph("Altitude: " + tourLog.getAltitude()));
                document.add(new Paragraph("Difficulty: " + tourLog.getDifficulty()));
                document.add(new Paragraph("Calories: " + tourLog.getCalories()));
                document.add(new Paragraph("\n"));
            }
            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
