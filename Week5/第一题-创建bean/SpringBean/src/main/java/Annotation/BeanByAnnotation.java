package Annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@Repository
public class BeanByAnnotation {
    private int id;
    private String name;
}
