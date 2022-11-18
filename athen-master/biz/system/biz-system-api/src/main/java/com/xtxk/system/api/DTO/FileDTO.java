package com.xtxk.system.api.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenying
 * @date 2022/11/8 5:34 PM
 * @time 5:34 PM
 * @since 1.0.0
 **/
@Data
public class FileDTO implements Serializable {
    private String file;

    public FileDTO(String file) {
        this.file = file;
    }
}
