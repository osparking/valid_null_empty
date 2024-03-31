package space.bum.spring_boot.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyDiary {
  @NotEmpty(message = "제목은 공백이 아닌 문자가 필요함")
  private String title;
  
  @NotNull(message = "내용에는 공백문자라도 있어야 됨")
  private String content;
  
  private String description;

}
