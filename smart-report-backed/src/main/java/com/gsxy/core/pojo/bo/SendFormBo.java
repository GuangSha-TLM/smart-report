package com.gsxy.core.pojo.bo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SendFormBo implements Serializable {

    private Long formId;
    private List<Long> userId;
    private List<Integer> schoolId;

}
