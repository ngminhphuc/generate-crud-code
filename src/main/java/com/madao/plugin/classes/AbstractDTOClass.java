//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.classes;

/**
 * 公共DTO
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-13 02:37
 */
public class AbstractDTOClass {
    public AbstractDTOClass() {
    }

    public static String getCoreString() {
        return "import java.time.LocalDateTime;\n\n " +
         "public class AbstractDto <E> {\n\n    " +
                "private LocalDateTime createAt;\n\n    " +
                "private LocalDateTime lastModifiedAt;\n\n    " +
                "private String createdBy;\n\n    " +
                "private String lastModifiedBy;\n\n    " +
                "public LocalDateTime getCreateAt() {\n        return createAt;\n    }    " +
                "public void setCreateAt(LocalDateTime createAt) {\n        this.createAt = createAt;\n    }"+
                "public String getLastModifiedBy() {\n        return lastModifiedBy;\n    }\n\n    " +
                "public void setLastModifiedBy(String lastModifiedBy) {\n        this.lastModifiedBy = lastModifiedBy;\n}"+
                "public String getCreatedBy() {\n        return createdBy;\n    }\n\n    " +
                "public void setCreatedBy(String createdBy) {\n        this.createdBy = createdBy;\n    }"+
                "public LocalDateTime getLastModifiedAt() {\n        return lastModifiedAt;\n    }\n    " +
                "public void setLastModifiedAt(LocalDateTime lastModifiedAt) {\n        this.lastModifiedAt = lastModifiedAt;\n    }\n}";
    }
}
