package com.gejian.live.common.dto.roomManager;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 房管表
 * </p>
 *
 * @author fengliang
 * @since 2021-09-28
 */
@Data
public class AnchorRoomManagerDTO{


    private Long id;

    /**
     * 主播id
     */
    private Long anchorId;

    /**
     * 房管id
     */
    private Long managerId;

    /**
     * 房管昵称
     */
    private String managerName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;






}
