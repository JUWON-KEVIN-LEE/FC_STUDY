package com.juwon.annotation_study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class AnnotationMain {
	public static void main(String[] args) {
		UseAnnotation use = new UseAnnotation();
		use.getClass().getAnnotation(CustomAnnotation.class).key();
	}
}

// Target = 적용할 대상 : 생성자, 멤버변수, 타입(클래스), 파라미터, 메소드
// Retention = Annotation 정보의 유지 단계 소스코드, 클래스, 런타임...

// Documented = javadoc 에 문서화할 element
// Inherited = 상속되는 Annotation

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {
	public String value() default "값";
	public String key();
}

@CustomAnnotation(key = "추가된 key 값")
class UseAnnotation {
	
}
