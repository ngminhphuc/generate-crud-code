# Install
idea插件市场搜索Generate Crud安装

> 开源不易，如果此项目帮到了你,还请不忘star哦🌟,你的关注是我的动力

更多内容请关注公众号：`码道人生`

# Generate_Crud_Code
根据实体类生成原始代码

> 技术栈：JPA、Mapstruct、Mockito Junit5、Swagger

在带有@Entity注解的实体类上右键单击"Generator CRUD"，然后将生成以下模块:
- Controller REST API with Swagger DOC API
- DAO(JpaRepository)
- DTO
- Service (with page query)
- Mapper mapstruct
- Mockito Junit5 Unit Test 
- Dto generate @ApiModelProperty

生成的DTO如果需要加验证，在@Column注解上定义columnDefinition属性和length属性
例如： 
```
@Column(length = 1, columnDefinition = "bit(1) COMMENT '是否推荐'", nullable = false)
```
DTO将生成如下字段
```
@Size(max = 100)
@NotBlank
@ApiModelProperty("你在实体类上定义的columnDefinition.comment注释")
private String ipAddress;
```
![image](https://yd-note.oss-cn-beijing.aliyuncs.com/%E4%B9%B1%E4%B8%83%E5%85%AB%E7%B3%9F/DEMO.gif)

# Verify
Controller层返回标准Result，生成后自己调整即可.

- 如果你的实体类字段类型为Date、LocalDate将会自动添加@CheckDate 注解
- 如果你的实体类字段名称包含 idCard 将会自动添加@CheckIdCard 注解
- 如果你的实体类字段名称包含 mail 将会自动添加@CheckEmail 注解
- 如果你的实体类字段名称包含 phone、mobile 将会自动添加@CheckMobile 注解

## 在项目中使用
### pom.xml文件参考配置
```
<!-- mapstruct dependency -->
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
                <groupId>com.mysema.maven</groupId>
                <artifactId>apt-maven-plugin</artifactId>
                <version>1.1.3</version>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>process</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>target/generated-sources/java</outputDirectory>
                            <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
## Doing
- 完善基于实体类生成的Dto校验
- 多数据源配置
- WebFlux & WebFlux unit test

# License
[Apache-2.0](https://opensource.org/licenses/Apache-2.0)

Copyright (c) 2021-present GuoGuang
