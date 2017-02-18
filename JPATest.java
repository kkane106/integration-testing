@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../WEB-INF/Test-context.xml"})
@WebAppConfiguration
@Transactional
public class FilmTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private FilmDAO dao;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void setUp() {
    dao = wac.getBean(FilmDAOImpl.class);
  }

  @After
  public void tearDown() {
    dao = null;
    em = null;
    wac = null;
  }

  @Test
  public void test_film_mappings() {
    Film f = dao.show(1);
    assertEquals("ACADEMY_DINOSAUR", f.getTitle();
  }
}
