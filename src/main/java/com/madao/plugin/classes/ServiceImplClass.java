//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.classes;

import com.madao.plugin.EntityClasses;
import com.madao.plugin.utils.MyStringUtils;

/**
 * service
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-13 02:37
 */
public class ServiceImplClass {
    public ServiceImplClass() {
    }

    public static String getContent(EntityClasses entityClasses) {
//        String serviceName = entityClasses.getServiceClass().getName();
        String serviceName = entityClasses.getEntityName().concat("Service");
		String entityName = entityClasses.getEntityName();
		String entityFieldName = MyStringUtils.firstLetterToLower(entityName);
        StringBuilder content = new StringBuilder();
		String var10001 = entityClasses.getMapperClass().getName();
		StringBuilder var10000 = content.append("private final ")
		        .append(entityClasses.getRepositoryClass().getName())
		        .append(" repository; ")
				.append("private final " + var10001 + " " + entityFieldName + "Mapper; ")
		        .append("public " + serviceName + "(" + entityClasses.getRepositoryClass().getName() + " repository," + entityClasses.getMapperClass().getName() +" "+ entityFieldName + "Mapper){ this.repository = repository;" )
				.append("this." + entityFieldName + "Mapper = " + entityFieldName + "Mapper;")
				.append("} ")
				.append("public " + entityClasses.getEntityClass().getName() + "Dto save(")
				.append(entityClasses.getEntityClass().getName() + "Dto "+ entityFieldName + "Dto){\n")
				.append(entityName + " entity =" + entityFieldName + "Mapper.toEntity(" + entityFieldName + "Dto);")
				.append("  return " + entityFieldName + "Mapper.toDto(repository.save(entity));}")
		        .append("public void deleteById(" + entityClasses.getIdType() + " id) { repository.deleteById(id); }")
		        .append("public ").append(entityClasses.getEntityClass().getName())
		        .append("Dto findById(" + entityClasses.getIdType() + " id) { ")
		        .append("return " + entityFieldName + "Mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));}")
		        .append("public Page<").append(entityClasses.getEntityClass().getName()).append("Dto> findByCondition("+entityClasses.getEntityClass().getName()+"Dto "+entityFieldName+"Dto,Pageable pageable) {")
				.append("Page<" + entityClasses.getEntityClass().getName() + "> entityPage=repository.findAll(pageable);")
		        .append("List<" + entityClasses.getEntityClass().getName() + "> entities= entityPage.getContent();")
		        .append("return new PageImpl<>(" + entityFieldName + "Mapper.toDto(entities), pageable, entityPage.getTotalElements());}")
		        .append("public " + entityClasses.getEntityClass().getName() + "Dto update("+entityClasses.getEntityClass().getName()+"Dto ");
		var10000.append(entityFieldName + "Dto ," + entityClasses.getIdField().getType().getPresentableText()
		        + " id){  " + entityClasses.getEntityClass().getName() + "Dto data=findById(id);")
				.append(entityName + " entity =" + entityFieldName + "Mapper.toEntity(" + entityFieldName + "Dto);")
				.append("BeanUtil.copyProperties(data, entity);")
		        .append("return save(" + entityFieldName + "Mapper.toDto(entity)); }");
        return content.toString();
    }
}
