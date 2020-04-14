package com.msunsoft.spt.business.mapper.user;

import com.msunsoft.spt.business.domain.user.TbPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TbPermissionMapper extends MyMapper<TbPermission> {
    List<TbPermission> selectByUserId(@Param("userId") Long userId);

    List<Map<String,Object>> selectPermissionByParams(@Param("roleId") long roleId, @Param("id") long id);
}