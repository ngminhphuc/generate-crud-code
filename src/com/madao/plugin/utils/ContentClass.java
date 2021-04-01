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
		String className = entityClasses.getControllerClass().getName();
		return "@Transactional\npublic class " + className + "Test {\n    //TODO: create the data Test generator class "
				+ entityClasses.getEntityName() + "Builder\n    private static final String ENDPOINT_URL = \"/"
				+ entityClasses.getControllerPath() + "s\";\n @InjectMocks\n    private "+entityClasses.getEntityName()+"MapperImpl "+entityClasses.getEntityName().toLowerCase() +"Mapper;\n       @InjectMocks\n    private "
				+ entityClasses.getControllerClass().getName()+" "
				+ entityClasses.getEntityName().toLowerCase() + "Controller;\n    @Mock\n    private "
				+ entityClasses.getEntityName() + "Service "
				+ entityClasses.getEntityName().toLowerCase() + "Service;\n private MockMvc mockMvc;\n\n    " +
				"@BeforeEach\n" +
				"    void setUp() {\n" +
				"        MockitoAnnotations.initMocks(this);\n" +
				"        mockMvc = MockMvcBuilders\n" +
				"                .standaloneSetup("+ entityClasses.getEntityName().toLowerCase() + "Controller)\n" +
				"                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())\n" +
				"                //.addFilter(CustomFilter::doFilter)\n" +
				"                .build();\n" +
				"    }"+
				""+
				"@Test\n" +
				"    void findAllByPage() throws Exception {\n" +
				"\n" +
				"        "+ entityClasses.getEntityName() + "Dto "+ entityClasses.getEntityName().toLowerCase() + " = new "+ entityClasses.getEntityName() + "Dto();\n" +
				"        "+ entityClasses.getEntityName().toLowerCase() + ".setId(\"1\");\n" +
				"\n" +
				"        Page<"+ entityClasses.getEntityName() + "Dto> page = new PageImpl<>(Collections.singletonList("+ entityClasses.getEntityName().toLowerCase() + "));\n" +
				"\n" +
				"\t    Mockito.when("+ entityClasses.getEntityName().toLowerCase() + "Service.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);\n" +
				"\n" +
				"        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)\n" +
				"\t\t        .accept(MediaType.APPLICATION_JSON))\n" +
				"\t\t        .andDo(MockMvcResultHandlers.print())\n" +
				"\t\t        .andExpect(MockMvcResultMatchers.status().isOk())\n" +
				"                .andExpect(MockMvcResultMatchers.jsonPath(\"$.data.content\", Matchers.hasSize(1)));\n" +
				"\n" +
				"\t    Mockito.verify("+ entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());\n" +
				"\t    Mockito.verifyNoMoreInteractions("+ entityClasses.getEntityName().toLowerCase() + "Service);\n" +
				"\n" +
				"    }"+
				"@Test\n    public void getById() throws Exception {\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toDto(("+entityClasses.getEntityName()+")ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getDto());\n\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.findById(ArgumentMatchers.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "())).thenReturn(java.util.Optional.of("
				+ entityClasses.getEntityName() + "Builder.getEntity()));\n\n        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + \"/1\"))\n                " +
				".andExpect(MockMvcResultMatchers.status().isOk())\n                .andExpect(MockMvcResultMatchers.content()\n                        " +
				".contentType(MediaType.APPLICATION_JSON_UTF8))\n                .andExpect(MockMvcResultMatchers.jsonPath(\"$.id\", Is.is(1)));\n        Mockito.verify("
				+ entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).findById(\"1\");\n        Mockito.verifyNoMoreInteractions("
				+ entityClasses.getEntityName().toLowerCase() + "Service);\n    }\n\n    @Test\n    public void save() throws Exception {\n       Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toEntity(("+entityClasses.getEntityName()+"Dto)ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.save(ArgumentMatchers.any("
				+ entityClasses.getEntityName() + ".class))).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n\n        mockMvc.perform(\n                MockMvcRequestBuilders.post(ENDPOINT_URL)\n                        .contentType(MediaType.APPLICATION_JSON_UTF8)\n                        .content(CustomUtils.asJsonString("
				+ entityClasses.getEntityName() + "Builder.getDto())))\n                .andExpect(MockMvcResultMatchers.status().isCreated());\n        Mockito.verify("
				+ entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).save(ArgumentMatchers.any("
				+ entityClasses.getEntityName() + ".class));\n        Mockito.verifyNoMoreInteractions("
				+ entityClasses.getEntityName().toLowerCase() + "Service);\n    }\n\n    @Test\n    public void update() throws Exception {\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Mapper.toEntity(("+entityClasses.getEntityName()+"Dto)ArgumentMatchers.any())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n        Mockito.when("
				+ entityClasses.getEntityName().toLowerCase() + "Service.update(ArgumentMatchers.any(), ArgumentMatchers.any"
				+ entityClasses.getIdTypeUpperCaseFirstChar() + "())).thenReturn("
				+ entityClasses.getEntityName() + "Builder.getEntity());\n\n        mockMvc.perform(\n                MockMvcRequestBuilders.put(ENDPOINT_URL + \"/1\")\n                        .contentType(MediaType.APPLICATION_JSON_UTF8)\n                        .content(CustomUtils.asJsonString("
				+ entityClasses.getEntityName() + "Builder.getDto())))\n                .andExpect(MockMvcResultMatchers.status().isOk());\n        Mockito.verify(" + entityClasses.getEntityName().toLowerCase() + "Service, Mockito.times(1)).update(ArgumentMatchers.any("
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

	public static String getBuilderClass(EntityClasses entityClasses) {
		return "public class "+entityClasses.getEntityName()+"Builder { " +
				"public static "+entityClasses.getEntityName()+"Dto getDto() {\n" +
				"\t\treturn "+entityClasses.getEntityName().toLowerCase()+"Mapper.toDto(new "+entityClasses.getEntityName()+"());\n" +
				"\t}\n" +
				"\tpublic static "+entityClasses.getEntityName()+" getEntity() {\n" +
				"\t\treturn "+entityClasses.getEntityName().toLowerCase()+"Mapper.toEntity(new "+entityClasses.getEntityName()+"Dto());\n" +
				"\t}" +
				"}";
	}
}
