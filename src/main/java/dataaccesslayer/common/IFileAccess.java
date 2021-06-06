package dataaccesslayer.common;

import models.TourItem;
import models.TourTypes;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public interface IFileAccess {
    int CreateTourItemFile(String name, String url, LocalDateTime creationTime);
    int CreateTourLogFile(String logText, TourItem tourItem);
    List<File> SearchFiles(String searchTerm, TourTypes searchType);
    List<File> GetAllFiles(TourTypes tourType);
}
