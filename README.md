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