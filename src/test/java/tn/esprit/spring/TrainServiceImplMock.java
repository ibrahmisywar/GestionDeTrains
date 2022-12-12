package tn.esprit.spring;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.entities.etatTrain;
import tn.esprit.spring.repository.TrainRepository;
import tn.esprit.spring.repository.VoyageRepository;
import tn.esprit.spring.services.ITrainService;
import tn.esprit.spring.services.IVoyageService;
import tn.esprit.spring.services.TrainServiceImpl;
import tn.esprit.spring.services.VoyageServiceImpl;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TrainServiceImplMock {
    @Mock
    TrainRepository sr;

    @InjectMocks
    ITrainService ss = new TrainServiceImpl();

    Train train1 = new Train(1, etatTrain.prevu,10);
    Stock train2 = new Train(2,etatTrain.en_gare,15);

    List<Train> listTrains = new ArrayList<Train>() {


        {
            add(new Train(3,etatTrain.annule,13));
            add(new Train(4,etatTrain.en_route,5));
        }
    };

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void retrieveAllTrainsTest() {
        when(sr.findAll()).thenReturn(listTrains);
        List<Train> result = ss.recupererAll();
        Assertions.assertEquals(listTrains, result);
    }

    @Test
    public void addTrainTest() {
        Train train4 = new Train();
        train4.setIdStock(5L);
        when(ss.ajouterTrain(train4)).thenReturn(train4);
        Train result = ss.ajouterTrain(train4).orElse(Null);
        Assertions.assertEquals(train4, result);
    }

    @Test
    public void deleteTrainTest() {
        doNothing().when(sr).deleteById(1L);
        when(sr.findById(1L)).thenReturn(Optional.ofNullable(null));
        Train result = ss.supprimerTrain(sr.findById(1L).orElse(Null));
        Assertions.assertNull(result);
    }

}
