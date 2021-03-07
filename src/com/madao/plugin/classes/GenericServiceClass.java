//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.classes;

public class GenericServiceClass {
    public GenericServiceClass() {
    }

    public static String getCoreString() {
        return "public interface GenericService<E, M> {\n    E save(E entity);\n\n    List<E> save(List<E> entities);\n\n    void deleteById(M id);\n\n    Optional<E> findById(M id);\n\n    List<E> findAll();\n\n    Page<E> findAll(Pageable pageable);\n\n    E update(E entity, M id);\n}";
    }
}
