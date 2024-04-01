package space.bum.spring_boot.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class MyDiaryTest {
  static Validator validator = null;
  
  @BeforeAll
  public static void setupValidatorInstance() {
      validator = Validation.buildDefaultValidatorFactory().getValidator();
  }
  
  @Test
  void test4() {
    var diary = new MyDiary();
    diary.setContent("");
    diary.setTitle("냉무");
    /**
     * > title 은 내용이 있으므로 not empty 충족 
     * > content 는 빈문자열이 있으므로 null 아님.
     */
    Set<ConstraintViolation<MyDiary>> violations = validator.validate(diary);
    
    assertThat(violations.size()).isEqualTo(0);    
  }
  
  @Test
  void test3_1() {
    var diary = new MyDiary();
    diary.setContent("");
    diary.setTitle("  ");
    /**
     * 검증 오류 발생 전무
     * > title 은 null 아이고, 빈(empty) 것도 아님. 다만, 공백문자임. 
     * > content 는 빈문자열이 있으므로 null 아님.
     */
    Set<ConstraintViolation<MyDiary>> violations = validator.validate(diary);
    
    assertThat(violations.size()).isEqualTo(0);    
  }
  
  @Test
  void test3() {
    var diary = new MyDiary();
    diary.setContent("");
    diary.setTitle("");
    /**
     * title 검증 오류 발생
     * > 비록 null 은 면했으나 빈(empty) 상태이므로 검증 실패 
     * > content 는 빈문자열이 있으므로 null 아님.
     */
    Set<ConstraintViolation<MyDiary>> violations = validator.validate(diary);
    
    assertThat(violations.size()).isEqualTo(1);    
  }
  
  @Test
  void test2() {
    var diary = new MyDiary();
    diary.setContent("");
    /**
     * title 검증 오류 발생
     * > content 는 빈문자열이 있으므로 null 아님.
     */
    Set<ConstraintViolation<MyDiary>> violations = validator.validate(diary);
    
    assertThat(violations.size()).isEqualTo(1);    
  }
  
  @Test
  void test1() {
    var diary = new MyDiary();
    /**
     * title, content 모두 검증 오류 발생
     */
    Set<ConstraintViolation<MyDiary>> violations = validator.validate(diary);
    
    assertThat(violations.size()).isEqualTo(2);    
  }

}
