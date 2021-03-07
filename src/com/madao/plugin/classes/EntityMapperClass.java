//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.classes;

public class EntityMapperClass {
    public EntityMapperClass() {
    }

    public static String getCoreString() {
        return "public interface EntityMapper<D, E> {\n" +
                "\n" +
                "    E toEntity(D dto);\n" +
                "\n" +
                "    D toDto(E entity);\n" +
                "\n" +
                "    List<E> toEntity(List<D> dtoList);\n" +
                "\n" +
                "    List<D> toDto(List<E> entityList);\n" +
                "\n" +
                "    Set<D> toDto(Set<E> entityList);\n" +
                "}\n";
    }
}
