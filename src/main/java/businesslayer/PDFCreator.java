package businesslayer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class PDFCreator implements IPDFCreator {

    @Override
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

            for (int i = 0; i < tourLogs.size(); i++) {
                document.add(new Paragraph("Date: " + tourLogs.get(i).getDate()));
                document.add(new Paragraph("Report: " + tourLogs.get(i).getReport()));
                document.add(new Paragraph("Distance: " + tourLogs.get(i).getDistance()));
                document.add(new Paragraph("Time: " + tourLogs.get(i).getTime()));
                document.add(new Paragraph("Rating: " + tourLogs.get(i).getRating()));
                document.add(new Paragraph("Weather: " + tourLogs.get(i).getWeather()));
                document.add(new Paragraph("Speed: " + tourLogs.get(i).getSpeed()));
                document.add(new Paragraph("Altitude: " + tourLogs.get(i).getAltitude()));
                document.add(new Paragraph("Difficulty: " + tourLogs.get(i).getDifficulty()));
                document.add(new Paragraph("Calories: " + tourLogs.get(i).getCalories()));
                document.add(new Paragraph("\n"));
            }
            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
