//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.madao.plugin.classes.*;
import com.madao.plugin.utils.ContentClass;
import com.madao.plugin.utils.MyStringUtils;
import com.madao.plugin.utils.PsiUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GeneratorAction extends AnAction {
    private static final String TITLE_INFORMATION = "Information";
    private static final String TITLE = "Madaoo Demo";
    private Project project;
    private PsiDirectory containerDirectory;
    private PsiUtils psiUtils;
    private Module module;
    private PsiDirectory parentDirectory;
    private PsiPackageStatement packageFile;
    private PsiDirectory containerDirectoryTest;
    private Project testProject;
    private PsiDirectory controllerTestDirectory;


    public static void main(String[] args) {
        System.out.println("test\"".replace("\"", ""));
    }

    public GeneratorAction() {
    }

    @Override
    public synchronized void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        this.project = anActionEvent.getProject();
        this.psiUtils = PsiUtils.of(this.project);
        PsiFile psiFile = (PsiFile)anActionEvent.getData(LangDataKeys.PSI_FILE);
        if (!(psiFile instanceof PsiJavaFile)) {
            Messages.showMessageDialog(this.project, "You shoud run this generator on Java file", "Information", Messages.getInformationIcon());
            return;
        }
        PsiJavaFile javaFile = (PsiJavaFile)psiFile;
        PsiClass[] classes = javaFile.getClasses();
        if (0 == classes.length) {
            Messages.showMessageDialog(this.project, "No class found", "Information", Messages.getInformationIcon());
            return;
        }
        PsiClass aClass = classes[0];
        if (null == aClass.getAnnotation("javax.persistence.Entity")) {
            Messages.showMessageDialog(this.project, "this class is not an entity", "Information", Messages.getInformationIcon());
            return;
        }
        this.containerDirectory = javaFile.getContainingDirectory();
        this.module = FileIndexFacade.getInstance(this.project).getModuleForFile(psiFile.getVirtualFile());
        EntityClasses entityClasses = (new EntityClasses()).setServiceDirectory(createServiceDirectory()).setEntityClass(aClass);
        this.parentDirectory = entityClasses.getServiceDirectory().getParent();
        Optional<VirtualFile> optionalVirtualFile = ProjectRootManager.getInstance(this.project).getModuleSourceRoots(JavaModuleSourceRootTypes.TESTS).stream().findFirst();
        if (optionalVirtualFile.isPresent()) {
            VirtualFile virtualFile = optionalVirtualFile.get();
            this.containerDirectoryTest = PsiManager.getInstance(this.project).findDirectory(virtualFile);
            assert this.containerDirectoryTest != null;
            String packageName = javaFile.getPackageName();
            String last = packageName.substring(packageName.lastIndexOf('.') + 1);
            String folderName = packageName.replace(last, "");
            this.controllerTestDirectory = this.psiUtils.getOrCreateSubDirectory(this.containerDirectoryTest, folderName + "controller");
            this.testProject = this.containerDirectoryTest.getProject();
        } else {
            Messages.showMessageDialog(this.project, "Module test not defined", "Information", Messages.getInformationIcon());
        }
        WriteCommandAction.runWriteCommandAction(this.project, () -> createRepository(entityClasses));
    }

    private void createControllerUnitTest(EntityClasses entityClasses) {
        String testName = entityClasses.getControllerClass().getName() + "Test";
        String content = ContentClass.getTestContent(entityClasses);
        ClassCreator.of(this.testProject).init(testName, content)
                .importClass(entityClasses.getControllerClass().getName())
                .importClass(entityClasses.getDtoClass())
                .importClass(entityClasses.getServiceClass())
                .importClass(entityClasses.getMapperClass())
                .importClass(entityClasses.getEntityClass())
                .importClass("EntityMapper").importClass("org.junit.Before")
                .importClass("java.util.Arrays").importClass("org.hamcrest.Matchers")
                .importClass("com.google.gson.Gson").importClass("org.junit.Test")
                .importClass("org.junit.runner.RunWith").importClass("org.mockito.InjectMocks")
                .importClass("org.mockito.Mock").importClass("org.mockito.Mockito")
                .importClass("org.mockito.ArgumentMatchers")
                .importClass("java.util.List")
                .importClass("ResourceNotFoundException")
                .importClass("BeanUtil")
                .importClass("org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc")
                .importClass("org.springframework.boot.test.context.SpringBootTest")
                .importClass("org.springframework.http.MediaType")
                .importClass("org.springframework.test.context.junit4.SpringRunner")
                .importClass("org.springframework.test.web.servlet.MockMvc")
                .importClass("org.springframework.test.web.servlet.setup.MockMvcBuilders")
                .importClass("org.springframework.test.context.junit4.SpringRunner")
                .importClass("org.springframework.test.web.servlet.MockMvc")
		        .importClass("org.springframework.test.web.servlet.request.MockMvcRequestBuilders")
                .importClass("org.springframework.test.web.servlet.result.MockMvcResultHandlers")
                .importClass("org.springframework.test.web.servlet.result.MockMvcResultMatchers")
                .importClass("org.springframework.test.web.servlet.setup.MockMvcBuilders")
                .importClass("org.springframework.boot.test.mock.mockito.MockBean")
                .importClass("org.springframework.beans.factory.annotation.Autowired")
                .importClass("org.mockito.ArgumentMatchers.*")
                .importClass("org.springframework.test.web.servlet.result.MockMvcResultMatchers")
                .importClass("org.hamcrest.core.Is").
                importClass("org.mockito.ArgumentMatchers")
                .importClass("org.mockito.Mockito")
                .importClass(entityClasses.getEntityClassName()+"Builder")
                .importClass("CustomUtils").addTo(this.controllerTestDirectory);
    }

    private void createClasses(EntityClasses entityClasses) {
        String className = entityClasses.getEntityClassName();

        assert className != null;

        String entityName = className.replace("Entity", "");
        PsiDirectory dtoDirectory = null == this.containerDirectory.getParent() ? this.containerDirectory : this.psiUtils.getOrCreateSubDirectory(this.containerDirectory.getParent(), "dto");
        String dtoContent = "public class " + entityName + "DTO extends AbstractDTO<" + entityClasses.getIdType() + ">";
        ClassCreator.of(this.project).init("AbstractDTO", AbstractDTOClass.getCoreString()).addTo(dtoDirectory);
        dtoContent = dtoContent + "{}";
        ClassCreator.of(this.project).init(entityName + "DTO", dtoContent).copyFields(entityClasses.getEntityClass()).addTo(dtoDirectory).and((dtoClass) -> {
            this.createMapperClass(entityClasses.setDtoClass(dtoClass));
        });
    }

    private PsiDirectory createServiceDirectory() {
        return null == this.containerDirectory.getParent() ? this.containerDirectory : this.psiUtils.getOrCreateSubDirectory(this.containerDirectory.getParent(), "service");
    }

    private void createMapperClass(EntityClasses entityClasses) {
        String entityName = entityClasses.getEntityName();
        PsiDirectory mapperDirectory = null == this.containerDirectory.getParent() ? this.containerDirectory : this.psiUtils.getOrCreateSubDirectory(this.containerDirectory.getParent(), "mapper");
        entityClasses.setMapperDirectory(mapperDirectory);
        Optional<PsiClass> entityMapperClassOptional = this.psiUtils.findClass("EntityMapper");
        Consumer<PsiClass> createMapperFunction = (entityMapperClass) -> {
            String mapperName = entityName + "Mapper";
            ClassCreator.of(this.project).init(mapperName,
                    "@Mapper(componentModel = \"spring\")public interface " + mapperName + " extends EntityMapper<" +
                            entityClasses.getDtoClass().getName() + ", " +
                            entityClasses.getEntityClass().getName() + "> { \n}").importClass("org.mapstruct.Mapper")
                    .importClass(entityClasses.getEntityClass()).importClass(entityClasses.getDtoClass())
                    .importClass("org.mapstruct.Mapping").addTo(mapperDirectory).and((mapperClass) -> {
                this.psiUtils.importClass(mapperClass, new PsiClass[]{entityClasses.getDtoClass(), entityMapperClass});
                entityClasses.setMapperClass(mapperClass);
            });
        };
        if (entityMapperClassOptional.isPresent()) {
            createMapperFunction.accept((PsiClass)entityMapperClassOptional.get());
        } else {
            ClassCreator.of(this.project).init("EntityMapper", EntityMapperClass.getCoreString())
                    .importClass("java.util.List")
                    .importClass("java.util.Set")
                    .addTo(mapperDirectory).and(createMapperFunction);
        }

    }


    private void createService(EntityClasses entityClasses) {
//        String serviceName = entityClasses.getServiceClass().getName();

        String serviceName = entityClasses.getEntityName().concat("Service");
        PsiDirectory serviceImplDirectory = entityClasses.getServiceDirectory();
        StringBuilder content = (new StringBuilder("@Service @Transactional public class "))
                .append(serviceName).append("{");
        PsiClass repositoryClass = entityClasses.getRepositoryClass();
        String saveAllMethod = "save";
        if (0 != repositoryClass.findMethodsByName("saveAll", true).length) {
            saveAllMethod = "saveAll";
        }

        content.append(ServiceImplClass.getContent(entityClasses));
        content.append("}");
        ClassCreator.of(this.project).init(serviceName, content.toString())
                .importClass(entityClasses.getEntityClass())
                .importClass("org.springframework.stereotype.Service")
                .importClass("Transactional").importClass("java.util.Optional")
                .importClass("java.util.List").importClass("PageHelper")
                .importClass("AbstractBaseEntityService")
                .importClass("com.github.pagehelper.PageInfo")
                .importClass("org.mapstruct.factory.Mappers")
                .importClass("org.springframework.data.domain.Pageable")
                .importClass("org.springframework.data.domain.Page")
                .importClass("org.springframework.data.domain.PageImpl")
                .addTo(serviceImplDirectory).and((implClass) -> {
            this.psiUtils.importClass(implClass, new PsiClass[]{
                    entityClasses.getRepositoryClass(),
                    entityClasses.getMapperClass(),
                    entityClasses.getDtoClass()});
            entityClasses.setServiceClass(implClass);
            this.psiUtils.importClass(implClass, new PsiClass[0]);
            this.createController(entityClasses);
                });
    }

    private String setCreateUpdateTimeIfExist(PsiField[] fields) {
        StringBuilder builder = new StringBuilder();
        PsiField[] var3 = fields;
        int var4 = fields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PsiField psiField = var3[var5];
            if (((String)Objects.requireNonNull(psiField.getName())).toLowerCase().contains("createdatetime")) {
                builder.append("dto.setCreateDateTime(optionalDto.get().getCreateDateTime());");
            }

            if (psiField.getName().toLowerCase().contains("updatedatetime")) {
                builder.append("dto.setUpdateDateTime(LocalDateTime.now());");
            }
        }

        return builder.toString();
    }

    private String getDtoInDtoNew(PsiClass dtoClass) {
        PsiField[] dtoFields = dtoClass.getFields();
        StringBuilder constructeur = new StringBuilder();
        PsiField[] var4 = dtoFields;
        int var5 = dtoFields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PsiField dtoField = var4[var6];
            String var10000 = dtoField.getName().substring(0, 1).toUpperCase();
            String fieldMaj = var10000 + dtoField.getName().substring(1);
            if (!fieldMaj.equals("UpdateDateTime")) {
                constructeur.append("dtoNew.set" + fieldMaj + "(dto.get" + fieldMaj + "());");
            }
        }

        return constructeur.toString();
    }

    private void createController(EntityClasses entityClasses) {
        PsiDirectory controllerDirectory = null == this.containerDirectory.getParent() ? this.containerDirectory :
                this.psiUtils.getOrCreateSubDirectory(this.containerDirectory.getParent(), "controller");
        entityClasses.setControllerDirectory(controllerDirectory);
//        this.createControllerInterface(entityClasses);
        PsiFile[] files = controllerDirectory.getFiles();
        String prefix = "/api/";
        if (0 != files.length) {
            PsiFile[] var6 = files;
            int var7 = files.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                PsiFile file = var6[var8];
                if (file instanceof PsiJavaFile) {
                    Optional<String> value = this.psiUtils.getAnnotationValue(file, "org.springframework.web.bind.annotation.RequestMapping", "value");
                    if (value.isPresent() && !((String)value.get()).startsWith("/api")) {
                        prefix = "/";
                    }
                }
            }
        }

        Optional<PsiClass> apiClass = this.psiUtils.findClass("io.swagger.annotations.Api");
        boolean useAPI = apiClass.isPresent();
        Optional<PsiClass> baseClassOptional = this.psiUtils.findClass("BaseController");
        String suffix = "Controller";
        if (!baseClassOptional.isPresent()) {
            baseClassOptional = this.psiUtils.findClass("BaseResource");
            suffix = "Resource";
            if (!baseClassOptional.isPresent()) {
                suffix = "Controller";
            }
        }

        String entityName = entityClasses.getEntityName();
        String controllerPath = (String)Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(entityName)).reduce((s1, s2) -> {
            return s1.toLowerCase().concat("-").concat(s2.toLowerCase());
        }).orElse("");
        String var10000 = controllerPath.substring(0, 1).toLowerCase();
        controllerPath = var10000 + controllerPath.substring(1);
        entityClasses.setControllerPath(controllerPath);
        StringBuilder content = new StringBuilder();
        content.append("@RequestMapping(\"").append(prefix).append(controllerPath).append("\")").append("@RestController");
        content.append(" public class ").append(entityName).append(suffix);
        String entityFieldName = MyStringUtils.firstLetterToLower(entityName);
        entityClasses.setEntityFieldName(entityFieldName);
        StringBuilder var19 = content.append("{");
        String var10001 = entityClasses.getServiceClass().getName();
        var19 = var19.append("private final " + var10001 + " " + entityFieldName + "Service; ");
        var10001 = entityClasses.getMapperClass().getName();
        var19.append("private final " + var10001 + " " + entityFieldName + "Mapper; ")
                .append("public " + entityName + suffix + "(" + entityClasses.getServiceClass().getName() + " " + entityFieldName + "Service, " + entityClasses.getMapperClass().getName() + " " + entityFieldName + "Mapper){")
                .append("this." + entityFieldName + "Service = " + entityFieldName + "Service;")
                .append("this." + entityFieldName + "Mapper = " + entityFieldName + "Mapper;")
                .append("}")
                .append("@PostMapping ")
                .append("public Result<Void> save(@RequestBody @Validated ")
                .append(entityClasses.getDtoClass().getName()).append(" ").append(entityFieldName + "DTO").append(") { ")
                .append(entityName + " " + entityFieldName + " = " + entityFieldName + "Mapper.toEntity(" + entityFieldName + "DTO);")
                .append("return Result.ok(" + entityFieldName + "Mapper.toDto(" + entityFieldName + "Service.save(" + entityFieldName + "))); }")
                .append("@GetMapping(\"/{id}\") public Result<" + entityClasses.getDtoClass().getName() + "> findById(@PathVariable(\"id\") ")
                .append(entityClasses.getIdField().getType().getPresentableText() + " id) {")
                .append(entityName + " " + entityFieldName + " = " + entityFieldName + "Service.findById(id).orElse(null);")
                .append("return Result.ok(" + entityFieldName + "Mapper.toDto(" + entityFieldName + "));}")
                .append("@DeleteMapping(\"/{id}\") public Result<Void> delete(@PathVariable(\"id\") ")
                .append(entityClasses.getIdField().getType().getPresentableText() + " id) {").append(entityFieldName)
                .append("Service.deleteById(id);}")
                .append("@GetMapping public List<")
                .append(entityClasses.getDtoClass().getName()).append("> list() { return Result.ok(")
                .append(entityFieldName + "Mapper.toDto(" + entityFieldName + "Service.findAll())); }")
                .append("@GetMapping(\"/page-query\") public Result<Page<")
                .append(entityClasses.getDtoClass().getName()).append(">> pageQuery(@PageableDefault(sort = \"createAt\", direction = DESC) Pageable pageable) {  ")
                .append("Page<" + entityName + "> " + entityFieldName + "Page = " + entityFieldName + "Service.findAll(pageable);")
                .append("List<" + entityName + "DTO> dtoList =" + entityFieldName + "Page\n                .stream()\n                .map(" + entityFieldName + "Mapper::toDto).collect(Collectors.toList());")
                .append("return Result.ok(new PageImpl<>(dtoList, pageable, " + entityFieldName + "Page.getTotalElements())) ;}")
                .append("@PutMapping(\"/{id}\") public Result<Void>")
                .append(" update(@RequestBody @Validated " + entityClasses.getDtoClass().getName() + " " + entityFieldName + "DTO, @PathVariable(\"id\") " + entityClasses.getIdField().getType().getPresentableText() + " id) { ")
                .append(entityName + " " + entityFieldName + " =" + entityFieldName + "Mapper.toEntity(" + entityFieldName + "DTO);")
                .append("return Result.ok(" + entityFieldName + "Mapper.toDto(" + entityFieldName + "Service.update(" + entityFieldName + ", id)));}")
                .append("}");
        ClassCreator.of(this.project).init(entityClasses.getEntityName() + suffix, content.toString())
                .importClass("org.springframework.http.HttpStatus")
                .importClass("org.springframework.web.bind.annotation.RequestMaping")
                .importClass("org.springframework.web.bind.annotation.PostMapping")
                .importClass("GetMapping").importClass("DeleteMapping")
                .importClass("org.springframework.web.bind.annotation.RequestBody")
                .importClass("PathVariable")
                .importClass("RequestParam")
                .importClass("Result")
                .importClass("org.springframework.data.domain.Pageable")
                .importClass("org.springframework.data.domain.PageImpl")
                .importClass("org.springframework.data.domain.Page")
                .importClass("java.util.Optional")
                .importClass("java.util.List")
                .importClass("java.util.stream.Collectors")
                .importClass("org.springframework.validation.annotation.Validated")
                .importClass(entityClasses.getControllerClass())
                .importClass(entityClasses.getEntityClass())
                .importClass(entityClasses.getDtoClass())
                .importClass(entityClasses.getMapperClass())
                .importClass(entityClasses.getServiceClass())
                .addTo(entityClasses.getControllerDirectory())
                .and(entityClasses::setControllerClass);
        WriteCommandAction.runWriteCommandAction(this.testProject, () -> {
            this.createUtilsClass(entityClasses);
            this.createBuilderClass(entityClasses);
        });
}

    private void createUtilsClass(EntityClasses entityClasses) {
        String utils = "CustomUtils";
        String content = ContentClass.getUtilTest();
        ClassCreator.of(this.testProject).init(utils, content).importClass("com.fasterxml.jackson.databind.ObjectMapper").addTo(this.controllerTestDirectory);
        this.createControllerUnitTest(entityClasses);
    }
    private void createBuilderClass(EntityClasses entityClasses) {
        String utils = entityClasses.getEntityClassName()+"Builder";
        String content = ContentClass.getBuilderClass();
        ClassCreator.of(this.testProject).init(utils, content)
		        .importClass("com.fasterxml.jackson.databind.ObjectMapper")
		        .addTo(this.controllerTestDirectory);
        this.createControllerUnitTest(entityClasses);
    }

    private PsiDirectory createDirectory(PsiDirectory parentDirectory, String nameDirectory) {
        PsiDirectory controllerDirectory = parentDirectory.findSubdirectory(nameDirectory);
        if (null == controllerDirectory) {
            controllerDirectory = parentDirectory.createSubdirectory(nameDirectory);
        }

        return controllerDirectory;
    }

    private void createRepository(EntityClasses entityClasses) {
        String entityName = entityClasses.getEntityName();

        assert entityName != null;

        PsiDirectory repositoryDirectory = null == this.containerDirectory.getParent() ? this.containerDirectory : this.psiUtils.getOrCreateSubDirectory(this.containerDirectory.getParent(), "repository");
        String repositoryName = entityName.replace("Entity", "").concat("Repository");
        ClassCreator.of(this.project).init(repositoryName, "@Repository public interface "
		        + repositoryName + " extends JpaRepository<" + entityClasses.getEntityClassName() + ", " + entityClasses.getIdType() + ">"
		        + ", JpaSpecificationExecutor<" + entityClasses.getEntityClassName() +">{}")
		        .importClass(entityClasses.getEntityClass())
		        .importClass("PagingAndSortingRepository")
		        .importClass("org.springframework.stereotype.Repository")
		        .addTo(repositoryDirectory)
		        .and((repositoryClass) -> {
            this.createClasses(entityClasses.setRepositoryClass(repositoryClass));
            this.createService(entityClasses);
        });
    }
}
