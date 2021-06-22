# 安装插件
idea插件市场搜索Generate Crud安装

# generate_crud_code
根据实体类生成原始代码

在带有@Entity注解的实体类上右键单击"Generator CRUD"，然后将生成以下模块:
- Controller REST API with Swagger DOC API
- DAO(JpaRepository)
- DTO
- Service (with page query)
- Mapper mapstruct
- Mockito Junit5 Unit Test


生成的DTO如果需要加验证，在@Column注解上定义columnDefinition属性和length属性
例如： 
```
@Column(length = 100,columnDefinition = "not null")
```
DTO将生成如下字段
```
@Size(max = 100)
@NotBlank
private String ipAddress;
```
![image](https://yd-note.oss-cn-beijing.aliyuncs.com/%E4%B9%B1%E4%B8%83%E5%85%AB%E7%B3%9F/DEMO.gif)

# note
Controller层返回标准Result，生成后自己调整即可

## pom.xml文件参考配置
```
<!-- mapstruct依赖 -->
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.4.2.Final</version>
</dependency>

<!-- 这一块主要是找到生成的类文件 -->
 <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.4.2.Final</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.0</version>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```
生成文件后需要标记生成的文件为代码(src/generated-sources/java目录标记为Generated Sources Root)
![generated-sources标记为源码.png](https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/generated-sources%E6%A0%87%E8%AE%B0%E4%B8%BA%E6%BA%90%E7%A0%81.png)
