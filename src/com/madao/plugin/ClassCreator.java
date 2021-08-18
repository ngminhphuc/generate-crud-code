//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.madao.plugin;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.madao.plugin.utils.PsiUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class ClassCreator {
    private PsiJavaFile javaFile;
    private Project project;
    private PsiUtils psiUtils;

    private ClassCreator(Project project) {
        this.project = project;
        this.psiUtils = PsiUtils.of(project);
    }

    public static ClassCreator of(Project project) {
        return new ClassCreator(project);
    }

    public ClassCreator init(String name, String content) {
        this.javaFile = (PsiJavaFile)PsiFileFactory.getInstance(this.project).createFileFromText(name + ".java", JavaFileType.INSTANCE, content);
        return this;
    }

    ClassCreator importClass(String className) {
        if (StringUtils.isBlank(className))
            return this;
        Objects.requireNonNull(this.javaFile);
        this.psiUtils.findClassGlobal(className, psiClass -> true).ifPresent(this.javaFile::importClass);
        return this;
    }

    ClassCreator importClassIf(String className, Supplier<Boolean> supplier) {
        if ((Boolean)supplier.get()) {
            this.importClass(className);
        }

        return this;
    }

    ClassCreator importClassIf(Supplier<String> nameSupplier, Supplier<Boolean> supplier) {
        if ((Boolean)supplier.get()) {
            this.importClass((String)nameSupplier.get());
        }

        return this;
    }

    ClassCreator importClass(PsiClass psiClass) {
        if (null == psiClass) {
            return this;
        } else {
            this.javaFile.importClass(psiClass);
            return this;
        }
    }

    And addTo(PsiDirectory psiDirectory) {
        return new And(((PsiJavaFile)Optional.ofNullable(psiDirectory.findFile(this.javaFile.getName())).orElseGet(() -> {
            this.psiUtils.format(this.javaFile);
            return (PsiJavaFile)psiDirectory.add(this.javaFile);
        })).getClasses()[0]);
    }

    ClassCreator copyFields(PsiClass srcClass) {
        PsiClass aClass = this.javaFile.getClasses()[0];
        PsiElementFactory elementFactory = PsiElementFactory.getInstance(this.project);
        PsiField[] fields = srcClass.getFields();
        PsiMethod constructeur = elementFactory.createConstructor((String)Objects.requireNonNull(aClass.getQualifiedName()));
        aClass.add(constructeur);
        PsiField[] var6 = fields;
        int var7 = fields.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            PsiField field = var6[var8];
            String name = field.getName();

            assert name != null;

            PsiType type = field.getType();
            this.psiUtils.findClass(type.getCanonicalText()).ifPresent((typeClass) -> {
                this.psiUtils.importClass(aClass, new PsiClass[]{typeClass});
            });
            String typeName = type.getPresentableText();
            if (typeName.contains(".")) {
                typeName = typeName.substring(typeName.lastIndexOf(".") + 1);
            }

            PsiAnnotation psiAnnotation = field.getAnnotation("javax.persistence.Column");
            StringBuilder annotationStringBuilder = new StringBuilder();
            if (typeName.equals("LocalDateTime")) {
                annotationStringBuilder.append("@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)");
            }

	        if (null != psiAnnotation) {
		        PsiAnnotationMemberValue memberLengthValue = psiAnnotation.findAttributeValue("length");
		        if (null != memberLengthValue) {
			        int length = Integer.parseInt(memberLengthValue.getText());
			        annotationStringBuilder.append("@Size(max = ").append(length).append(") ");
			        this.importClass("javax.validation.constraints.Size");
		        }
		        PsiAnnotationMemberValue nullableValue = psiAnnotation.findAttributeValue("nullable");
		        if ("false".equals(nullableValue.getText())){
			        if ("String".equals(typeName)) {
				        annotationStringBuilder.append("@NotBlank ");
				        this.importClass("javax.validation.constraints.NotBlank");
			        } else {
				        annotationStringBuilder.append("@NotNull ");
				        this.importClass("javax.validation.constraints.NotNull");
			        }
		        }
	        }

            PsiField cField = elementFactory.createFieldFromText(annotationStringBuilder.toString() + "private " + typeName + " " + name + ";", (PsiElement)null);
            aClass.add(cField);
            PsiMethod normalSetter = elementFactory.createMethodFromText(this.createSetter(name, type.getCanonicalText()), field);
            PsiMethod getter = elementFactory.createMethodFromText(this.createGetter(name, type.getCanonicalText()), field);
            if (0 == aClass.findMethodsByName(normalSetter.getName()).length) {
                aClass.add(normalSetter);
            }

            if (0 == aClass.findMethodsByName(getter.getName()).length) {
                aClass.add(getter);
            }
        }

        return this;
    }

    private PsiMethod getConstructeurWithParam(PsiElementFactory psiElementFactory, PsiClass aClass, PsiField[] fields) {
        PsiMethod constructor = psiElementFactory.createConstructor(aClass.getQualifiedName());
        constructor.getModifierList().setModifierProperty("public", true);
        Stream.of(fields).forEach((psiField) -> {
            PsiParameter builderParameter = psiElementFactory.createParameter(psiField.getName(), psiField.getType());
            constructor.getParameterList().add(builderParameter);
        });
        return constructor;
    }

    private String createBuilderSetter(String className, String name, String type) {
        return "public " + className + " " + name + "(" + type + " " + name + ") {this." + name + " = " + name + ";return this;}";
    }

    private String createSetter(@NotNull String name, String type) {

        String var10000 = name.substring(0, 1).toUpperCase();
        return "public void set" + var10000 + name.substring(1) + "(" + type + " " + name + ") {this." + name + " = " + name + ";}";
    }

    private String createGetter(String name, String type) {
        return "public " + type + " get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "() {return this." + name + ";}";
    }

    public static class And {
        private PsiClass psiClass;

        public And(PsiClass psiClass) {
            this.psiClass = psiClass;
        }

        public void and(Consumer<PsiClass> callback) {
            callback.accept(this.psiClass);
        }
    }
}
