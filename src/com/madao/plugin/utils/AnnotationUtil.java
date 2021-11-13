package com.madao.plugin.utils;

/**
 * 注解工具类
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-13 02:37
 */
public class AnnotationUtil {

	public static String getAnnotationCheckIdCard() {
		return "\n" +
				"import cn.hutool.core.util.IdcardUtil;\n" +
				"\n" +
				"import javax.validation.Constraint;\n" +
				"import javax.validation.ConstraintValidator;\n" +
				"import javax.validation.ConstraintValidatorContext;\n" +
				"import javax.validation.Payload;\n" +
				"import java.lang.annotation.*;\n" +
				"\n" +
				"/**\n" +
				" * china idCard verification\n" +
				" * <p>\n" +
				" * Supported types are:\n" +
				" * <ul>\n" +
				" *     <li>{@code String}</li>\n" +
				" * </ul>\n" +
				" *\n" +
				" * @author GuoGuang\n" +
				" * @公众号 码道人生\n" +
				" * @gitHub https://github.com/GuoGuang\n" +
				" * @website https://madaoo.com\n" +
				" * @created 2021-09-02\n" +
				" */\n" +
				"@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})\n" +
				"@Retention(RetentionPolicy.RUNTIME)\n" +
				"@Documented\n" +
				"@Constraint(validatedBy = CheckIdCard.ValidatorValue.class)\n" +
				"public @interface CheckIdCard {\n" +
				"\n" +
				"\tint value() default 0;\n" +
				"\n" +
				"\tString message() default \"illegal idCard\";\n" +
				"\n" +
				"\tClass<?>[] groups() default {};\n" +
				"\n" +
				"\tClass<? extends Payload>[] payload() default {};\n" +
				"\n" +
				"\t/**\n" +
				"\t * Defines several {@link CheckIdCard} annotations on the same element\n" +
				"\t */\n" +
				"\t@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})\n" +
				"\t@Retention(RetentionPolicy.RUNTIME)\n" +
				"\t@Documented\n" +
				"\t@interface List {\n" +
				"\t\tCheckIdCard[] value();\n" +
				"\t}\n" +
				"\n" +
				"\tclass ValidatorValue implements ConstraintValidator<CheckIdCard, String> {\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic void initialize(CheckIdCard constraintAnnotation) {\n" +
				"\t\t}\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic boolean isValid(String content, ConstraintValidatorContext constraintValidatorContext) {\n" +
				"\t\t\tif (content == null) {\n" +
				"\t\t\t\treturn false;\n" +
				"\t\t\t}\n" +
				"\t\t\treturn IdcardUtil.isValidCard(content);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"}\n";
	}
	public static String getAnnotationCheckDate() {
		return "\n" +
				"import cn.hutool.core.lang.Validator;\n" +
				"\n" +
				"import javax.validation.Constraint;\n" +
				"import javax.validation.ConstraintValidator;\n" +
				"import javax.validation.ConstraintValidatorContext;\n" +
				"import javax.validation.Payload;\n" +
				"import java.lang.annotation.*;\n" +
				"import java.time.LocalDate;\n" +
				"import java.util.Date;\n" +
				"\n" +
				"/**\n" +
				" * china mobile  verification\n" +
				" *\n" +
				" * <p>\n" +
				" * Supported types are:\n" +
				" * <ul>\n" +
				" *     <li>{@code Date}</li>\n" +
				" *     <li>{@code LocalDate}</li>\n" +
				" * </ul>\n" +
				" *\n" +
				" * @author GuoGuang\n" +
				" * @公众号 码道人生\n" +
				" * @gitHub https://github.com/GuoGuang\n" +
				" * @website https://madaoo.com\n" +
				" * @created 2021-09-02\n" +
				" */\n" +
				"@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})\n" +
				"@Retention(RetentionPolicy.RUNTIME)\n" +
				"@Documented\n" +
				"@Constraint(validatedBy = {CheckDate.CheckDateValidatorForDate.class,CheckDate.CheckDateValidatorForLocalDate.class})\n" +
				"public @interface CheckDate {\n" +
				"\n" +
				"\tint value() default 0;\n" +
				"\n" +
				"\tString message() default \"invalid date\";\n" +
				"\n" +
				"\tClass<?>[] groups() default {};\n" +
				"\n" +
				"\tClass<? extends Payload>[] payload() default {};\n" +
				"\n" +
				"\t/**\n" +
				"\t * Defines several {@link CheckDate} annotations on the same element\n" +
				"\t */\n" +
				"\t@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})\n" +
				"\t@Retention(RetentionPolicy.RUNTIME)\n" +
				"\t@Documented\n" +
				"\t@interface List {\n" +
				"\t\tCheckDate[] value();\n" +
				"\t}\n" +
				"\n" +
				"\tclass CheckDateValidatorForDate implements ConstraintValidator<CheckDate, Date> {\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic void initialize(CheckDate constraintAnnotation) {}\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic boolean isValid(Date content, ConstraintValidatorContext constraintValidatorContext) {\n" +
				"\t\t\tif (content == null || \"1970-01-01\".equals(content.toString())) {\n" +
				"\t\t\t\treturn false;\n" +
				"\t\t\t}\n" +
				"\t\t\tString regex = \"(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)\";\n" +
				"\t\t\treturn Validator.isMatchRegex(regex,content.toString());\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\tclass CheckDateValidatorForLocalDate implements ConstraintValidator<CheckDate, LocalDate> {\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic void initialize(CheckDate constraintAnnotation) {}\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic boolean isValid(LocalDate content, ConstraintValidatorContext constraintValidatorContext) {\n" +
				"\t\t\tif (content == null || \"1970-01-01\".equals(content.toString())) {\n" +
				"\t\t\t\treturn false;\n" +
				"\t\t\t}\n" +
				"\t\t\tString regex = \"(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)\";\n" +
				"\t\t\treturn Validator.isMatchRegex(regex,content.toString());\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"}\n";
	}

	public static String getAnnotationCheckMobile() {
		return "\n" +
				"import cn.hutool.core.lang.Validator;\n" +
				"\n" +
				"import javax.validation.Constraint;\n" +
				"import javax.validation.ConstraintValidator;\n" +
				"import javax.validation.ConstraintValidatorContext;\n" +
				"import javax.validation.Payload;\n" +
				"import java.lang.annotation.*;\n" +
				"\n" +
				"/**\n" +
				" * china mobile  verification\n" +
				" *\n" +
				" * <p>\n" +
				" * Supported types are:\n" +
				" * <ul>\n" +
				" *     <li>{@code String}</li>\n" +
				" * </ul>\n" +
				" *\n" +
				" * @author GuoGuang\n" +
				" * @公众号 码道人生\n" +
				" * @gitHub https://github.com/GuoGuang\n" +
				" * @website https://madaoo.com\n" +
				" * @created 2021-09-02\n" +
				" */\n" +
				"@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})\n" +
				"@Retention(RetentionPolicy.RUNTIME)\n" +
				"@Documented\n" +
				"@Constraint(validatedBy = CheckMobile.ValidatorValue.class)\n" +
				"public @interface CheckMobile {\n" +
				"\n" +
				"\tint value() default 0;\n" +
				"\n" +
				"\tString message() default \"invalid mobile\";\n" +
				"\n" +
				"\tClass<?>[] groups() default {};\n" +
				"\n" +
				"\tClass<? extends Payload>[] payload() default {};\n" +
				"\n" +
				"\t/**\n" +
				"\t * Defines several {@link CheckMobile} annotations on the same element\n" +
				"\t */\n" +
				"\t@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})\n" +
				"\t@Retention(RetentionPolicy.RUNTIME)\n" +
				"\t@Documented\n" +
				"\t@interface List {\n" +
				"\t\tCheckMobile[] value();\n" +
				"\t}\n" +
				"\n" +
				"\tclass ValidatorValue implements ConstraintValidator<CheckMobile, String> {\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic void initialize(CheckMobile constraintAnnotation) {}\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic boolean isValid(String content, ConstraintValidatorContext constraintValidatorContext) {\n" +
				"\t\t\tif (content == null) {\n" +
				"\t\t\t\treturn false;\n" +
				"\t\t\t}\n" +
				"\t\t\treturn Validator.isMobile(content);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"}\n";
	}

	public static String getAnnotationCheckEmail() {
		return "\n" +
				"import cn.hutool.core.lang.Validator;\n" +
				"\n" +
				"import javax.validation.Constraint;\n" +
				"import javax.validation.ConstraintValidator;\n" +
				"import javax.validation.ConstraintValidatorContext;\n" +
				"import javax.validation.Payload;\n" +
				"import java.lang.annotation.*;\n" +
				"\n" +
				"/**\n" +
				" * mailbox verification\n" +
				" *\n" +
				" * <p>\n" +
				" * Supported types are:\n" +
				" * <ul>\n" +
				" *     <li>{@code String}</li>\n" +
				" * </ul>\n" +
				" *\n" +
				" * @author GuoGuang\n" +
				" * @公众号 码道人生\n" +
				" * @gitHub https://github.com/GuoGuang\n" +
				" * @website https://madaoo.com\n" +
				" * @created 2021-09-02\n" +
				" */\n" +
				"@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})\n" +
				"@Retention(RetentionPolicy.RUNTIME)\n" +
				"@Documented\n" +
				"@Constraint(validatedBy = CheckEmail.ValidatorValue.class)\n" +
				"public @interface CheckEmail {\n" +
				"\n" +
				"\tint value() default 0;\n" +
				"\n" +
				"\tString message() default \"invalid email\";\n" +
				"\n" +
				"\tClass<?>[] groups() default {};\n" +
				"\n" +
				"\tClass<? extends Payload>[] payload() default {};\n" +
				"\n" +
				"\t/**\n" +
				"\t * Defines several {@link CheckEmail} annotations on the same element\n" +
				"\t */\n" +
				"\t@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})\n" +
				"\t@Retention(RetentionPolicy.RUNTIME)\n" +
				"\t@Documented\n" +
				"\t@interface List {\n" +
				"\t\tCheckEmail[] value();\n" +
				"\t}\n" +
				"\n" +
				"\tclass ValidatorValue implements ConstraintValidator<CheckEmail, String> {\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic void initialize(CheckEmail constraintAnnotation) {}\n" +
				"\n" +
				"\t\t@Override\n" +
				"\t\tpublic boolean isValid(String content, ConstraintValidatorContext constraintValidatorContext) {\n" +
				"\t\t\tif (content == null) {\n" +
				"\t\t\t\treturn false;\n" +
				"\t\t\t}\n" +
				"\t\t\treturn Validator.isEmail(content);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"}\n";
	}
}
