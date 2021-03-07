//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifierList;
import com.madao.plugin.utils.PsiUtils;

import java.util.Objects;
import java.util.stream.Stream;

public class EntityClasses {
    private String FieldIdMaj;
    private PsiClass entityClass;
    private PsiClass repositoryClass;
    private PsiClass mapperClass;
    private PsiClass dtoClass;
    private PsiClass serviceClass;
    private PsiClass serviceImplClass;
    private PsiClass controllerClass;
    private PsiDirectory serviceDirectory;
    private PsiDirectory queryDirectory;
    private PsiClass queryClass;
    private PsiClass daoClass;
    private PsiClass pageQueryClass;
    private PsiClass pageDataClass;
    private PsiDirectory mapperDirectory;
    private PsiDirectory controllerDirectory;
    private String controllerPath;
    private String entityFieldName;

    public EntityClasses() {
    }

    public String getFieldIdMaj() {
        String var10000 = this.getIdField().getName().substring(0, 1).toUpperCase();
        return var10000 + this.getIdField().getName().substring(1);
    }

    public void setFieldIdMaj(String fieldIdMaj) {
        this.FieldIdMaj = fieldIdMaj;
    }

    public PsiClass getEntityClass() {
        return this.entityClass;
    }

    public EntityClasses setEntityClass(PsiClass entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    public PsiClass getRepositoryClass() {
        return this.repositoryClass;
    }

    EntityClasses setRepositoryClass(PsiClass repositoryClass) {
        this.repositoryClass = repositoryClass;
        return this;
    }

    public PsiClass getMapperClass() {
        return this.mapperClass;
    }

    public EntityClasses setMapperClass(PsiClass mapperClass) {
        this.mapperClass = mapperClass;
        return this;
    }

    public PsiClass getDtoClass() {
        return this.dtoClass;
    }

    public EntityClasses setDtoClass(PsiClass dtoClass) {
        this.dtoClass = dtoClass;
        return this;
    }

    public PsiClass getServiceClass() {
        return this.serviceClass;
    }

    public EntityClasses setServiceClass(PsiClass serviceClass) {
        this.serviceClass = serviceClass;
        return this;
    }

    public PsiClass getServiceImplClass() {
        return this.serviceImplClass;
    }

    public EntityClasses setServiceImplClass(PsiClass serviceImplClass) {
        this.serviceImplClass = serviceImplClass;
        return this;
    }

    public PsiClass getControllerClass() {
        return this.controllerClass;
    }

    public EntityClasses setControllerClass(PsiClass controllerClass) {
        this.controllerClass = controllerClass;
        return this;
    }

    public PsiDirectory getServiceDirectory() {
        return this.serviceDirectory;
    }

    public EntityClasses setServiceDirectory(PsiDirectory serviceDirectory) {
        this.serviceDirectory = serviceDirectory;
        return this;
    }

    public String getEntityName() {
        return ((String)Objects.requireNonNull(this.getEntityClass().getName())).replace("Entity", "");
    }

    public String getEntityClassName() {
        return this.entityClass.getName();
    }

    public PsiClass getQueryClass() {
        return this.queryClass;
    }

    public EntityClasses setQueryClass(PsiClass queryClass) {
        this.queryClass = queryClass;
        return this;
    }

    public PsiClass getDaoClass() {
        return this.daoClass;
    }

    public EntityClasses setDaoClass(PsiClass daoClass) {
        this.daoClass = daoClass;
        return this;
    }

    public PsiDirectory getQueryDirectory() {
        return this.queryDirectory;
    }

    public EntityClasses setQueryDirectory(PsiDirectory queryDirectory) {
        this.queryDirectory = queryDirectory;
        return this;
    }

    public PsiClass getPageQueryClass() {
        return this.pageQueryClass;
    }

    public EntityClasses setPageQueryClass(PsiClass pageQueryClass) {
        this.pageQueryClass = pageQueryClass;
        return this;
    }

    public PsiClass getPageDataClass() {
        return this.pageDataClass;
    }

    public EntityClasses setPageDataClass(PsiClass pageDataClass) {
        this.pageDataClass = pageDataClass;
        return this;
    }

    public String getIdType() {
        return PsiUtils.getIdType(this.entityClass);
    }

    public PsiDirectory getMapperDirectory() {
        return this.mapperDirectory;
    }

    public void setMapperDirectory(PsiDirectory mapperDirectory) {
        this.mapperDirectory = mapperDirectory;
    }

    public void setControllerDirectory(PsiDirectory controllerDirectory) {
        this.controllerDirectory = controllerDirectory;
    }

    public PsiDirectory getControllerDirectory() {
        return this.controllerDirectory;
    }

    public PsiField getIdField() {
        return (PsiField)Stream.of(this.entityClass.getFields()).filter((psiField) -> {
            return Stream.of(((PsiModifierList)Objects.requireNonNull(psiField.getModifierList())).getAnnotations()).anyMatch((psiAnnotation) -> {
                return psiAnnotation.getText().equals("@Id");
            });
        }).findFirst().get();
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getControllerPath() {
        return this.controllerPath;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public String getEntityFieldName() {
        return this.entityFieldName;
    }

    public String getMockitoAnyType() {
        return this.getIdField().getType().getPresentableText().equals("String") ? "ArgumentMatchers.anyString()" : "1L";
    }

    public String getMockitoAnyValue() {
        return this.getIdField().getType().getPresentableText().equals("String") ? "\"id\"" : "1L";
    }

    public String getIdTypeUpperCaseFirstChar() {
        char var10000 = Character.toUpperCase(this.getIdType().charAt(0));
        return var10000 + this.getIdType().substring(1);
    }
}
