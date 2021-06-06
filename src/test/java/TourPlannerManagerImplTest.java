import businesslayer.MapQuest;
import businesslayer.TourPlannerManagerImpl;
import dataaccesslayer.postgresSqlServer.TourItemPostgresDAO;
import models.TourItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TourPlannerManagerImplTest {


    private List<TourItem> tours;
    @Mock
    private TourPlannerManagerImpl manager;
    @BeforeEach
    public void setUp() {
        tours = List.of(new TourItem(1, "name", "des", "distance", "start", "end"), new TourItem(2, "NAME", "des", "distance", "start", "end"));
    }

    @Test
    public void testIfDeleteTourImageDoesDeleteImage() throws IOException, InterruptedException {
        MapQuest mapQuest = new MapQuest();
        String name = "test";
        String filePath = "Images/" + name + ".jpg";
        mapQuest.startMapQuest(name, "wien", "graz");
        File file = new File (filePath);
        assertTrue(file.exists());
        manager = new TourPlannerManagerImpl();
        manager.DeleteTourImage(filePath);
        assertFalse(file.exists());
    }

    @Test
    public void testIfGetItemsReturnsValue() throws SQLException {
        when(manager.GetItems()).thenReturn(tours);
        assertNotNull(manager.GetItems());
        assertEquals(2, manager.GetItems().size());
    }

    @Test
    public void testIfGetItemReturnsValue() throws SQLException {
        when(manager.GetItem(1)).thenReturn(tours.get(0));
        assertNotNull(manager.GetItem(1));
    }

}
