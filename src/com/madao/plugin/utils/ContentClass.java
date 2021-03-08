//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.utils;

import com.madao.plugin.EntityClasses;

public class ContentClass {
	public ContentClass() {
	}

	public static String getTestContent(EntityClasses entityClasses) {
		String var10000 = entityClasses.getControllerClass().getName();
		return "@SpringBootTest\n@AutoConfigureMockMvc\n@RunWith(SpringRunner.class)\npublic class "
				+ var10000 + "Test {\n    //TODO: create the data Test generator class "
				+ entityClasses.getEntityName() + "Builder\n    private static final String ENDPOINT_URL = \"/"
				+ entityClasses.getControllerPath() + "s\";\n    @MockBean\n    private EntityMapper entityMapper;\n    @InjectMocks\n    private "
				+ entityClasses.getControllerClass().getName()+" "
				+ entityClasses.getEntityName().toLowerCase() + "Controller;\n    @MockBean\n    private "
				+ entityClasses.getEntityName() + "Service "
				+ entityClasses.getEntityName().toLowerCase() + "Service;\n    @MockBean\n    private "
				+ entityClasses.getEntityName() + "Mapper "
				+ entityClasses.getEntityName().toLowerCase() + "Mapper;\n    @Autowired\n    private MockMvc mockMvc;\n\n    @Before\n    public void setup() {\n       this.mockMvc = MockMvcBuilders.standaloneSetup(this."
				+ entityClasses.getEntityName().toLowerCase() + "Controller).build();\n    }\n\n    @Test\n    public void getAll() throws Exception {\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toDto(("+entityClasses.getEntityName()+")ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getListDTO());\n\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.findAll()).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getListEntities());\n        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))\n                " +
				".andExpect(MockMvcResultMatchers.status().isOk())\n                .andExpect(MockMvcResultMatchers.content()\n                        " +
				".contentType(MediaType.APPLICATION_JSON_UTF8))\n                .andExpect(MockMvcResultMatchers.jsonPath" +
				"(\"$\", Matchers.hasSize(2)));\n\n    }\n\n    @Test\n    public void getById() throws Exception {\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toDto(("+entityClasses.getEntityName()+")ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getDTO());\n\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.findById(ArgumentMatchers.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "())).thenReturn(java.util.Optional.of("
				+ entityClasses.getEntityName() + "Builder.getEntity()));\n\n        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + \"/1\"))\n                " +
				".andExpect(MockMvcResultMatchers.status().isOk())\n                .andExpect(MockMvcResultMatchers.content()\n                        " +
				".contentType(MediaType.APPLICATION_JSON_UTF8))\n                .andExpect(MockMvcResultMatchers.jsonPath(\"$.id\", Is.is(1)));\n        Mockito.verify("
				+ entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).findById(1L);\n        Mockito.verifyNoMoreInteractions("
				+ entityClasses.getEntityName().toLowerCase() + "Service);\n    }\n\n    @Test\n    public void save() throws Exception {\n       Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toEntity(("+entityClasses.getEntityName()+"DTO)ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.save(ArgumentMatchers.any("
				+ entityClasses.getEntityName() + ".class))).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n\n        mockMvc.perform(\n                MockMvcRequestBuilders.post(ENDPOINT_URL)\n                        .contentType(MediaType.APPLICATION_JSON_UTF8)\n                        .content(CustomUtils.asJsonString("
				+ entityClasses.getEntityName() + "Builder.getDTO())))\n                .andExpect(MockMvcResultMatchers.status().isCreated());\n        Mockito.verify("
				+ entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).save(ArgumentMatchers.any("
				+ entityClasses.getEntityName() + ".class));\n        Mockito.verifyNoMoreInteractions("
				+ entityClasses.getEntityName().toLowerCase() + "Service);\n    }\n\n    @Test\n    public void update() throws Exception {\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toEntity(("+entityClasses.getEntityName()+"DTO)ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.update(ArgumentMatchers.any(), ArgumentMatchers.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n\n        mockMvc.perform(\n                MockMvcRequestBuilders.put(ENDPOINT_URL + \"/1\")\n                        .contentType(MediaType.APPLICATION_JSON_UTF8)\n                        .content(CustomUtils.asJsonString("
				+ entityClasses.getEntityName() + "Builder.getDTO())))\n                .andExpect(MockMvcResultMatchers.status().isOk());\n        Mockito.verify(" + entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).update(ArgumentMatchers.any("
				+ entityClasses.getEntityName() + ".class), ArgumentMatchers.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "());\n        Mockito.verifyNoMoreInteractions("
				+ entityClasses.getEntityName().toLowerCase() + "Service);\n    }\n\n    @Test\n    public void delete() throws Exception {\n        Mockito.doNothing().when("
				+ entityClasses.getEntityName().toLowerCase() + "Service).deleteById(ArgumentMatchers.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "());\n        mockMvc.perform(\n                MockMvcRequestBuilders.delete(ENDPOINT_URL + \"/1\"))\n                .andExpect(MockMvcResultMatchers.status().isOk());\n        Mockito.verify("
				+ entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).deleteById(Mockito.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "());\n        Mockito.verifyNoMoreInteractions("
				+ entityClasses.getEntityName().toLowerCase() + "Service);\n    }\n }";
	}

	public static String getUtilTest() {
		return "public class CustomUtils {\n    " +
				"public static String asJsonString(final Object obj) {\n        " +
				"try {\n            " +
					"return new ObjectMapper().writeValueAsString(obj);\n        " +
				"} catch (Exception e) {\n            " +
					"throw new RuntimeException(e);\n        " +
					"}\n    " +
				"}\n" +
				"}";
	}

	public static String getBuilderClass() {
		return "public class RoleBuilder {\n    " +
				"public static String asJsonString(final Object obj) {\n        " +
				"try {\n            " +
					"return new ObjectMapper().writeValueAsString(obj);\n        " +
				"} catch (Exception e) {\n            " +
					"throw new RuntimeException(e);\n        " +
					"}\n    " +
				"}\n" +
				"}";
	}
}
