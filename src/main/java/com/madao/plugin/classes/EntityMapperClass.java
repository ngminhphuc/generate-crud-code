//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.classes;

/**
 * 实体类Mapper
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-13 02:37
 */
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
