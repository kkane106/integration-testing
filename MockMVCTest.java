@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"../WEB-INF/DaoTest-context.xml"})
@WebAppConfiguration
@Transactional // you will need this if you are using DAO methods with transactions
public class FilmControllerTest {
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Autowired
  private FilmController controller;

  private FilmDAO dao;

  @Before
  public void setUp() throws Exception {
    dao = wac.getBean(FilmDAOImpl.class);
    controller.setFilmDAO(dao);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @After
  public void tearDown() throws Exception {
    controller = null;
    mockMvc = null;
    wac = null;
  }
  @Test
  public void test_get_film_returns_film_view_and_model(){
    try {
      // Use MockMVC to create a request to the test servlet and get a response
      MvcResult response = mockMvc.perform(get("/GetFilm.do?id=1"))
                            .andExpect(
                                status().isOk()
                            ).andReturn();

      // Extract the returned ModelAndView from the response
      ModelAndView mv = response.getModelAndView();

      // Extract the Model from the ModelAndView
      ModelMap map = mv.getModelMap();

      // Cast the Object value paired to the "film" key to a Film object
      Film f = (Film) map.get("film");

      // Check the values with JUnit tests
      assertEquals("film.jsp", mv.getViewName());
      assertEquals("ACADEMY DINOSAUR", f.getTitle());

    } catch (Exception e) {
      fail(e.toString());
    }
  }
}
