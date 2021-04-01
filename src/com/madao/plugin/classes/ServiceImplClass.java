//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.classes;

import com.madao.plugin.EntityClasses;

public class ServiceImplClass {
    public ServiceImplClass() {
    }

    public static String getContent(EntityClasses entityClasses) {
//        String serviceName = entityClasses.getServiceClass().getName();
        String serviceName = entityClasses.getEntityName().concat("Service");
        StringBuilder content = new StringBuilder();
        StringBuilder var10000 = content.append("private final ")
		        .append(entityClasses.getRepositoryClass().getName())
		        .append(" repository; ")
		        .append("public " + serviceName + "(" + entityClasses.getRepositoryClass().getName() + " repository){ this.repository = repository;} ").append("public " + entityClasses.getEntityClass().getName() + " save(").append(entityClasses.getEntityClass().getName()).append(" entity)").append(" { return repository.save(entity);}").append("public List<" + entityClasses.getEntityClass().getName() + "> save(List<").append(entityClasses.getEntityClass().getName()).append("> entities) ")
                .append("{ return (List<" + entityClasses.getEntityClass().getName() + ">) repository.saveAll(entities); }")
		        .append("public void deleteById(" + entityClasses.getIdType() + " id) { repository.deleteById(id); }")
		        .append("public Optional<").append(entityClasses.getEntityClass().getName())
		        .append("> findById(" + entityClasses.getIdType() + " id) { ")
		        .append("return repository.findById(id);}")
		        .append("public Page<").append(entityClasses.getEntityClass().getName()).append("> findByCondition("+entityClasses.getEntityClass().getName()+"Dto "+entityClasses.getEntityClass().getName().toLowerCase()+"Dto,Pageable pageable) {")
		        .append("Page<" + entityClasses.getEntityClass().getName() + "> entityPage=repository.findAll(pageable);")
		        .append("List<" + entityClasses.getEntityClass().getName() + "> entities= entityPage.getContent();")
		        .append("return new PageImpl<>(entities, pageable, entityPage.getTotalElements());}")
		        .append("public " + entityClasses.getEntityClass().getName() + " update(");

        String var10001 = entityClasses.getEntityClass().getName();

        var10000.append(var10001 + " entity," + entityClasses.getIdField().getType().getPresentableText()
		        + " id){  " + entityClasses.getEntityClass().getName() + " data=findById(id).orElseThrow(ResourceNotFoundException::new);")
		        .append("BeanUtil.copyProperties(data, entity);")
		        .append("return save(entity); }");
        return content.toString();
    }
}
