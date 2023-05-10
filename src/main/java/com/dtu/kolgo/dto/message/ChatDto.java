package com.dtu.kolgo.dto.message;

import com.dtu.kolgo.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatDto {

    private ChatType type;
    private String date;
    private Integer userId;
    private List<Integer> userIds;

}
